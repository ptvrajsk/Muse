package com.personal.prithivi.muse;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SongsManager {

    private Context context;
    private ArrayList<Song> songs;
    private HashMap<String, Bitmap> thumbnails;
    private AtomicInteger number = new AtomicInteger(0);

    public SongsManager() {
        thumbnails = new HashMap<>();
    }

    public ArrayList<Song> retrieveSongs(Context context) {

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION
        };

        final String sortOrder = MediaStore.Audio.AudioColumns.TITLE + " COLLATE LOCALIZED ASC";

        ArrayList<Song> songs = new ArrayList<>();

        Cursor cursor = null;
        try {

            Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            cursor = context.getContentResolver().query(uri, projection, selection, null, sortOrder);

            if(cursor != null){

                cursor.moveToFirst();

                while(!cursor.isAfterLast()) {

                    String title = cursor.getString(0);
                    String artist = cursor.getString(1);
                    String path = cursor.getString(2);
                    String displayName  = cursor.getString(3);
                    String songDuration = cursor.getString(4);
                    cursor.moveToNext();

                    if(path != null && path.endsWith(".mp3")) {
                        songs.add(new Song (title, artist, path, displayName, songDuration));
                    }
                }

            }

            // print to see list of mp3 files
            for( Song song : songs) {
                Log.i("TAG", song.getDisplayName());
            }

        } catch (Exception e) {
            Log.e("TAG", e.toString());
        } finally {
            if(cursor != null){
                cursor.close();
            }
        }

        this.songs = songs;
        return songs;
    }


    public void cacheThumbnails(Handler h) {

        int progressStatus = 0;
        int songsPerPercent = songs.size() / 100;
        int counter = 1;

        MediaMetadataRetriever mmr = new MediaMetadataRetriever();

        for (Song s : songs) {
            try {
                mmr.setDataSource(s.getPath());
            } catch (RuntimeException e) {
                Log.e("THUMBNAIL_FAIL", s.getPath());
                continue;
            }
            byte[] img = mmr.getEmbeddedPicture();
            if (img != null) {
                thumbnails.put(s.getPath(), BitmapFactory.decodeByteArray(img, 0, img.length));
                Log.e("THUMBNAIL_SUCCESS", s.getPath());

            } else {
                Log.e("THUMBNAIL_UNAVAILABLE", s.getPath());
            }

            if (counter == songsPerPercent) {
                counter = 0;
                progressStatus++;
                Message msg = new Message();
                msg.obj = progressStatus;
                h.sendMessage(msg);
            } else {
                counter++;
            }

        }

        //image.setImageBitmap(thumbnails.get("/storage/D004-A0DC/Songs/Vikram Vedha/Yaanji-MassTamilan.com.mp3"));

    }

    private void updateProgress(ProgressBar prv) {
        final ProgressBar pv = prv;
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                pv.setProgress(number.incrementAndGet());
            }
        });
    }



}
