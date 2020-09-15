package com.keycodemon.alarm;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AlarmTimeAdapter extends ArrayAdapter<AlarmTimeItem> {
    Context context;
    ArrayList<AlarmTimeItem> listAlarmItem;

    AlarmTimeAdapter(Context context, ArrayList<AlarmTimeItem> listAlarmItem){
        super(context, R.layout.alarm_time, R.id.time, listAlarmItem);
        this.context = context;
        this.listAlarmItem = listAlarmItem;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.alarm_time, parent, false);

        TextView time = row.findViewById(R.id.time);
        Switch toggle = row.findViewById(R.id.switchEnable);

        Button[] buttons = {row.findViewById(R.id.btnT2), row.findViewById(R.id.btnT3), row.findViewById(R.id.btnT4), row.findViewById(R.id.btnT5),
                row.findViewById(R.id.btnT6), row.findViewById(R.id.btnT7), row.findViewById(R.id.btnCN)};
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setTag(new DayChosen(i, listAlarmItem.get(position)));
            setButtonGraphic(buttons[i]);
            if (!buttons[i].hasOnClickListeners()) {
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button dayButton = (Button) view;
                        changeButtonGraphic(dayButton);
                    }
                });
            }
        }

        time.setText(listAlarmItem.get(position).Time);
        toggle.setChecked(listAlarmItem.get(position).Enable);

        return row;
    }

    void setButtonGraphic(Button dayButton){
        DayChosen dayChosen = (DayChosen) dayButton.getTag();
        if(dayChosen.getValue()){
            dayButton.setBackground(context.getDrawable(R.drawable.button_circle_checked));
            dayButton.setTextColor(Color.WHITE);
        }else{
            dayButton.setBackground(context.getDrawable(R.drawable.button_circle_uncheck));
            dayButton.setTextColor(Color.parseColor("#4ebaaa"));
        }
    }

    void changeButtonGraphic(Button dayButton){
        DayChosen dayChosen = (DayChosen) dayButton.getTag();
        if(dayChosen.getValue()){
            dayButton.setBackground(context.getDrawable(R.drawable.button_circle_uncheck));
            dayButton.setTextColor(Color.parseColor("#4ebaaa"));
            dayChosen.disable();
        }else{

            dayButton.setBackground(context.getDrawable(R.drawable.button_circle_checked));
            dayButton.setTextColor(Color.WHITE);
            dayChosen.enable();
        }
    }
}