package com.lfy.lfy202120201124.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lfy.lfy202120201124.entity.Comment;

import java.util.ArrayList;
import java.util.List;

public class VideoDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "videos.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_VIDEOS = "videos";
    private static final String TABLE_COMMENTS = "comments";
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_CONTENT = "content";
    private static final String TAG = "VideoDbHelper";

    public VideoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createVideoTable = "CREATE TABLE " + TABLE_VIDEOS + " (" +
                COLUMN_URL + " TEXT)";
        db.execSQL(createVideoTable);

        String createCommentTable = "CREATE TABLE " + TABLE_COMMENTS + " (" +
                COLUMN_URL + " TEXT, " +
                COLUMN_USER_NAME + " TEXT, " +
                COLUMN_CONTENT + " TEXT)";
        db.execSQL(createCommentTable);

        // 插入测试数据
        String packageName = "com.lfy.lfy202120201124";
        db.execSQL("INSERT INTO " + TABLE_VIDEOS + " (" + COLUMN_URL + ") VALUES ('android.resource://" + packageName + "/raw/v1')");
        db.execSQL("INSERT INTO " + TABLE_VIDEOS + " (" + COLUMN_URL + ") VALUES ('android.resource://" + packageName + "/raw/v2')");
        db.execSQL("INSERT INTO " + TABLE_VIDEOS + " (" + COLUMN_URL + ") VALUES ('android.resource://" + packageName + "/raw/v3')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIDEOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

    public void addComment(Comment comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_URL, comment.getVideoUrl());
        values.put(COLUMN_USER_NAME, comment.getUserName());
        values.put(COLUMN_CONTENT, comment.getContent());

        db.insert(TABLE_COMMENTS, null, values);
        db.close();
    }

    public List<Comment> getCommentsByVideoUrl(String videoUrl) {
        List<Comment> comments = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_COMMENTS, new String[]{COLUMN_URL, COLUMN_USER_NAME, COLUMN_CONTENT},
                    COLUMN_URL + "=?", new String[]{videoUrl}, null, null, null);

            if (cursor.moveToFirst()) {
                int urlIndex = cursor.getColumnIndexOrThrow(COLUMN_URL);
                int userNameIndex = cursor.getColumnIndexOrThrow(COLUMN_USER_NAME);
                int contentIndex = cursor.getColumnIndexOrThrow(COLUMN_CONTENT);

                do {
                    String url = cursor.getString(urlIndex);
                    String userName = cursor.getString(userNameIndex);
                    String content = cursor.getString(contentIndex);
                    comments.add(new Comment(url, userName, content));
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

        return comments;
    }
}
