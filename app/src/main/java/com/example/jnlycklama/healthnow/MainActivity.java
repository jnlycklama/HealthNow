package com.example.jnlycklama.healthnow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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
        Intent i  = new Intent(this, VideoActivity.class);
        startActivity(i);
    }


}
