package com.lfy.lfy202120201124;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lfy.lfy202120201124.db.CarDbHelper;
import com.lfy.lfy202120201124.entity.ProductInfo;

public class ProductDetailsActivity extends AppCompatActivity {
    private ImageView product_img;
    private TextView product_title;
    private TextView product_price;
    private TextView product_details;
    private ProductInfo productInfo;
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
        product_title = findViewById(R.id.product_title);
        product_price = findViewById(R.id.product_price);
        product_details = findViewById(R.id.product_details);

        //设置数据
        if (null!=productInfo){
            product_img.setImageResource(productInfo.getProduct_img());
            product_title.setText(productInfo.getProduct_title());
            product_details.setText(productInfo.getProduct_details());
            product_price.setText(productInfo.getProduct_price()+"");
        }

        //加入购物车
        findViewById(R.id.addCar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加入到购物车
               int row =  CarDbHelper.getInstance(ProductDetailsActivity.this).addCar("dq",productInfo.getProduct_id(),productInfo.getProduct_img(),productInfo.getProduct_title(),productInfo.getProduct_price());
                if (row>0){
                    Toast.makeText(ProductDetailsActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ProductDetailsActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}