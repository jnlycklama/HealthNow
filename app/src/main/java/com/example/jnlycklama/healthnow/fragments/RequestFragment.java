package com.example.jnlycklama.healthnow.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

        ImageView image = (ImageView) v.findViewById(R.id.imageView4);

        //ImageView image = (ImageView) findViewById(R.id.imageView4);
        Bitmap bitImg = BitmapFactory.decodeResource(getResources(),
                R.drawable.two);
        image.setImageBitmap(getRoundedCornerImage(bitImg));

        scr1 = (LinearLayout) v.findViewById(R.id.screen_1);
        scr2 = (LinearLayout) v.findViewById(R.id.screen_2);

        timer = (TextView) v.findViewById(R.id.timer);

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                long secondsLeft = millisUntilFinished / 1000;
                long minutes = secondsLeft/60;
                long seconds = secondsLeft%60;
                timer.setText(Long.toString(minutes) + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                scr1.setVisibility(View.GONE);


                scr2.setAlpha(0f);
                scr2.setVisibility(View.VISIBLE);


                // Animate the content view to 100% opacity, and clear any animation
                // listener set on the view.
                scr2.animate().alpha(1f).setDuration(1000).setListener(null);

                // Animate the loading view to 0% opacity. After the animation ends,
                // set its visibility to GONE as an optimization step (it won't
                // participate in layout passes, etc.)
                scr1.animate().alpha(0f).setDuration(1000)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                scr1.setVisibility(View.GONE);
                            }
                        });


            }
        }.start();



        return v;
    }

    public static Bitmap getRoundedCornerImage(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = bitmap.getWidth();

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;

    }




}
