package com.example.jnlycklama.healthnow;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView startVid;
    RelativeLayout bottomChunk;
    ImageView video;
    FrameLayout circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void analysisBtn(View v){
        Intent i  = new Intent(this, AnalysisActivity.class);
        startActivity(i);
    }
    public void videoBtn(View v){
        video = (ImageView) v.findViewById(R.id.imageView2);
        startVid = (TextView) v.findViewById(R.id.videoCall);
        bottomChunk = (RelativeLayout) v.findViewById(R.id.bottom_section);
        circle = (FrameLayout) v.findViewById(R.id.circle);

        //int c = 0XB90504;
        video.setImageResource(R.drawable.videoplayerred);
        startVid.setTextColor(Color.rgb(185, 5, 4));
        bottomChunk.setBackgroundColor(Color.rgb(246,246,246));
        circle.setBackgroundResource(R.drawable.circlered);
        Intent i  = new Intent(this, VideoActivity.class);
        startActivity(i);
    }


}
