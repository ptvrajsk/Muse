package com.personal.prithivi.muse;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;

public class Song {

    private String title;
    private String artist;
    private String path;
    private String displayName;
    private String songDuration;
    private String album;
    private Bitmap thumbnail;
    private ThumbnailStatus hasThumbnail;

    //////////////////////////////////////
    //       Song Specific Enum         //
    //////////////////////////////////////

    private enum ThumbnailStatus {
        AVAILABLE, UNAVAILABLE, UNKNOWN
    }

    //////////////////////////////////////
    //           Constructors           //
    //////////////////////////////////////

    public Song(String title, String artist, String path, String displayName, String songDuration, String album) {
        this.title = title;
        this.artist = artist;
        this.path = path;
        this.displayName = displayName;
        this.songDuration = songDuration;
        this.album = album;
        this.hasThumbnail = ThumbnailStatus.UNKNOWN;
    }

    //////////////////////////////////////
    //         Other functions          //
    //////////////////////////////////////

    public boolean generateThumbnail() {

        if (this.hasThumbnail == ThumbnailStatus.UNKNOWN) {

            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(this.path);
            byte[] raw = mmr.getEmbeddedPicture();
            mmr.release();

            if (raw != null) {
                this.thumbnail = BitmapFactory.decodeByteArray(raw, 0, raw.length);
                this.hasThumbnail = ThumbnailStatus.AVAILABLE;
                return true;
            } else {
                this.hasThumbnail = ThumbnailStatus.UNAVAILABLE;
                return false;
            }

        }

        return false;
    }

    public boolean hasThumbnail() {
        return this.hasThumbnail == ThumbnailStatus.AVAILABLE;
    }

    //////////////////////////////////////
    //        Getters & Setters         //
    //////////////////////////////////////

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getPath() {
        return path;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public String getAlbum() {
        return album;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

}
