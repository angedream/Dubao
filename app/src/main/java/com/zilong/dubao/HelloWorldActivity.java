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
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }
        String[] fishs = bundle.getStringArray("fish");
        int fish_num = bundle.getInt("fish_num");
        double fish_price=bundle.getDouble("fish_price");
        String fish_name="";
        for (int i = 0; i < fishs.length; i++) {
            fish_name+=fishs[i];
        }
        TextView textViewName=findViewById(R.id.fish_name);
        textViewName.setText("名称:"+(fish_name==null?"未知":fish_name));
        TextView textViewNum=findViewById(R.id.fish_num);
        textViewNum.setText("数量:"+fish_num);
        TextView textViewPrice=findViewById(R.id.fish_price);
        textViewPrice.setText("价格:"+fish_price);
    }
}