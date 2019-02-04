package com.personal.prithivi.muse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class SongsList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list);

        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song("title 1", "artist 1", "path 1", "name", "Duration"));
        songs.add(new Song("title 1", "artist 1", "path 1", "name", "Duration"));
        songs.add(new Song("title 1", "artist 1", "path 1", "name", "Duration"));
        songs.add(new Song("title 1", "artist 1", "path 1", "name", "Duration"));
        songs.add(new Song("title 1", "artist 1", "path 1", "name", "Duration"));
        songs.add(new Song("title 1", "artist 1", "path 1", "name", "Duration"));

        recyclerView = this.findViewById(R.id.songList);
        recyclerViewAdapter = new SongAdapter(songs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

    }


}
