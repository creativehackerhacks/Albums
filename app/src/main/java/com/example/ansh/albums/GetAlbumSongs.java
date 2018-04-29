package com.example.ansh.albums;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.ansh.albums.pojo.AlbumSongs;
import com.example.ansh.albums.pojo.Albums;

import java.util.List;

public class GetAlbumSongs {

    private Cursor mAlbumSongsCursor;
    private List<AlbumSongs> mAlbumSongsList;

    public GetAlbumSongs(List<AlbumSongs> albumSongsList) {
        this.mAlbumSongsList = albumSongsList;
    }

    public void getAlbumSongsList(Context context, long albumId) {

        String selection = "is_music != 0";

        if(albumId > 0) {
            selection = selection + " and album_id = " + albumId;
        }

        ContentResolver musicResolver = context.getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        mAlbumSongsCursor = musicResolver.query(musicUri, null, selection, null, null);
        if (mAlbumSongsCursor != null && mAlbumSongsCursor.moveToFirst()) {

            int titleColumn = mAlbumSongsCursor.getColumnIndex
                    (MediaStore.Audio.Media.TITLE);
            int idColumn = mAlbumSongsCursor.getColumnIndex
                    (MediaStore.Audio.Media._ID);
            int artistColumn = mAlbumSongsCursor.getColumnIndex
                    (MediaStore.Audio.Media.ARTIST);
            int songDuration = mAlbumSongsCursor.getColumnIndex
                    (MediaStore.Audio.Media.DURATION);

//            Uri sArtWorkUri = Uri.parse("content://media/external/audio/media");

            //add songs to list
            do {

                // Getting album art

//                long thisID = mAlbumSongsCursor.getLong(idColumn);
//                Uri uri = ContentUris.withAppendedId(sArtWorkUri, thisID);

                String title = mAlbumSongsCursor.getString(titleColumn);
                String artist = mAlbumSongsCursor.getString(artistColumn);
                String noSongs = mAlbumSongsCursor.getString(songDuration);


                mAlbumSongsList.add(new AlbumSongs(title, artist));
            }
            while (mAlbumSongsCursor.moveToNext());
        }
        mAlbumSongsCursor.close();
    }

}
