package com.example.ansh.albums.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ansh.albums.R;
import com.example.ansh.albums.RecyclerViewClickListener;
import com.example.ansh.albums.pojo.Albums;

import java.util.ArrayList;
import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyAlbumsViewHolder> {

    private RecyclerViewClickListener mClickListener;
    private Context mContext;
    private View mView;
    private LayoutInflater mLayoutInflater;

    private List<Albums> mAlbumsList = new ArrayList<>();

    // Initialize it with the DataSet
    public AlbumsAdapter(Context context, List albumsList, RecyclerViewClickListener listener) {
        this.mContext = context;
        this.mAlbumsList = albumsList;

        this.mClickListener = listener;

        // Now we don't have to use ViewRoot.getContext()
        // inside onCreateViewHolder
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public AlbumsAdapter.MyAlbumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = mLayoutInflater.inflate(R.layout.album_list_row, parent, false);

        return new MyAlbumsViewHolder(mView, mClickListener);
    }



    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.MyAlbumsViewHolder holder, int position) {
        Albums albums = mAlbumsList.get(position);
        holder.mTitle.setText(albums.getTitle());
        holder.mArtist.setText(albums.getArtist());
        holder.mNumOfSongs.setText(albums.getNumOfSongs());
        Glide.with(mContext)
                .load(albums.getUri())
                .into(holder.mImageView);
    }



    @Override
    public int getItemCount() {
        return mAlbumsList.size();
    }



    public class MyAlbumsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Initialize views inside albums_list_row.xml
        private TextView mTitle, mArtist, mNumOfSongs;
        private ImageView mImageView;
        private RecyclerViewClickListener mListener;

        public MyAlbumsViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title);
            mArtist = itemView.findViewById(R.id.artist);
            mNumOfSongs = itemView.findViewById(R.id.num_of_songs);
            mImageView = itemView.findViewById(R.id.albumImage);

            mListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v, getAdapterPosition());
        }
    }


}


