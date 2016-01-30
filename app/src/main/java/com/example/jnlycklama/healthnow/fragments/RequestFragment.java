package com.example.jnlycklama.healthnow.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jnlycklama.healthnow.R;
import com.example.jnlycklama.healthnow.VideoActivity;


public class RequestFragment extends Fragment {

    private LinearLayout scr1;
    private LinearLayout scr2;

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


        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                scr1.setVisibility(View.INVISIBLE);
                                scr2.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                },
                5000
        );

        return v;
    }





}
