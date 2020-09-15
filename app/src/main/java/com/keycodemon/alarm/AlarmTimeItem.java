package com.keycodemon.alarm;

public class AlarmTimeItem implements Comparable{
    public String Time;
    public String Days;
    public boolean Enable;
    public boolean[] DaysChose;

    public AlarmTimeItem(String time, String days, boolean enable){
        this.Time = time;
        this.Days = days;
        this.Enable = enable;
        DaysChose = new boolean[7];
    }

    @Override
    public int compareTo(Object o) {
        return Time.compareTo(((AlarmTimeItem) o).Time);
    }
}