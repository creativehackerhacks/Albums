package com.example.ansh.albums.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ansh.albums.R;
import com.example.ansh.albums.pojo.AlbumSongs;

import java.util.List;

public class AlbumSongsAdapter extends RecyclerView.Adapter<AlbumSongsAdapter.AlbumSongsViewHolder> {

    private Context mContext;
    private List<AlbumSongs> mAlbumSongs;
    private LayoutInflater mLayoutInflater;
    private View mView;

    public AlbumSongsAdapter(Context context, List albumSongsList)
    {
        this.mContext = context;
        this.mAlbumSongs = albumSongsList;

        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public AlbumSongsAdapter.AlbumSongsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mView = mLayoutInflater.inflate(R.layout.album_songs_row, parent, false);

        return new AlbumSongsViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumSongsAdapter.AlbumSongsViewHolder holder, int position) {
        AlbumSongs songs = mAlbumSongs.get(position);
        holder.mTitle.setText(songs.getSongTitle());
        holder.mSubTitle.setText(songs.getSongSubTitle());
    }

    @Override
    public int getItemCount() {
        return mAlbumSongs.size();
    }

    public class AlbumSongsViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle, mSubTitle;

        public AlbumSongsViewHolder(View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.songTitle);
            mSubTitle = itemView.findViewById(R.id.songArtist);
        }
    }
}
