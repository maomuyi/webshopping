package com.lfy.lfy202120201124.db;

import android.content.ContentValues;
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
                "product_price integer," +
                "product_count integer" +
                ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //购物车添加商品
    public int addCar(String username, int product_id, int product_img, String product_title, String product_price) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //填充占位符
        values.put("username", username);
        values.put("product_id", product_id);
        values.put("product_img", product_img);
        values.put("product_title", product_title);
        values.put("product_price", product_price);
        String nullColumnHack = "values(null,?,?,?,?,?)";
        //执行
        int insert = (int) db.insert("car_table", nullColumnHack, values);
        db.close();
        return insert;
    }

    //修改购物车
    public int updatePwd(int car_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("_id", car_id);
        // 执行SQL
        int update = db.update("car_table", values, " _id=?", new String[]{car_id+""});
        // 关闭数据库连接
        db.close();
        return update;

    }
    //根据用户名和商品ID判断有没有添加过商品
}
