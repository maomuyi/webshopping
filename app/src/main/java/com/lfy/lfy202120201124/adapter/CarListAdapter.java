package com.lfy.lfy202120201124.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lfy.lfy202120201124.R;
import com.lfy.lfy202120201124.entity.CarInfo;

import java.util.ArrayList;
import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.MyHolder> {

    private List<CarInfo> carInfoList = new ArrayList<>();
    public void setCarInfoList(List<CarInfo> list){
        this.carInfoList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_list_item,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //绑定数据
        CarInfo carInfo = carInfoList.get(position);
        holder.product_img.setImageResource(carInfo.getProduct_img());
        holder.product_title.setText(carInfo.getProduct_title());
        holder.product_price.setText(carInfo.getProduct_price()+"");
        holder.product_count.setText(carInfo.getProduct_count()+"");

    }

    @Override
    public int getItemCount() {
        return carInfoList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        ImageView product_img;
        TextView product_title;
        TextView product_price;
        TextView product_count;

        public MyHolder(@NonNull View itemView) {

            super(itemView);
            product_img = itemView.findViewById(R.id.product_img);
            product_title = itemView.findViewById(R.id.product_title);
            product_price = itemView.findViewById(R.id.product_price);
            product_count = itemView.findViewById(R.id.product_count);
        }
    }
}
