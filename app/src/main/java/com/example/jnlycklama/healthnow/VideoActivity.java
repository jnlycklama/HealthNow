package com.example.jnlycklama.healthnow;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.jnlycklama.healthnow.fragments.RatingFragment;
import com.example.jnlycklama.healthnow.fragments.RequestFragment;
import com.example.jnlycklama.healthnow.fragments.VideoCallFragment;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("medtoc");

        RequestFragment firstFragment = new RequestFragment();
        changeFragment(firstFragment);
    }

    public void changeFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        ft.replace(R.id.fragment_container, fragment).commit();
    }

    public void startCall(View v){
        changeFragment(new VideoCallFragment());
    }

    public void endCall(View v){
        changeFragment(new RatingFragment());
    }

    public void submitRating(View v){
        Intent i  = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
