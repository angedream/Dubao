package com.zilong.dubao;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startbtn=findViewById(R.id.sendbtn);
        startbtn.setOnClickListener(v->{
           uuid u=new uuid();
           String s= u.getuuid(this);
           Toast.makeText(this,s,Toast.LENGTH_SHORT).show();

        });

    }

}