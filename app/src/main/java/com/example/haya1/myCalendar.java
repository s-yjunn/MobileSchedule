package com.example.haya1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class myCalendar extends AppCompatActivity {

    private TextView currentDateDisplay;
    private TextView LongTask1;
    private TextView LongTask2;
    private TextView LongTask3;
    private TextView LongTask4;
    private TextView LongTask5;

    public static final String CalenderInfo = "CalendarInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calendar);

        currentDateDisplay = (TextView)findViewById(R.id.CurrentDayDisplay);
        SimpleDateFormat  format = new SimpleDateFormat("EEE");
        currentDateDisplay.setText(format.format(Calendar.getInstance().getTime()));

        LongTask1 = (TextView)findViewById(R.id.LongTask1);
        LongTask2 = (TextView)findViewById(R.id.LongTask2);
        LongTask3 = (TextView)findViewById(R.id.LongTask3);
        LongTask4 = (TextView)findViewById(R.id.LongTask4);
        LongTask5 = (TextView)findViewById(R.id.LongTask5);

        FileInputStream fis = null;
        try {
            fis = openFileInput(CalenderInfo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (!line.equals("\n")) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            String contents = stringBuilder.toString();
            LongTask1.setText(contents);
        }


    }

    public void btn_NewTaskPopup(View view)
    {
        Intent i = new Intent(getApplicationContext(), AddTaskToCalendar.class);
        startActivityForResult(i, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}