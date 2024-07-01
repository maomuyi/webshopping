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
    private static final int DATABASE_VERSION = 6; // 更新版本号
    private static final String TABLE_VIDEOS = "videos";
    private static final String TABLE_COMMENTS = "comments";
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_TIMESTAMP = "timestamp";
    private static final String COLUMN_LIKE_COUNT = "like_count"; // 新增点赞数量字段
    private static final String TAG = "VideoDbHelper";

    public VideoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createVideoTable = "CREATE TABLE " + TABLE_VIDEOS + " (" +
                COLUMN_URL + " TEXT PRIMARY KEY, " +
                COLUMN_LIKE_COUNT + " INTEGER DEFAULT 0)"; // 新增点赞数量字段
        db.execSQL(createVideoTable);

        String createCommentTable = "CREATE TABLE " + TABLE_COMMENTS + " (" +
                COLUMN_URL + " TEXT, " +
                COLUMN_USER_NAME + " TEXT, " +
                COLUMN_CONTENT + " TEXT, " +
                COLUMN_TIMESTAMP + " TEXT)";
        db.execSQL(createCommentTable);

        // 插入测试数据
        String packageName = "com.lfy.lfy202120201124";
        db.execSQL("INSERT INTO " + TABLE_VIDEOS + " (" + COLUMN_URL + ") VALUES ('android.resource://" + packageName + "/raw/v1')");
        db.execSQL("INSERT INTO " + TABLE_VIDEOS + " (" + COLUMN_URL + ") VALUES ('android.resource://" + packageName + "/raw/v2')");
        db.execSQL("INSERT INTO " + TABLE_VIDEOS + " (" + COLUMN_URL + ") VALUES ('android.resource://" + packageName + "/raw/v3')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 4) { // assuming the like_count column is added in version 4
            db.execSQL("ALTER TABLE " + TABLE_VIDEOS + " ADD COLUMN " + COLUMN_LIKE_COUNT + " INTEGER DEFAULT 0");
        }
    }

    public void addComment(Comment comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_URL, comment.getUrl());
        values.put(COLUMN_USER_NAME, comment.getUserName());
        values.put(COLUMN_CONTENT, comment.getContent());
        values.put(COLUMN_TIMESTAMP, comment.getTimestamp());

        db.insert(TABLE_COMMENTS, null, values);
        db.close();
    }

    public List<Comment> getCommentsByVideoUrl(String videoUrl) {
        List<Comment> comments = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_COMMENTS, new String[]{COLUMN_URL, COLUMN_USER_NAME, COLUMN_CONTENT, COLUMN_TIMESTAMP},
                    COLUMN_URL + "=?", new String[]{videoUrl}, null, null, null);

            if (cursor.moveToFirst()) {
                int urlIndex = cursor.getColumnIndexOrThrow(COLUMN_URL);
                int userNameIndex = cursor.getColumnIndexOrThrow(COLUMN_USER_NAME);
                int contentIndex = cursor.getColumnIndexOrThrow(COLUMN_CONTENT);
                int timestampIndex = cursor.getColumnIndexOrThrow(COLUMN_TIMESTAMP);

                do {
                    String url = cursor.getString(urlIndex);
                    String userName = cursor.getString(userNameIndex);
                    String content = cursor.getString(contentIndex);
                    String timestamp = cursor.getString(timestampIndex);
                    comments.add(new Comment(url, userName, content, timestamp));
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

    // 新增点赞数量的获取和更新方法
    public int getLikeCount(String videoUrl) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_VIDEOS, new String[]{COLUMN_LIKE_COUNT}, COLUMN_URL + "=?", new String[]{videoUrl}, null, null, null);
        int likeCount = 0;
        if (cursor.moveToFirst()) {
            likeCount = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_LIKE_COUNT));
        }
        cursor.close();
        db.close();
        return likeCount;
    }

    public void updateLikeCount(String videoUrl, int likeCount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LIKE_COUNT, likeCount);
        db.update(TABLE_VIDEOS, values, COLUMN_URL + "=?", new String[]{videoUrl});
        db.close();
    }
}
