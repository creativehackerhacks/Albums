package com.example.ansh.albums.pojo;

import android.net.Uri;

public class Albums {

    private String title, artist, numOfSongs;
    private Uri mUri;
    private Long mAlbumId;

    public Albums() {
    }

    public Albums(Long albumId, String title, String artist, String numOfSongs, Uri uri) {
        this.title = title;
        this.artist = artist;
        this.numOfSongs = numOfSongs;
        this.mUri = uri;
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

    public Uri getUri() {
        return mUri;
    }

    public void setUri(Uri uri) {
        mUri = uri;
    }

    public Long getAlbumId() {
        return mAlbumId;
    }

    public void setAlbumId(Long albumId) {
        mAlbumId = albumId;
    }
}
