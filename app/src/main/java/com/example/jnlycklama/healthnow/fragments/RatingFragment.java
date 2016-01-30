package com.example.jnlycklama.healthnow.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.example.jnlycklama.healthnow.R;


public class RatingFragment extends Fragment {

    public RatingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rating, container, false);
        RatingBar rb = (RatingBar) v.findViewById(R.id.rating_bar);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                System.out.println(String.valueOf(rating));
            }
        });

        return v;
    }

}
