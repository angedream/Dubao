package com.zilong.dubao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBtn();
        createNotificationChannel();
//        startService();
        ImageView qrCodeImageView = findViewById(R.id.qrCode);
        uuid u=new uuid();
        String dubaoId=u.getuuid(this);
        Log.d("uuid",dubaoId);
        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        int foreground = Color.parseColor("#2196F3"); // 前景蓝
        int background = Color.YELLOW; // 背景白
        Bitmap qrBitmap = QRCodeUtil.ShowQRCode(
                dubaoId, logoBitmap, 0.2f, foreground, background);
        qrCodeImageView.setImageBitmap(qrBitmap);
    }

    private void startService(){
        Intent i = new Intent(this, MyService.class);
        startForegroundService(i);
    }


    private void initBtn(){
        Button button =findViewById(R.id.sendmsg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
    }
    private void createNotificationChannel() {
        NotificationManager manager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(
                "DUBAO",
                "嘟宝安心守护孩子安全",
                NotificationManager.IMPORTANCE_DEFAULT
        );
        manager.createNotificationChannel(channel);
    }

    int id=10;
    private void sendNotification() {

        // 创建点击通知的 Intent
        Intent intent = new Intent(this, MessageActivity.class);
        intent.putExtra("name","嘟妈id号"+id);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, id, intent,
                PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "DUBAO")
                .setSmallIcon(R.drawable.logo)
                .setContentIntent(pendingIntent)
                .setContentTitle("新消息提醒")
                .setContentText("您收到一条新消息"+id);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(id, builder.build());
        id++;
    }


}