package com.lfy.lfy202120201124.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lfy.lfy202120201124.entity.ProductInfo;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorite_db";
    private static final int DATABASE_VERSION = 2;  // 更新版本号
    private static final String TABLE_NAME = "favorites";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRODUCT_ID = "product_id";
    private static final String COLUMN_PRODUCT_IMG = "product_img";
    private static final String COLUMN_PRODUCT_TITLE = "product_title";
    private static final String COLUMN_PRODUCT_PRICE = "product_price";

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
                COLUMN_PRODUCT_ID + " INTEGER UNIQUE, " +
                COLUMN_PRODUCT_IMG + " INTEGER, " +
                COLUMN_PRODUCT_TITLE + " TEXT, " +
                COLUMN_PRODUCT_PRICE + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_PRODUCT_IMG + " INTEGER");
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_PRODUCT_TITLE + " TEXT");
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_PRODUCT_PRICE + " INTEGER");
        }
    }

    public boolean isFavorite(int productId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_PRODUCT_ID}, COLUMN_PRODUCT_ID + "=?", new String[]{String.valueOf(productId)}, null, null, null);
        boolean isFavorite = cursor.getCount() > 0;
        cursor.close();
        return isFavorite;
    }

    public void setFavorite(int productId, boolean isFavorite, int productImg, String productTitle, int productPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (isFavorite) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_PRODUCT_ID, productId);
            values.put(COLUMN_PRODUCT_IMG, productImg);
            values.put(COLUMN_PRODUCT_TITLE, productTitle);
            values.put(COLUMN_PRODUCT_PRICE, productPrice);
            db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        } else {
            db.delete(TABLE_NAME, COLUMN_PRODUCT_ID + "=?", new String[]{String.valueOf(productId)});
        }
    }

    public List<ProductInfo> getFavoriteProducts() {
        List<ProductInfo> favoriteProducts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int productIdIndex = cursor.getColumnIndex(COLUMN_PRODUCT_ID);
            int productImgIndex = cursor.getColumnIndex(COLUMN_PRODUCT_IMG);
            int productTitleIndex = cursor.getColumnIndex(COLUMN_PRODUCT_TITLE);
            int productPriceIndex = cursor.getColumnIndex(COLUMN_PRODUCT_PRICE);

            do {
                int productId = cursor.getInt(productIdIndex);
                int productImg = cursor.getInt(productImgIndex);
                String productTitle = cursor.getString(productTitleIndex);
                int productPrice = cursor.getInt(productPriceIndex);

                ProductInfo productInfo = new ProductInfo(productId, productImg, productTitle, null, productPrice);
                favoriteProducts.add(productInfo);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return favoriteProducts;
    }
}
