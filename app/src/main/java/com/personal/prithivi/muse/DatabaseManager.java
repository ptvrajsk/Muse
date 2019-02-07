package com.personal.prithivi.muse;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseManager {

    private Context context;
    private static ArrayList<Song> songsCollection;

    public DatabaseManager(Context context) {
        this.context = context;
    }

    public void retrieveSongs() {

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM
        };
        final String sortOrder = MediaStore.Audio.AudioColumns.TITLE + " COLLATE LOCALIZED ASC";
        ArrayList<Song> mp3Files = new ArrayList<>();

        Cursor cursor = null;
        try {
            Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            cursor = this.context.getContentResolver().query(uri, projection, selection, null, sortOrder);

            if( cursor != null) {
                cursor.moveToFirst();

                while( !cursor.isAfterLast() ) {

                    String title = cursor.getString(0);
                    String artist = cursor.getString(1);
                    String path = cursor.getString(2);
                    String displayName  = cursor.getString(3);
                    String songDuration = cursor.getString(4);
                    String album = cursor.getString((5));

                    cursor.moveToNext();
                    if(path != null && path.endsWith(".mp3")) {
                        mp3Files.add(new Song (title, artist, path, displayName, songDuration, album));
                    }

                }

            }

            // print to see list of mp3 files
            for( Song song : mp3Files) {
                Log.i("TAG", song.getDisplayName());
            }

        } catch (Exception e) {

            Log.e("TAG", e.toString());

        } finally {
            if( cursor != null) {
                cursor.close();
            }
        }

        DatabaseManager.songsCollection = mp3Files;
    }

    public static ArrayList<Song> getSongsCollection() {
        return songsCollection;
    }


}
