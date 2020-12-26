package com.example.haya1;

public class Task {

    private String reminder;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;

    public Task(String reminder, int startHour, int startMinute, int endHour, int endMinute){
        this.reminder = reminder;
        this.startHour = startHour;
        this.endHour = endHour;
        this.startMinute = startMinute;
        this.endMinute = endMinute;
    }

    public String getReminder(){
        return reminder;
    }

    public void setReminder(String newReminder){
        reminder = newReminder;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }
}
