package top.tobiaslee.cardemulator;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;

/**
 * Created by tobiaslee on 2017/6/3.
 */

public class FlipImageView {
    private static final String TAG = "FlipTextView";
    private Context mContext;

    private ImageView iv1;
    private ImageView iv2;
    private MediaPlayer player;
    private String imgPath = "http://filthon.ralee.cc/file/18";
    Rotate3dAnimation rotation1, rotation2, rotation3, rotation4;


    public FlipImageView(Context mContext, ImageView iv1, ImageView iv2) {
        this.mContext = mContext;
        this.iv1 = iv1;
        this.iv2 = iv2;
        player = MediaPlayer.create(mContext, R.raw.rare);
    }

    public void readyImage() {
        iv1.setOnClickListener((v)-> {
            flipImageView();
        });
    }

    private void flipImageView() {
        Log.d(TAG, "flip:  in flip");

        final float centerX = iv1.getWidth()/ 2.0f;
        final float centerY = iv1.getHeight() / 2.0f;
        rotation1 = new Rotate3dAnimation(mContext, 0, 90, centerX, centerY, 0 ,false);
        rotation2 = new Rotate3dAnimation(mContext, -90, 0, centerX, centerY, 0 ,false);


        rotation1.setDuration(1000);
        rotation1.setFillAfter(true);
        rotation1.setInterpolator(new AccelerateDecelerateInterpolator());

        rotation2.setDuration(500);
        rotation2.setFillAfter(true);
        rotation2.setInterpolator(new AccelerateDecelerateInterpolator());

        rotation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Glide.with(mContext).load(imgPath).apply(new RequestOptions().override(iv1.getWidth(),
                        iv1.getHeight())).into(iv2);
                player.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv1.clearAnimation();
                iv1.setVisibility(View.GONE);
                iv2.setVisibility(View.VISIBLE);
                iv2.startAnimation(rotation2);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

//        rotation2.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                iv2.setOnClickListener((v)-> {
//                    flipImageBack();
//                });
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });

        iv1.startAnimation(rotation1);

    }
    // flip it back
    private void flipImageBack() {
        Log.d(TAG, "flip:  in flip");

        final float centerX = iv1.getWidth()/ 2.0f;
        final float centerY = iv2.getHeight() / 2.0f;
        rotation1 = new Rotate3dAnimation(mContext, 0, -90, centerX, centerY, 0 ,false);
        rotation2 = new Rotate3dAnimation(mContext, 90, 0, centerX, centerY, 0 ,false);


        rotation1.setDuration(1000);
        rotation1.setFillAfter(true);
        rotation1.setInterpolator(new AccelerateDecelerateInterpolator());

        rotation2.setDuration(500);
        rotation2.setFillAfter(true);
        rotation2.setInterpolator(new AccelerateDecelerateInterpolator());

        rotation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv2.setVisibility(View.GONE);
                iv1.setVisibility(View.VISIBLE);
                iv1.startAnimation(rotation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        rotation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                iv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flipImageView();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        iv2.startAnimation(rotation1);
    }

}
