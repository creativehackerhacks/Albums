package com.example.ansh.albums.pojo;

import android.net.Uri;
import android.widget.TextView;

public class AlbumSongs {

    private String mSongTitle, mSongSubTitle;

    public AlbumSongs(String songTitle, String songSubTitle) {
        mSongTitle = songTitle;
        mSongSubTitle = songSubTitle;
    }

    public String getSongTitle() {
        return mSongTitle;
    }

    public void setSongTitle(String songTitle) {
        mSongTitle = songTitle;
    }

    public String getSongSubTitle() {
        return mSongSubTitle;
    }

    public void setSongSubTitle(String songSubTitle) {
        mSongSubTitle = songSubTitle;
    }

}
