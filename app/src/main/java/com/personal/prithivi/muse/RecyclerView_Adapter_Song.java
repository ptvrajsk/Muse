package com.personal.prithivi.muse;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerView_Adapter_Song extends RecyclerView.Adapter<RecyclerView_Adapter_Song.SongHolder> {

    private ArrayList<Song> allSongs;
    private Bitmap defaultThumbnail;


    //////////////////////////////////////
    //           Holder Class           //
    //////////////////////////////////////

    public static class SongHolder extends RecyclerView.ViewHolder {

        public ImageView thumbnail;
        public TextView songTitle;
        public TextView songAlbum;

        public SongHolder(View itemView) {
            super(itemView);
            this.thumbnail = itemView.findViewById(R.id.song_thumbnail);
            this.songTitle = itemView.findViewById(R.id.song_title);
            this.songAlbum = itemView.findViewById(R.id.song_album);
        }
    }


    //////////////////////////////////////
    //        Adapter functions         //
    //////////////////////////////////////

    public RecyclerView_Adapter_Song(ArrayList<Song> allSongs, Bitmap defaultThumbnail) {
        this.allSongs = allSongs;
        this.defaultThumbnail = defaultThumbnail;
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_individual_song, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {

        Song s = allSongs.get(position);
        holder.songTitle.setText(s.getDisplayName());
        holder.songAlbum.setText(s.getAlbum());

        if (s.hasThumbnail()) {
            holder.thumbnail.setImageBitmap(s.getThumbnail());
        } else {
            holder.thumbnail.setImageBitmap(this.defaultThumbnail);
        }


    }


    @Override
    public int getItemCount() {
        return allSongs.size();
    }
}
