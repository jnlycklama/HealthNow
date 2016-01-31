package com.example.jnlycklama.healthnow;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout button = (RelativeLayout) findViewById(R.id.video_call_btn);
        final ImageView cameraPic = (ImageView) findViewById(R.id.invert_camera_pic);
        final TextView cameraText =  (TextView) findViewById(R.id.invert_text);
        final FrameLayout circle =  (FrameLayout) findViewById(R.id.invert_circle);

        button.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button.setBackgroundColor(Color.parseColor("#ffffff"));
                    cameraPic.setImageDrawable(getResources().getDrawable(R.drawable.videoplayerred));
                    cameraText.setTextColor(Color.parseColor("#B90504"));
                    circle.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_red));

                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    button.setBackgroundColor(Color.parseColor("#B90504"));
                    cameraPic.setImageDrawable(getResources().getDrawable(R.drawable.videoplayerwhite));
                    cameraText.setTextColor(Color.parseColor("#ffffff"));
                    circle.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle));

                    Intent i = new Intent(getApplicationContext(), VideoActivity.class);
                    startActivity(i);
                }
                return true;
            }
        });

    }

    public void analysisBtn(View v){
        Intent i  = new Intent(this, AnalysisActivity.class);
        startActivity(i);
    }




}
