package com.example.jnlycklama.healthnow.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jnlycklama.healthnow.R;
import com.example.jnlycklama.healthnow.VideoActivity;


public class RequestFragment extends Fragment {

    private LinearLayout scr1;
    private LinearLayout scr2;
    private TextView timer;

    public RequestFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_request, container, false);

        scr1 = (LinearLayout) v.findViewById(R.id.screen_1);
        scr2 = (LinearLayout) v.findViewById(R.id.screen_2);

        timer = (TextView) v.findViewById(R.id.timer);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                long secondsLeft = millisUntilFinished / 1000;
                long minutes = secondsLeft/60;
                long seconds = secondsLeft%60;
                timer.setText(Long.toString(minutes) + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                scr1.setVisibility(View.INVISIBLE);
                scr2.setVisibility(View.VISIBLE);
            }
        }.start();



        return v;
    }





}
