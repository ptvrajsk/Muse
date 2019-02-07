package com.personal.prithivi.muse;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Splash_Screen extends AppCompatActivity {

    private final int EXTERNAL_STORAGE_READ_SUCCESS = 2;
    private final String DESCRIPTION_UNABLE_TO_READ_EXTERNAL_STORAGE = "Unable to read from external storage.";
    private final String DESCRIPTION_EXITING_APPLICATION = "Exiting Application.";
    DatabaseManager databaseManager = new DatabaseManager(this);
    private ImageView testImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ArrayList<Song> songs = null;

        //Verify that application has persmissions to read from storage
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    EXTERNAL_STORAGE_READ_SUCCESS);

        } else {
            this.databaseManager.retrieveSongs();
        }

        Intent i = new Intent(this, Activity_List_Songs.class);
//        i.putParcelableArrayListExtra("SongsCollection", songs);

        final Intent nextAct = i;


        Button butt = this.findViewById(R.id.nextActivityButton);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(nextAct);
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case EXTERNAL_STORAGE_READ_SUCCESS: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    return;

                } else {
                    Toast.makeText(this, this.DESCRIPTION_UNABLE_TO_READ_EXTERNAL_STORAGE,
                            Toast.LENGTH_LONG).show();
                    Toast.makeText(this, this.DESCRIPTION_EXITING_APPLICATION,
                            Toast.LENGTH_LONG).show();
                }


            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }



}
