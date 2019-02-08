package com.personal.prithivi.muse;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Activity_List_Songs extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_songs);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.BLACK);
        }


        final ArrayList<Song> songs = DatabaseManager.getSongsCollection();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Song s : songs) {
                    s.generateThumbnail();
                }
            }
        }).start();

        recyclerView = this.findViewById(R.id.songList);
        recyclerViewAdapter = new Song_RecyclerView_Adapter(songs,
                BitmapFactory.decodeResource(this.getResources(), R.drawable.image_default_thumbnail));
        recyclerView.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public void onScrollStateChanged(int state) {

                switch (state) {

                    case RecyclerView.SCROLL_STATE_SETTLING:

                    case RecyclerView.SCROLL_STATE_IDLE:
                        final int start = this.findFirstVisibleItemPosition();
                        final int end = this.findLastVisibleItemPosition();
                        final Handler handler = new Handler();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                boolean needToUpdate = true;
                                for (int i = start; i <= end; i++) {

                                    needToUpdate = songs.get(i).generateThumbnail();
                                    if (needToUpdate) {
                                        final int itemToUpdate = i;
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                recyclerViewAdapter.notifyItemChanged(itemToUpdate);
                                            }
                                        });
                                    }

                                }
                            }
                        }).start();


                }

                super.onScrollStateChanged(state);
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);




    }


}
