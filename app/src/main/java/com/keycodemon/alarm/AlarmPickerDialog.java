package com.keycodemon.alarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

public class AlarmPickerDialog extends AppCompatDialogFragment {

    TimePicker alarmPicker;
    AlarmPickerListener alarmPickerListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_alarm_picker, null);

        builder.setView(view)
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Thiết lập", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Pad left hour and minute with '0'
                        String hour = String.format("%02d", alarmPicker.getHour());
                        String minute = String.format("%02d", alarmPicker.getMinute());
                        alarmPickerListener.AddAlarmTime(hour, minute);
                    }
                });

        alarmPicker = view.findViewById(R.id.alarmPicker);
        alarmPicker.setIs24HourView(true);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            alarmPickerListener = (AlarmPickerListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement AlarmPickerListner");
        }
    }

    public interface AlarmPickerListener{
        void AddAlarmTime(String hour, String minute);
    }
}
