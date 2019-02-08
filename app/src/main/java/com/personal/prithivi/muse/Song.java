package com.personal.prithivi.muse;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {

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

    public Song(Parcel parcel) {
        this.title = parcel.readString();
        this.artist = parcel.readString();
        this.path = parcel.readString();
        this.displayName = parcel.readString();
        this.songDuration = parcel.readString();
        this.album = parcel.readString();
        this.hasThumbnail = ThumbnailStatus.UNKNOWN;
        this.generateThumbnail();
    }

    //////////////////////////////////////
    //         Other functions          //
    //////////////////////////////////////

    public boolean generateThumbnail() {

        if (this.hasThumbnail == ThumbnailStatus.UNKNOWN) {

            MediaMetadataRetriever mmr = new MediaMetadataRetriever();

            try {
                mmr.setDataSource(this.path);
            } catch (RuntimeException e) {
                return false;
            }

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
    //       Parcelable Interface       //
    //////////////////////////////////////

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }
        @Override
        public Song[] newArray(int i) {
            return new Song[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.artist);
        parcel.writeString(this.path);
        parcel.writeString(this.displayName);
        parcel.writeString(this.songDuration);
        parcel.writeString(this.album);
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
