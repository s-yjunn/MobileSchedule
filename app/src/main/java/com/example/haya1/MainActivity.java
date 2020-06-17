package com.example.haya1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private TextView TimeLeftDisplay;
    private TextView ExpDisplay;
    private TextView CoinDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimeLeftDisplay = (TextView)findViewById(R.id.TimeLeft);
        ExpDisplay = (TextView)findViewById(R.id.ExpDisplay);
        CoinDisplay = (TextView)findViewById(R.id.CoinDisplay);

//        Device.StartTimer(TimeSpan.FromSeconds(1), ontimerTick);
//        Button btn_NewTimerPopup = (Button)findViewById(R.id.btn_NewTimerPopup);
//        btn_NewTimerPopup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), NewTimerPopup.class);
//                startActivity(i, ActivityOptions.makeSceneTransitionAnimation(NewTimerPopup).toBundle());
//            }
//        });
    }

    public void btn_NewTimerPopup(View view)
    {
        Intent i = new Intent(getApplicationContext(), NewTimerPopup.class);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
        {
            if (TimeData.timerEnabled){
                startTimer();
            }
        }
    }


    public void startTimer()
    {
        final long timeMillis = TimeData.timerHour * 3600000 + TimeData.timerMinute * 60000 + TimeData.timerSecond * 1000;
        CountDownTimer timer = new CountDownTimer(timeMillis, 1000) {
            @Override
            public void onTick(long l) {
                int hour = (int)l / 3600000;
                int minute = (int)(l % 3600000) / 60000;
                int second = (int)(l - hour * 3600000 - minute * 60000) / 1000;
                String display = "Time left: " + String.format("%02d:%02d:%02d", hour, minute, second);
                TimeLeftDisplay.setText(display);
                ExpAndCoinData.currentExp += 1;
                ExpDisplay.setText("Current EXP: " + ExpAndCoinData.currentExp);
            }

            @Override
            public void onFinish() {
                TimeData.timerEnabled = false;
                TimeLeftDisplay.setText("No task right now.");
                ExpAndCoinData.currentCoins += timeMillis / 10000;
                CoinDisplay.setText("Current Coins: " + ExpAndCoinData.currentCoins);
                timeUpAlert();
            }
        }.start();
    }

    private void timeUpAlert()
    {
        new AlertDialog.Builder(this)
                .setTitle("Well Done!")
                .setMessage(TimeData.reminder)
                .setCancelable(false)
                .setPositiveButton("OK!",null)
                .show();
    }

//}

}