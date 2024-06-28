package com.lfy.lfy202120201124.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.lfy.lfy202120201124.entity.CarInfo;

import java.util.List;

public class OrderDbHelper extends SQLiteOpenHelper {
    private static OrderDbHelper sHelper;
    private static final String DB_NAME = "order.db";   //数据库名
    private static final int VERSION = 1;    //版本号

    //必须实现其中一个构方法
    public OrderDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static OrderDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new OrderDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建order_table表
        db.execSQL("create table order_table(order_id integer primary key autoincrement, " +
                "username text," +
                "product_img integer," +
                "product_title text," +
                "product_price integer," +
                "product_count integer," +
                "address text," +
                "phone text" +
                ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    //批量插入数据
    public void insertByAll(List<CarInfo> list, String address, String phone) {
        //获取数据库实例
        SQLiteDatabase db = getWritableDatabase();
        //开始事务
        db.beginTransaction();
        try {
            for (int i = 0; i < list.size(); i++) {
                ContentValues values = new ContentValues();
                values.put("username", list.get(i).getUsername());
                values.put("product_img", list.get(i).getProduct_img());
                values.put("product_title", list.get(i).getProduct_title());
                values.put("product_price", list.get(i).getProduct_price());
                values.put("product_count", list.get(i).getProduct_count());
                values.put("address", address);
                values.put("phone", phone);
                db.insert("order_table", null, values);

            }
            //标记事物成功
            db.setTransactionSuccessful();
        } finally {
            //结束事务
            db.endTransaction();
        }

        //关闭数据库
        db.close();

    }
}
