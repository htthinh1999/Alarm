package com.keycodemon.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements AlarmPickerDialog.AlarmPickerListener {

    ListView lvAlarmTime;
    ArrayList<AlarmTimeItem> listAlarmTimeItem;
    AlarmManager alarmManager;
    Intent intent;
    PendingIntent pendingIntent;

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

        Init();
    }

    void Init(){

        lvAlarmTime = findViewById(R.id.lvAlarmTime);
        lvAlarmTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openAlarmPickerDialog();
            }
        });
        listAlarmTimeItem = new ArrayList<AlarmTimeItem>();

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        intent = new Intent(getApplicationContext(), AlarmReceiver.class);
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
    public void AddAlarmTime(String hour, String minute, Integer id) {
        AlarmTimeItem alarmTimeItem = new AlarmTimeItem(hour + ":" + minute, true);

        if(id==null){
            listAlarmTimeItem.add(alarmTimeItem);
        }else{
            listAlarmTimeItem.set(id, alarmTimeItem);
        }
        Collections.sort(listAlarmTimeItem);
        AlarmTimeAdapter alarmTimeAdapter = new AlarmTimeAdapter(this, listAlarmTimeItem);
        lvAlarmTime.setAdapter(alarmTimeAdapter);

        Calendar timeToAlarm = Calendar.getInstance();
        timeToAlarm.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        timeToAlarm.set(Calendar.MINUTE, Integer.parseInt(minute));
        timeToAlarm.set(Calendar.SECOND, 0);
        if(timeToAlarm.get(Calendar.HOUR_OF_DAY) < Calendar.getInstance().get(Calendar.HOUR_OF_DAY)){
            timeToAlarm.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH + 1);
        }

        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, timeToAlarm.getTimeInMillis(), pendingIntent);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, timeToAlarm.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }

}