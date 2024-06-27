package com.lfy.lfy202120201124.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lfy.lfy202120201124.R;
import com.lfy.lfy202120201124.entity.ProductInfo;

import java.util.ArrayList;
import java.util.List;

public class RightListAdapter extends RecyclerView.Adapter<RightListAdapter.MyHolder> {
    private List<ProductInfo> productInfos = new ArrayList<>();
    public void setListData(List<ProductInfo> list){
        this.productInfos=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_list_item,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //绑定数据
        ProductInfo productInfo = productInfos.get(position);

        holder.product_img.setImageResource(productInfo.getProduct_img());
        holder.product_title.setText(productInfo.getProduct_title());
        holder.product_details.setText(productInfo.getProduct_details());
        holder.product_price.setText(productInfo.getProduct_price()+"");
    }

    @Override
    public int getItemCount() {
        return productInfos.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        ImageView product_img;
        TextView product_title;
        TextView product_details;
        TextView product_price;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            product_img = itemView.findViewById(R.id.product_img);
            product_title = itemView.findViewById(R.id.product_title);
            product_details = itemView.findViewById(R.id.product_details);
            product_price = itemView.findViewById(R.id.product_price);
        }
    }
}
