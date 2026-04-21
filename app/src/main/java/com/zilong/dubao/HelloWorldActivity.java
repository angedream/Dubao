package com.zilong.dubao;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HelloWorldActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        Intent intent=getIntent();
        Fish fish=(Fish) intent.getSerializableExtra("fish");
        TextView textViewName=findViewById(R.id.fish_name);
        textViewName.setText("名称:"+fish.name);
        TextView textViewNum=findViewById(R.id.fish_num);
        textViewNum.setText("数量:"+fish.num);
        TextView textViewPrice=findViewById(R.id.fish_price);
        textViewPrice.setText("价格:"+fish.price);
    }
}