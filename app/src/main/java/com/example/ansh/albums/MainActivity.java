package com.example.ansh.albums;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ansh.albums.adapter.AlbumsAdapter;
import com.example.ansh.albums.dataSet.GetAlbums;
import com.example.ansh.albums.pojo.Albums;
import com.example.ansh.albums.utils.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private PermissionUtils mPermissionUtils;

    private RecyclerView mRecyclerView;
    private TextView mTextPermissionStatus;

    private List<Albums> mAlbumsList = new ArrayList<>();
    private AlbumsAdapter mAlbumsAdapter;
    private Albums mAlbums;

    private GetAlbums mGetAlbums;

    private int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextPermissionStatus = findViewById(R.id.show_text_permission_status);
        mTextPermissionStatus.setOnClickListener(this);
        mGetAlbums = new GetAlbums(mAlbumsList);

        mRecyclerView = findViewById(R.id.main_recyclerView);
        mAlbumsAdapter = new AlbumsAdapter(MainActivity.this, mAlbumsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAlbumsAdapter);

        mPermissionUtils = PermissionUtils.getInstance(this);

    }

    // We'll receive callback from onRequestPermissionResult
    // therefore use this method in activity or fragment.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionUtils.MY_PERMISSION_REQUEST_READ_EXTERNAL_STORAGE:
                // if request is cancelled, the result arrays are empty.
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
                    // Do the contacts-related task you need to do.
                    Toast.makeText(this, "Got the permission! HURRAY!!!", Toast.LENGTH_SHORT).show();
                    prepareAlbumsData();
                    Log.i(TAG, "onRequestPermissionsResult: I am running");
                } else {
                    // permission denied, boo!
                    // Disable the functionality that depends on this permission
                    Toast.makeText(this, "LOL!! Didn't got the permission...", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onRequestPermissionsResult: I am NOT running");
                }
                return;
        }

        // Other 'case' lines to check for other permission this app might request.

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == mTextPermissionStatus.getId()) {
            goToSettings();
        }
    }

    private void goToSettings() {
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        Intent settingsIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, uri);
        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(settingsIntent);
    }

    private void prepareAlbumsData() {
        mGetAlbums.getAlbumList(this);
        Log.i(TAG, "prepareAlbumsData: " + mAlbumsList.size());
        mAlbumsAdapter.notifyDataSetChanged();
        mTextPermissionStatus.setVisibility(mAlbumsList.isEmpty() ? View.VISIBLE : View.INVISIBLE);
    }

//    public void prepareAlbumsData() {
//
//        Albums albums = new Albums("Mad Max: Fury Road", "Action & Adventure", "2015");
//        mAlbumsList.add(albums);
//
//        albums = new Albums("Inside Out", "Animation, Kids & Family", "2015");
//        mAlbumsList.add(albums);
//
//        albums = new Albums("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
//        mAlbumsList.add(albums);
//
//        albums = new Albums("Shaun the Sheep", "Animation", "2015");
//        mAlbumsList.add(albums);
//
//        albums = new Albums("The Martian", "Science Fiction & Fantasy", "2015");
//        mAlbumsList.add(albums);
//
//        albums = new Albums("Mission: Impossible Rogue Nation", "Action", "2015");
//        mAlbumsList.add(albums);
//
//        albums = new Albums("Up", "Animation", "2009");
//        mAlbumsList.add(albums);
//
//        albums = new Albums("Star Trek", "Science Fiction", "2009");
//        mAlbumsList.add(albums);
//
//        albums = new Albums("The LEGO Movie", "Animation", "2014");
//        mAlbumsList.add(albums);
//
//        albums = new Albums("Iron Man", "Action & Adventure", "2008");
//        mAlbumsList.add(albums);
//
//        albums = new Albums("Aliens", "Science Fiction", "1986");
//        mAlbumsList.add(albums);
//
//        albums = new Albums("Chicken Run", "Animation", "2000");
//        mAlbumsList.add(albums);
//
//        albums = new Albums("Back to the Future", "Science Fiction", "1985");
//        mAlbumsList.add(albums);
//
//        albums = new Albums("Raiders of the Lost Ark", "Action & Adventure", "1981");
//        mAlbumsList.add(albums);
//
//        albums = new Albums("Goldfinger", "Action & Adventure", "1965");
//        mAlbumsList.add(albums);
//
//        albums = new Albums("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
//        mAlbumsList.add(albums);
//
//        mAlbumsAdapter.notifyDataSetChanged();
//        mTextPermissionStatus.setVisibility(mAlbumsList.isEmpty() ? View.VISIBLE : View.INVISIBLE);
//    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mPermissionUtils.checkPerm(this)) {
            prepareAlbumsData();
            Log.i(TAG, "onStart: I am running");
        }
    }

}
