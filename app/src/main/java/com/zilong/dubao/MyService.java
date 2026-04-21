package com.zilong.dubao;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    public MyService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"后台服务onCreate",Toast.LENGTH_SHORT).show();
        createNotificationChannel();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"后台服务onStartCommand",Toast.LENGTH_SHORT).show();
        startForeground(10001, createNotification());
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {

        Toast.makeText(this,"后台服务onDestroy",Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    private void createNotificationChannel() {
        // Android 8.0+ 需要创建通知渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "DUBAO",
                    "嘟宝安心守护孩子安全",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
    private Notification createNotification() {
        // 点击通知返回应用
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        return new NotificationCompat.Builder(this, "DUBAO")
                .setContentTitle("嘟宝")
                .setContentText("嘟宝安心守护孩子安全...")
                .setSmallIcon(android.R.drawable.ic_menu_info_details)
                .setContentIntent(pendingIntent)
                .setOngoing(true)  // 不可滑动删除
                .build();
    }
}