package com.lfy.lfy202120201124.entity;

import com.lfy.lfy202120201124.R;

import java.util.ArrayList;
import java.util.List;

public class DataService {
    //模拟数据
    public static List<ProductInfo> getListData(int position){
        List<ProductInfo> list = new ArrayList<>();
        if (position == 0){
            list.add(new ProductInfo(0, R.mipmap.dt990pro,"xx2","111",221));
            list.add(new ProductInfo(1, R.mipmap.dt990pro,"x2x","1121",2112));
        } else if (position==1) {
            list.add(new ProductInfo(2, R.mipmap.dt990pro,"x2x","1121",2112));
        } else if (position==2) {
            list.add(new ProductInfo(3, R.mipmap.dt990pro,"x2x","1121",2112));
        }else if (position ==3){
            list.add(new ProductInfo(4, R.mipmap.dt990pro,"x2x","1121",2112));
        }else {
            list.add(new ProductInfo(5, R.mipmap.dt990pro,"x2x","1121",2112));
        }
        return list;
    }
}
