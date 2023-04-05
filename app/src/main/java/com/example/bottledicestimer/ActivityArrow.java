package com.example.bottledicestimer;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ActivityArrow extends AppCompatActivity {
    private ImageView arrow;
    private Random random = new Random();
    private int lastDir;
    private boolean spinning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrow);

        arrow = (ImageView)findViewById(R.id.arrow);
    }

    public void spinArrow(View v){
        if(!spinning){
            int newDir = random.nextInt(3600);
            float pivotX = arrow.getWidth() / 2;
            float pivotY = arrow.getHeight() / 2;

            Animation rotate = new RotateAnimation(lastDir, newDir, pivotX,pivotY);
            rotate.setDuration(2500);
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            lastDir = newDir;
            arrow.startAnimation(rotate);
        }


    }
}