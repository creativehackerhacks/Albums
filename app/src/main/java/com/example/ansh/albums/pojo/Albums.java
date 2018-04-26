package com.example.ansh.albums.pojo;

public class Albums {

    private String title, artist, numOfSongs;

    public Albums() {
    }

    public Albums(String title, String artist, String numOfSongs) {
        this.title = title;
        this.artist = artist;
        this.numOfSongs = numOfSongs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String genre) {
        this.artist = genre;
    }

    public String getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(String numOfSongs) {
        this.numOfSongs = numOfSongs;
    }
}
