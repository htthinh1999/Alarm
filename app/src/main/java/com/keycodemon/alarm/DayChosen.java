package com.keycodemon.alarm;

public class DayChosen{
    public int Position;
    public AlarmTimeItem AlarmTimeItem;
    public DayChosen(int position, AlarmTimeItem alarmTimeItem){
        Position = position;
        AlarmTimeItem = alarmTimeItem;
    }

    public void enable(){
        AlarmTimeItem.DaysChose[Position] = true;
    }

    public void disable(){
        AlarmTimeItem.DaysChose[Position] = false;
    }

    public boolean getValue(){
        return AlarmTimeItem.DaysChose[Position];
    }
}