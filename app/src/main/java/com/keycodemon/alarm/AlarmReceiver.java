package com.keycodemon.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Hello", "Xin chao");
        Intent serviceIntent = new Intent(context, Music.class);
        context.startService(serviceIntent);
    }
}
