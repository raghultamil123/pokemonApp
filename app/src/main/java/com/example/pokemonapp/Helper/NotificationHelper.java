package com.example.pokemonapp.Helper;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationHelper {
    public static NotificationChannel createNotificationChannel(String channelId,String channelName,int importance){
        NotificationChannel notificationChannel=null;
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
        notificationChannel = new NotificationChannel(channelId,channelName,importance);

        }
        return notificationChannel;
    }
}
