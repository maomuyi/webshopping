package com.lfy.lfy202120201124.db;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavoriteDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorite_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "favorites";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRODUCT_ID = "product_id";

    private static FavoriteDbHelper instance;

    public static synchronized FavoriteDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new FavoriteDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    private FavoriteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCT_ID + " INTEGER UNIQUE)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean isFavorite(int productId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_PRODUCT_ID}, COLUMN_PRODUCT_ID + "=?", new String[]{String.valueOf(productId)}, null, null, null);
        boolean isFavorite = cursor.getCount() > 0;
        cursor.close();
        return isFavorite;
    }

    public void setFavorite(int productId, boolean isFavorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (isFavorite) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_PRODUCT_ID, productId);
            db.insert(TABLE_NAME, null, values);
        } else {
            db.delete(TABLE_NAME, COLUMN_PRODUCT_ID + "=?", new String[]{String.valueOf(productId)});
        }
    }
}

