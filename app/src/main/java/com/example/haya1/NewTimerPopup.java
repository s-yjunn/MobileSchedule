package com.example.haya1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;

public class NewTimerPopup extends Activity {

    private int hour;
    private int minute;
    private int second;
    private NumberPicker hourPicker;
    private NumberPicker minutePicker;
    private NumberPicker secondPicker;
    private EditText reminder;
    private Switch EnableTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_timer_popup);

        hourPicker = (NumberPicker)findViewById(R.id.hourPicker);
        minutePicker = (NumberPicker)findViewById(R.id.minutePicker);
        secondPicker = (NumberPicker)findViewById(R.id.secondPicker);
        reminder = (EditText)findViewById(R.id.editTextReminder);
        EnableTimer = (Switch)findViewById(R.id.switch_EnableTimer);

        hourPicker.setMaxValue(3);
        hourPicker.setMinValue(0);
        minutePicker.setMaxValue(59);
        minutePicker.setMinValue(0);
        secondPicker.setMaxValue(59);
        secondPicker.setMinValue(1);
    }

    public void btn_StartTimer(View view)
    {
        TimeData.timerHour = hourPicker.getValue();
        TimeData.timerMinute = minutePicker.getValue();
        TimeData.timerSecond = secondPicker.getValue();
        TimeData.reminder = reminder.getText().toString();
        TimeData.timerEnabled = EnableTimer.isChecked();
        //Intent intent = new Intent();
        setResult(2);
        finish();
    }

    public void closeWindow(View view)
    {
        finish();
    }


}
