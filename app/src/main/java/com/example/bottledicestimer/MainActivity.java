package com.example.bottledicestimer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button timer;
    private Button bottle;
    private Button dices;
    private Button arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

        onClickFunction();
    }

    void onClickFunction(){

        timer = (Button)findViewById(R.id.buttonTimer);
        bottle = (Button)findViewById(R.id.buttonBottle);
        dices = (Button)findViewById(R.id.buttonDice);
        arrow = (Button)findViewById(R.id.buttonArrow);

        timer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // button.setEnabled(false);
//                        Handler handler = new Handler();
//                        handler.postDelayed(()->timer.setEnabled(true), 500);


                        openActivityTimer();

                    }
                }
        );

        bottle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        openActivityBottle();

                    }
                }
        );

        dices.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        openActivityDices();

                    }
                }
        );

        dices.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        openActivityDices();

                    }
                }
        );
        arrow.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        openActivityArrow();

                    }
                }
        );
    }




    public void openActivityTimer(){
        Intent intent = new Intent(this, ActivityTimer.class);
        startActivity(intent);
    };

    public void openActivityBottle(){
        Intent intent = new Intent(this, ActivityBottle.class);
        startActivity(intent);
    };

    public void openActivityDices(){
        Intent intent = new Intent(this, ActivityDices.class);
        startActivity(intent);
    }

    public void openActivityArrow(){
        Intent intent = new Intent(this, ActivityArrow.class);
        startActivity(intent);
    };


}