package com.personal.prithivi.muse;

public class Song {


    private String title;
    private String artist;
    private String path;
    private String displayName;
    private String songDuration;
    private String album;

    public Song(String title, String artist, String path, String displayName, String songDuration, String album) {
        this.title = title;
        this.artist = artist;
        this.path = path;
        this.displayName = displayName;
        this.songDuration = songDuration;
        this.album = album;
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
