package com.personal.prithivi.muse;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {

    private ArrayList<Song> allSongs;

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

    public SongAdapter(ArrayList<Song> allSongs) {
        this.allSongs = allSongs;
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

    }

    @Override
    public int getItemCount() {
        return allSongs.size();
    }
}
