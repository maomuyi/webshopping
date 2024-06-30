package com.lfy.lfy202120201124;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lfy.lfy202120201124.db.CarDbHelper;
import com.lfy.lfy202120201124.db.FavoriteDbHelper;
import com.lfy.lfy202120201124.entity.ProductInfo;
import com.lfy.lfy202120201124.entity.UserInfo;

public class ProductDetailsActivity extends AppCompatActivity {
    private ImageView product_img;
    private ImageView favorite_icon;
    private TextView product_title;
    private TextView product_price;
    private TextView product_details;
    private ProductInfo productInfo;
    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        //获取传递的数据
        productInfo = (ProductInfo) getIntent().getSerializableExtra("productInfo");

        //返回控件
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //初始化控件
        product_img = findViewById(R.id.product_img);
        favorite_icon = findViewById(R.id.favorite_icon);
        product_title = findViewById(R.id.product_title);
        product_price = findViewById(R.id.product_price);
        product_details = findViewById(R.id.product_details);

        //设置数据
        if (null != productInfo) {
            product_img.setImageResource(productInfo.getProduct_img());
            product_title.setText(productInfo.getProduct_title());
            product_details.setText(productInfo.getProduct_details());
            product_price.setText(String.valueOf(productInfo.getProduct_price()));
        }

        //检查收藏状态
        isFavorite = FavoriteDbHelper.getInstance(ProductDetailsActivity.this).isFavorite(productInfo.getProduct_id());
        updateFavoriteIcon();

        //加入购物车
        findViewById(R.id.addCar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo userInfo = UserInfo.getUserInfo();
                if (userInfo != null) {
                    new AlertDialog.Builder(ProductDetailsActivity.this)
                            .setTitle("是否加入购物车")
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //加入到购物车
                                    int row = CarDbHelper.getInstance(ProductDetailsActivity.this).addCar(userInfo.getUsername(), productInfo.getProduct_id(), productInfo.getProduct_img(), productInfo.getProduct_title(), productInfo.getProduct_price());
                                    if (row > 0) {
                                        Toast.makeText(ProductDetailsActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(ProductDetailsActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }
            }
        });

        //添加收藏点击事件
        favorite_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFavorite = !isFavorite;
                FavoriteDbHelper.getInstance(ProductDetailsActivity.this).setFavorite(
                        productInfo.getProduct_id(), isFavorite, productInfo.getProduct_img(),
                        productInfo.getProduct_title(), productInfo.getProduct_price()
                );
                updateFavoriteIcon();
                Toast.makeText(ProductDetailsActivity.this, isFavorite ? "收藏成功" : "取消收藏", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateFavoriteIcon() {
        favorite_icon.setImageResource(isFavorite ? R.drawable.ic_favorite_red : R.drawable.ic_favorite_border);
    }
}
