package com.example.haya1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.FileOutputStream;
import java.io.IOException;

public class AddTaskToCalendar extends AppCompatActivity {

    TimePicker StartTime;
    TimePicker EndTime;
    EditText Reminder;

    public final String EventList = "EventList";
    public static final String CalenderInfo = "CalendarInfo";

    SharedPreferences events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_to_calendar);

//        events = getPreferences(EventList, )

        StartTime = (TimePicker)findViewById(R.id.StartTime);
        EndTime = (TimePicker)findViewById(R.id.EndTime);
        Reminder = (EditText) findViewById(R.id.editTextReminder);
    }

    public void btn_addTaskListener(View view){
        int startHour = StartTime.getHour();
        int startMinute = StartTime.getMinute();
        int endHour = EndTime.getHour();
        int endMinute = EndTime.getMinute();
        String reminder = Reminder.getText().toString();

        String fileContents = "Reminder: " + reminder +
                "\nStart Time: " + startHour + ":" + startMinute +
                "\nEnd Time: " + endHour + ":" + endMinute + "\n";
        try (FileOutputStream fos = openFileOutput(CalenderInfo, Context.MODE_PRIVATE)) {
            fos.write(fileContents.getBytes());
        }
        catch (IOException e){
            System.out.println("IO error");
        }
        finish();
    }

    public void closeWindow(View view)
    {
        finish();
    }
}