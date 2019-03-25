package com.example.sumayea.stop_watch;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private  boolean running;
    private int second = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!= null){
            second =savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }

        runTimer();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle savedInstance) {
        savedInstance.putInt("seconds", second);
        savedInstance.putBoolean("running",running);
    }

    public void onClickStart(View view){

        running= true;
    }

    public void onClickStop(View view){

        running=false;
    }

    public void onClickReset(View view){

        running=false;
        second = 0;
    }

    public void runTimer(){

        final TextView timeText = findViewById(R.id.timeText);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours= second/3600; //get hour value from second
                int minutes = (second%3600)/60; // get minute value from second
                int seconds = second%60; //get second value from second

                String time = String.format(Locale.getDefault(), "%d:%02d:%02d",hours,minutes,seconds);
                timeText.setText(time);

                if (running)
                {
                    second++;
                }

                handler.postDelayed(this,1000);
            }
        });


    }

}
