package com.personal.prithivi.muse;

import android.Manifest;
import android.content.pm.PackageManager;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    private final int EXTERNAL_STORAGE_READ_SUCCESS = 2;
    private final String DESCRIPTION_UNABLE_TO_READ_EXTERNAL_STORAGE = "Unable to read from external storage.";
    SongRetriever songRetriever = new SongRetriever(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    EXTERNAL_STORAGE_READ_SUCCESS);

        } else {

            this.songRetriever.retrieveSongs();

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case EXTERNAL_STORAGE_READ_SUCCESS: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    this.songRetriever.retrieveSongs();

                } else {
                    Toast.makeText(this, this.DESCRIPTION_UNABLE_TO_READ_EXTERNAL_STORAGE,
                            Toast.LENGTH_LONG);
                }


                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }



}
