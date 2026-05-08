package com.zilong.dubao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        String dumaName=getIntent().getStringExtra("dumaName");
        String dumaId=getIntent().getStringExtra("dumaId");
        ((TextView)findViewById(R.id.name)).setText(dumaName);
        ((TextView)findViewById(R.id.dumaId)).setText(dumaId);
    }
}