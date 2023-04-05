package com.example.bottledicestimer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class ActivityDices extends AppCompatActivity {

    private ImageView imageView1;
    private ImageView imageView2;
    private Button button;
    private TextView tekstas1;
    private TextView tekstas2;
    private TextView tekstas3;
    private TextView player;
    int renew = 0;
    private Random sk = new Random();
    int[] pics = {R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};


    private boolean playerSwitch;
    int player1PTS = 0;
    int player2PTS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dices);

        shake();
        buttonClick();
        playerSwitch = true;




    }

    private void shake(){
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor sensorShake = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent!=null){
                    float x_accl = sensorEvent.values[0];
                    float y_accl = sensorEvent.values[1];
                    float z_accl = sensorEvent.values[2];

                    if (x_accl > 12 ||
                            x_accl < -12||
                            y_accl > 12||
                            y_accl < -12||
                            z_accl > 12||
                            z_accl < -12){


                        player1PTS = 0;
                        player2PTS = 0;
                        tekstas2.setText(String.valueOf(player1PTS));
                        tekstas3.setText(String.valueOf(player2PTS));


                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        sensorManager.registerListener(sensorEventListener,sensorShake,SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void buttonClick() {
        imageView1 = (ImageView)findViewById(R.id.imageView);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        button = (Button)findViewById(R.id.button);

        tekstas2 = (TextView)findViewById(R.id.textView2);
        tekstas3 = (TextView)findViewById(R.id.textView3);
        player = findViewById(R.id.txtPlayer);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int skaicius = sk.nextInt(5);
                        int suma = skaicius + 1;
                        imageView1.animate().rotationBy(360).setDuration(1000).start();
                        imageView1.animate().rotationXBy(360).setDuration(1000).start();
                        imageView1.animate().rotationYBy(360).setDuration(1000).start();
                        imageView1.setImageResource(pics[skaicius]);



                        skaicius = sk.nextInt(5);
                        suma = suma + skaicius + 1;
                        imageView2.animate().rotationBy(-360).setDuration(1000).start();
                        imageView2.animate().rotationXBy(-360).setDuration(1000).start();
                        imageView2.animate().rotationYBy(-360).setDuration(1000).start();
                        imageView2.setImageResource(pics[skaicius]);

                        button.setEnabled(false);




                        if(playerSwitch){
                            player1PTS = player1PTS+suma;
                            player.setText("1 Žaidėjas");
                            tekstas2.setText(String.valueOf(player1PTS));

                        }
                        else{
                            player2PTS = player2PTS+suma;
                            player.setText("2 Žaidėjas");
                            tekstas3.setText(String.valueOf(player2PTS));
                        }



                        Handler handler = new Handler();
                        handler.postDelayed(()->button.setEnabled(true), 500);
                       // tekstas1.setText(String.valueOf("Išridenote: "+suma));
                        Toast.makeText(ActivityDices.this, ("Išridenote: "+suma), Toast.LENGTH_SHORT).show();

//                        tekstas2.setText(String.valueOf("1 žaidėjo taškai: - "+(skaicius+1)));
//                        tekstas3.setText(String.valueOf("2 žaidėjo taškai: - "+(skaicius+1)));

                        playerSwitch = !playerSwitch;
                    }
                }
        );
    }
}