package com.keycodemon.alarm;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements AlarmPickerDialog.AlarmPickerListener {

    ListView lvAlarmTime;
    ArrayList<AlarmTimeItem> listAlarmTimeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlarmPickerDialog();
            }
        });

        lvAlarmTime = findViewById(R.id.lvAlarmTime);
        listAlarmTimeItem = new ArrayList<AlarmTimeItem>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.changeRingtone:

                break;
        }
        return true;
    }

    public void openAlarmPickerDialog(){
        AlarmPickerDialog alarmPickerDialog = new AlarmPickerDialog();
        alarmPickerDialog.show(getSupportFragmentManager(), "Alarm picker");
    }

    @Override
    public void AddAlarmTime(String hour, String minute) {
        AlarmTimeItem alarmTimeItem = new AlarmTimeItem(hour+":"+minute, "T2, T3, T4, T5, T6, T7, CN", true);

        listAlarmTimeItem.add(alarmTimeItem);
        Collections.sort(listAlarmTimeItem);
        AlarmTimeAdapter alarmTimeAdapter = new AlarmTimeAdapter(this, listAlarmTimeItem);
        lvAlarmTime.setAdapter(alarmTimeAdapter);
    }

}