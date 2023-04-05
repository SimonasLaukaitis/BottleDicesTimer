package com.example.bottledicestimer;

import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityTimer extends AppCompatActivity {

    //timer variables
    private Button start ;
    private Button clear;
    private TextView txtMin;
    private TextView txtSec;
    private long min;
    private long sec;
    //Pradiniam laiko nustatymui
    private int settMin;
    private int settSec;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_timer);

        buttonClick();
    }

    void buttonClick(){
        //timer variables
        start = (Button) findViewById(R.id.buttonStart);
        clear = (Button) findViewById(R.id.buttonRefresh);
        txtMin = (TextView) findViewById(R.id.TextNumberMin);
        txtSec = (TextView) findViewById(R.id.TextNumberSec);

        // sistemos notificationas pasibaigus timeriui
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), notification);

        // Sufokusavus pradingsta reiksmes

        txtMin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Clear the value of the input field
                    txtMin.setText("");
                }
            }
        });
        // Sufokusavus pradingsta reiksmes
        txtSec.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Clear the value of the input field
                    txtSec.setText("");
                }
            }
        });




        start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // button.setEnabled(false);
                        Handler handler = new Handler();

                       // handler.postDelayed(()->start.setEnabled(true), 500);

                        clear.setEnabled(true);

                        // Prevencija nuo null reiksmiu
                        if(txtMin.getText().toString().equals("")){txtMin.setText(String.valueOf(0));};
                        if(txtSec.getText().toString().equals("")){txtSec.setText(String.valueOf("00"));};
                        // Prevencija nuo per dieliu reiksmiu
                        if(Integer.parseInt(txtMin.getText().toString())>9){txtMin.setText(String.valueOf(0));};
                        if(Integer.parseInt(txtSec.getText().toString())>59){txtSec.setText(String.valueOf("00"));};

                        //Numatytosios reiksmes
                        settMin = Integer.parseInt(txtMin.getText().toString());
                        settSec = Integer.parseInt(txtSec.getText().toString());



                        min = settMin;
                        sec = settSec;

                        long tmp = (min * 60) + sec;



                        //Timeris
                        //https://www.geeksforgeeks.org/countdowntimer-in-android-with-example/

                        countDownTimer = new CountDownTimer((tmp * 1000), 1000) {
                            public void onTick(long millisUntilFinished) {

                                min = (millisUntilFinished / 60000) % 60;
                                sec = (millisUntilFinished / 1000) % 60;

                                txtMin.setText(String.valueOf(min));
                                if(sec < 10){
                                    txtSec.setText(String.valueOf("0"+sec));
                                }
                                else{
                                    txtSec.setText(String.valueOf(sec));
                                }

                                txtMin.setFocusable(false);
                                txtSec.setFocusable(false);

                            }
                            // When the task is over it will print 00:00:00 there
                            public void onFinish() {
                                txtMin.setText(String.valueOf("0"));
                                txtSec.setText(String.valueOf("00"));

                                // sistemos notificationas pasibaigus timeriui


                                mp.start();
                            }
                        }.start();

                        start.setEnabled(false);
                        txtMin.setFocusableInTouchMode(true);
                        txtSec.setFocusableInTouchMode(true);

                    }
                }
        );

        clear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // button.setEnabled(false);
                        Handler handler = new Handler();
                        handler.postDelayed(()->clear.setEnabled(true), 500);


                        countDownTimer.cancel();
                        txtMin.setText(String.valueOf(settMin));


                        min = settMin;
                        sec = settSec;

                        if(sec<10){
                            txtSec.setText(String.valueOf("0" + settSec));
                        }else{
                            txtSec.setText(String.valueOf(settSec));
                        }

                        txtMin.setFocusableInTouchMode(true);
                        txtSec.setFocusableInTouchMode(true);
                        start.setEnabled(true);

                        //TODO: pataisyti , kad stabdytu, o ne pauze darytu
                        mp.pause();

                        //mp.release();




                    }
                }
        );
    }
}