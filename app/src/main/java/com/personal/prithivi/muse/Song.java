package com.personal.prithivi.muse;

public class Song {


    private String title;
    private String artist;
    private String path;
    private String displayName;
    private String songDuration;

    public Song(String title, String artist, String path, String displayName, String songDuration) {
        this.title = title;
        this.artist = artist;
        this.path = path;
        this.displayName = displayName;
        this.songDuration = songDuration;
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
}
