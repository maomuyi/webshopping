package com.lfy.lfy202120201124.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CarDbHelper extends SQLiteOpenHelper {
    private static CarDbHelper sHelper;
    private static final String DB_NAME = "car.db";   //数据库名
    private static final int VERSION = 1;    //版本号

    //必须实现其中一个构方法
    public CarDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static CarDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new CarDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建car_table表
        db.execSQL("create table car_table(_id integer primary key autoincrement, " +
                "username text," +
                "product_id text," +
                "product_img integer," +
                "product_title text," +
                "product_price integer" +
                ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    //TODO 在这里根据自己的业务需求，编写增删改查的方法，如下所示 作者：浩宇软件开发 https://www.bilibili.com/read/cv24747762/ 出处：bilibili
}
