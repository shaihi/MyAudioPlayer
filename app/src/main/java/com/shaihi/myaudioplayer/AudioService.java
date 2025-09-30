package com.shaihi.myaudioplayer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class AudioService extends Service {
    final private String CHANNEL_ID = "audio_playback_channel";

    public AudioService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        createNotificationChannel();
        Notification notification = new NotificationCompat
                .Builder(this, CHANNEL_ID)
                .setContentTitle("Music Playing")
                .setContentText("Your song is playing")
                .setSmallIcon(android.R.drawable.ic_media_play)
                .build();
        startForeground(1, notification);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Media Playback Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(serviceChannel);
                Log.d("MediaService", "Notification channel '" + CHANNEL_ID + "' created with importance DEFAULT.");
            } else {
                Log.e("MediaService", "NotificationManager is null, channel not created.");
            }
        }
    }
}