package com.lfy.lfy202120201124.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lfy.lfy202120201124.R;

import java.util.ArrayList;
import java.util.List;

public class LeftListAdapter extends RecyclerView.Adapter<LeftListAdapter.MyHolder> {

    private List<String> datalist = new ArrayList<>();
    private int currentIndex=0;

    public LeftListAdapter(List<String> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override

    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_list_item, null);
        return new MyHolder(view);
    }

    @Override
    @SuppressLint("RecyclerView")
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //绑定数据
        String name = datalist.get(position);
        holder.tv_name.setText(name);
        //分类的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=leftListOnClickItemLietener){
                    leftListOnClickItemLietener.OnItemClick(position);
                }
            }
        });
        //设置点击颜色变化
        if (currentIndex ==position){
            holder.itemView.setBackgroundResource(R.drawable.type_selecotr_bg);
        }else {
            holder.itemView.setBackgroundResource(R.drawable.type_selector_normal_bg);
        }
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        public MyHolder(@NonNull View itemView){
            super(itemView);
            tv_name = itemView.findViewById(R.id.name);
        }
    }
    private LeftListOnClickItemLietener leftListOnClickItemLietener;


    public void setLeftListOnClickItemLietener(LeftListOnClickItemLietener leftListOnClickItemLietener) {
        this.leftListOnClickItemLietener = leftListOnClickItemLietener;
    }

    //点击事件
    public interface LeftListOnClickItemLietener{
        void OnItemClick(int position);

    }
    public void setCurrentIndex(int position){
        this.currentIndex=position;
        notifyDataSetChanged();
    }
}
