package com.lfy.lfy202120201124.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lfy.lfy202120201124.ProductDetailsActivity;
import com.lfy.lfy202120201124.R;
import com.lfy.lfy202120201124.entity.ProductInfo;

import java.util.List;

public class FavoriteAdapter extends BaseAdapter {
    private Context context;
    private List<ProductInfo> productList;

    public FavoriteAdapter(Context context, List<ProductInfo> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false);
        }

        ImageView productImg = convertView.findViewById(R.id.product_img);
        TextView productTitle = convertView.findViewById(R.id.product_title);
        TextView productPrice = convertView.findViewById(R.id.product_price);

        ProductInfo product = productList.get(position);

        productImg.setImageResource(product.getProduct_img());
        productTitle.setText(product.getProduct_title());
        productPrice.setText(String.valueOf(product.getProduct_price()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("productInfo", product);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
