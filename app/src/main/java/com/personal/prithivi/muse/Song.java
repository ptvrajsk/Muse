package com.personal.prithivi.muse;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {


    private String title;
    private String artist;
    private String path;
    private String displayName;
    private String songDuration;
    private String album;

    public static final Parcelable.Creator<Song> CREATOR
            = new Parcelable.Creator<Song>() {
        public Song createFromParcel(Parcel parcel) {
            return new Song(parcel);
        }

        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public Song(String title, String artist, String path, String displayName, String songDuration, String album) {
        this.title = title;
        this.artist = artist;
        this.path = path;
        this.displayName = displayName;
        this.songDuration = songDuration;
        this.album = album;
    }

    public Song(Parcel parcel) {
        this.title = parcel.readString();
        this.artist = parcel.readString();
        this.path = parcel.readString();
        this.displayName = parcel.readString();
        this.songDuration = parcel.readString();
        this.album = parcel.readString();
    }

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
}
