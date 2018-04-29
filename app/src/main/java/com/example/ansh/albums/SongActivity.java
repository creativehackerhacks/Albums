package com.example.ansh.albums;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ansh.albums.adapter.AlbumSongsAdapter;
import com.example.ansh.albums.pojo.AlbumSongs;

import java.util.ArrayList;
import java.util.List;

public class SongActivity extends AppCompatActivity {

    private Context mContext = SongActivity.this;

    private List<AlbumSongs> mAlbumSongs;
    private GetAlbumSongs mGetAlbumSongs;

    private RecyclerView mRecyclerView;
    private List<AlbumSongs> mAlbumSongsList = new ArrayList<>();
    private AlbumSongsAdapter mAlbumSongsAdapter;

    private Long mAlbumId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_songs);

        Intent intent = getIntent();
        mAlbumId = intent.getLongExtra("AlbumId", 0);
        Log.i("LALALA", mAlbumId.toString());

        mGetAlbumSongs = new GetAlbumSongs(mAlbumSongsList);
        mGetAlbumSongs.getAlbumSongsList(this, mAlbumId);

        mRecyclerView = findViewById(R.id.song_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(SongActivity.this));
        mAlbumSongsAdapter = new AlbumSongsAdapter(this, mAlbumSongsList);
        mRecyclerView.setAdapter(mAlbumSongsAdapter);

    }
}
