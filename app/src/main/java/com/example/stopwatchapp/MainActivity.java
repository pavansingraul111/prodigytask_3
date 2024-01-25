package com.example.stopwatchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    int milliseconds = 0;
    boolean isRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.timerText);
        startTimer();
    }
    public void onClickStart(View view) {
       isRunning = true;
    }
    public void onClickPause(View view) {
        isRunning = false;
    }
    public void onClickReset(View view) {
       isRunning = false;
       milliseconds= 0;
    }
    public void startTimer() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                int ms = milliseconds%10;
                int s = milliseconds/10;
                long m = s/60;
                if(isRunning){
                   milliseconds++;
                }
                String formatString = String.format(Locale.getDefault(), "%02d:%02d:%02d", m, s, ms);
                textView.setText(formatString);
                handler.postDelayed(this, 50);
            }
        };
        handler.post(runnable);
    }
}