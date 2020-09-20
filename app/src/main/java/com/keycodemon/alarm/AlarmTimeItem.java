package com.keycodemon.alarm;

public class AlarmTimeItem implements Comparable{
    public String Time;
    public boolean Enable;
    public boolean[] DaysChose;

    public AlarmTimeItem(String time, boolean enable){
        Time = time;
        Enable = enable;
        DaysChose = new boolean[7];
    }

    @Override
    public int compareTo(Object o) {
        return Time.compareTo(((AlarmTimeItem) o).Time);
    }
}