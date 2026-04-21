package com.zilong.dubao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startbtn=findViewById(R.id.startbtn);
        startbtn.setOnClickListener(v->{
            Intent intent = new Intent(this, MyService.class);
            startForegroundService(intent);

        });
        Button stopbtn=findViewById(R.id.stopbtn);
        stopbtn.setOnClickListener(v->{
            Intent intent = new Intent(this, MyService.class);
            stopService(intent);
        });
    }

}