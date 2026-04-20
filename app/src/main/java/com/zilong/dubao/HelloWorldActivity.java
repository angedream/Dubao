package com.zilong.dubao;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelloWorldActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        Intent intent=getIntent();
        String fish_name=intent.getStringExtra("fish_nam1e");
        int fish_num=intent.getIntExtra("fish_num",0);
        double fish_price=intent.getDoubleExtra("fish_price",0.0);
        TextView textViewName=findViewById(R.id.fish_name);
        textViewName.setText("名称:"+(fish_name==null?"未知":fish_name));
        TextView textViewNum=findViewById(R.id.fish_num);
        textViewNum.setText("数量:"+fish_num);
        TextView textViewPrice=findViewById(R.id.fish_price);
        textViewPrice.setText("价格:"+fish_price);
    }
}