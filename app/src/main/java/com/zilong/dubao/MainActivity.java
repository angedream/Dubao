package com.zilong.dubao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HelloWorldActivity.class);
                Bundle bundle=new Bundle();
                bundle.putStringArray("fish",new String[]{"鲫鱼","草鱼","鲤鱼"});
                bundle.putInt("fish_num",111);
                bundle.putDouble("fish_price",9.9);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}