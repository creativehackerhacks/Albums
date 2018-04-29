package com.example.ansh.albums.dataSet;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.ansh.albums.pojo.Albums;

import java.util.ArrayList;
import java.util.List;

public class GetAlbums {

    private Cursor mAlbumsAlbumCursor;
    private List<Albums> mAlbumList;

    public GetAlbums(List<Albums> albumList) {
        this.mAlbumList = albumList;
    }

    public void getAlbumList(Context context) {

        ContentResolver musicResolver = context.getContentResolver();
        Uri musicUri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        mAlbumsAlbumCursor = musicResolver.query(musicUri, null, null, null, null);
        if (mAlbumsAlbumCursor != null && mAlbumsAlbumCursor.moveToFirst()) {

            int titleColumn = mAlbumsAlbumCursor.getColumnIndex
                    (MediaStore.Audio.Albums.ALBUM);
            int idColumn = mAlbumsAlbumCursor.getColumnIndex
                    (MediaStore.Audio.Albums._ID);
            int artistColumn = mAlbumsAlbumCursor.getColumnIndex
                    (MediaStore.Audio.Albums.ARTIST);
            int numOfSongs = mAlbumsAlbumCursor.getColumnIndex
                    (MediaStore.Audio.Albums.NUMBER_OF_SONGS);

            Uri sArtWorkUri = Uri.parse("content://media/external/audio/albumart");

            //add songs to list
            do {

                // Getting album art

                long thisID = mAlbumsAlbumCursor.getLong(idColumn);
                Uri uri = ContentUris.withAppendedId(sArtWorkUri, thisID);

                String title = mAlbumsAlbumCursor.getString(titleColumn);
                String artist = mAlbumsAlbumCursor.getString(artistColumn);
                String noSongs = mAlbumsAlbumCursor.getString(numOfSongs);


                mAlbumList.add(new Albums(thisID, title, artist, noSongs, uri));
            }
            while (mAlbumsAlbumCursor.moveToNext());
        }
        mAlbumsAlbumCursor.close();
    }

}
