package com.example.jnlycklama.healthnow;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.jnlycklama.healthnow.fragments.VideoCallFragment;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        VideoCallFragment firstFragment = new VideoCallFragment();

        getFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
    }

}
