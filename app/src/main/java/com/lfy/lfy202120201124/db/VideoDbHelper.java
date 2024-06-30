package com.lfy.lfy202120201124.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lfy.lfy202120201124.entity.Video;

import java.util.ArrayList;
import java.util.List;

public class VideoDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "videos.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "videos";
    private static final String COLUMN_URL = "url";
    private static final String TAG = "VideoDbHelper";

    public VideoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_URL + " TEXT)";
        db.execSQL(createTable);

        // 插入测试数据
        String packageName = "com.lfy.lfy202120201124";
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_URL + ") VALUES ('android.resource://" + packageName + "/raw/v1')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_URL + ") VALUES ('android.resource://" + packageName + "/raw/v2')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_URL + ") VALUES ('android.resource://" + packageName + "/raw/v3')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<Video> getAllVideos() {
        List<Video> videoList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_NAME, new String[]{COLUMN_URL}, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                int urlIndex = cursor.getColumnIndexOrThrow(COLUMN_URL);
                do {
                    String url = cursor.getString(urlIndex);
                    videoList.add(new Video(url));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error reading from database", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return videoList;
    }
}
