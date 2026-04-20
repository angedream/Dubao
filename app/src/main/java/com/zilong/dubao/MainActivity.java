package com.zilong.dubao;

import androidx.appcompat.app.AppCompatActivity;

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
        Log.d("MainActivity","onCreate被调用");

        Button btn = findViewById(R.id.btn_change);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击事件
                ImageView imageView =findViewById(R.id.img);
                imageView.setImageResource(R.drawable.dubao2);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity","onStart被调用");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity","onResume被调用");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","onPause被调用");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","onStop被调用");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","onDestroy被调用");

    }
}