package com.example.jnlycklama.healthnow.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.example.jnlycklama.healthnow.R;
import com.example.jnlycklama.healthnow.VideoActivity;


public class VideoCallFragment extends Fragment {


    private SurfaceView preview=null;
    private SurfaceHolder previewHolder=null;
    private Camera camera=null;
    private boolean inPreview=false;
    private boolean cameraConfigured=false;

    private LinearLayout loading_screen;

    public VideoCallFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_call, container, false);

        preview=(SurfaceView)view.findViewById(R.id.camera_view);
        previewHolder=preview.getHolder();
        previewHolder.addCallback(surfaceCallback);
        previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        loading_screen = (LinearLayout) view.findViewById(R.id.loading_screen);

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        int color = 0xFF222222;
        progressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        progressBar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);

        final ImageView videoView =(ImageView)view.findViewById(R.id.video_preview);

//        VideoView videoView =(VideoView)view.findViewById(R.id.video_preview);
//        MediaController mediaController= new MediaController(getActivity());
//        mediaController.setAnchorView(videoView);
//        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.one);
//        videoView.setMediaController(mediaController);
//        videoView.setVideoURI(uri);
//        videoView.requestFocus();
//        videoView.start();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        videoView.setAlpha(0f);
                        videoView.setVisibility(View.VISIBLE);
                        videoView.animate().alpha(1f).setDuration(1000).setListener(null);

                        loading_screen.animate().alpha(0f).setDuration(1000)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        loading_screen.setVisibility(View.GONE);
                                    }
                                });

                    }
                });

            }
        }, 2500);

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        camera=Camera.open(1);
        startPreview();
    }
    @Override
    public void onPause() {
        if (inPreview) {
            camera.stopPreview();
        }
        camera.release();
        camera=null;
        inPreview=false;

        super.onPause();
    }
    private Camera.Size getBestPreviewSize(int width, int height, Camera.Parameters parameters) {
        Camera.Size result=null;

        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width<=width && size.height<=height) {
                if (result==null) {
                    result=size;
                }
                else {
                    int resultArea=result.width*result.height;
                    int newArea=size.width*size.height;

                    if (newArea>resultArea) {
                        result=size;
                    }
                }
            }
        }

        return(result);
    }

    private void initPreview(int width, int height) {
        if (camera!=null && previewHolder.getSurface()!=null) {
            try {
                camera.setPreviewDisplay(previewHolder);
            } catch (Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }

            if (!cameraConfigured) {
                Camera.Parameters parameters = camera.getParameters();
                parameters.set("camera-id",0);

                Camera.Size size=getBestPreviewSize(width, height, parameters);

                if (size!=null) {
                    parameters.setPreviewSize(size.width, size.height);
                    camera.setParameters(parameters);
                    cameraConfigured=true;
                }
            }
        }
    }

    private void startPreview() {
        if (cameraConfigured && camera!=null) {
            camera.startPreview();
            inPreview=true;
        }
    }

    SurfaceHolder.Callback surfaceCallback=new SurfaceHolder.Callback() {
        public void surfaceCreated(SurfaceHolder holder) {
            // no-op -- wait until surfaceChanged()
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            Camera.Parameters parameters = camera.getParameters();
            parameters.set("camera-id",0);
            Display display = ((WindowManager) getActivity().getSystemService(getActivity().WINDOW_SERVICE)).getDefaultDisplay();

            if(display.getRotation() == Surface.ROTATION_0){
                parameters.setPreviewSize(height, width);
                camera.setDisplayOrientation(90);
            }
            if(display.getRotation() == Surface.ROTATION_90){
                parameters.setPreviewSize(width, height);
            }
            if(display.getRotation() == Surface.ROTATION_180){
                parameters.setPreviewSize(height, width);
            }
            if(display.getRotation() == Surface.ROTATION_270){
                parameters.setPreviewSize(width, height);
                camera.setDisplayOrientation(180);
            }

            initPreview(width, height);
            startPreview();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            // no-op
        }
    };

}
