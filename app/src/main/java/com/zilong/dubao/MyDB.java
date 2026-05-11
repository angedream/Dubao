package com.zilong.dubao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDB extends SQLiteOpenHelper {
    public MyDB( String name, SQLiteDatabase.CursorFactory factory,
                int version){
        super(app.getContext(), name, factory, version);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(app.getContext(),"数据库与表创建成功",Toast.LENGTH_SHORT).show();
        String dumaTabel="CREATE TABLE duma (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, dumaName text,dumaId text,bindDateTime INTEGER)";
        db.execSQL(dumaTabel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
