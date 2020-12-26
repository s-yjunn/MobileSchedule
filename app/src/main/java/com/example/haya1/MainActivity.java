package com.example.haya1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private TextView TimeLeftDisplay;
    private TextView ExpDisplay;
    private TextView CoinDisplay;

    public static final String UserData = "UserData";
    public static final String UserExp = "UserExp";
    public static final String UserCoin = "UserCoin";
    SharedPreferences userData;

    public static final String CalenderInfo = "CalendarInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userData = getSharedPreferences(UserData, MODE_PRIVATE);

        File file = new File(getFilesDir(), CalenderInfo);

        TimeLeftDisplay = (TextView)findViewById(R.id.TimeLeft);
        ExpDisplay = (TextView)findViewById(R.id.ExpDisplay);
        CoinDisplay = (TextView)findViewById(R.id.CoinDisplay);

        ExpDisplay.setText("Current Exp: " + userData.getInt(UserExp, 0));
        CoinDisplay.setText("Current Coins: " + userData.getInt(UserCoin, 0));
    }

    public void btn_NewTimerPopup(View view)
    {
        Intent i = new Intent(getApplicationContext(), NewTimerPopup.class);
        startActivityForResult(i, 1);
    }

    public void btn_myCalendar(View view)
    {
        Intent i = new Intent(getApplicationContext(), myCalendar.class);
        startActivityForResult(i, 2);
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

//                ExpAndCoinData.currentExp += 1;
                SharedPreferences.Editor editor = userData.edit();
                int currentExp = userData.getInt(UserExp, 0) + 1;
                editor.putInt(UserExp, currentExp);
                editor.apply();
                ExpDisplay.setText("Current EXP: " + userData.getInt(UserExp, 0));
            }

            @Override
            public void onFinish() {
                TimeData.timerEnabled = false;
                TimeLeftDisplay.setText("No task right now.");
//                ExpAndCoinData.currentCoins += timeMillis / 10000;
                SharedPreferences.Editor editor = userData.edit();
                int currentCoin = userData.getInt(UserCoin, 0) + (int)(timeMillis / 10000);
                editor.putInt(UserCoin, currentCoin);
                editor.apply();
                CoinDisplay.setText("Current Coins: " + userData.getInt(UserCoin, 0));
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