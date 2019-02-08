package com.personal.prithivi.muse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Activity_Play_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__play__screen);

        ImageView play_screen_thumbnail = this.findViewById(R.id.play_screen_thumbnail);


        Song selectedSong = this.getIntent().getParcelableExtra("SelectedSong");
        play_screen_thumbnail.setImageBitmap(selectedSong.getThumbnail());


    }
}
