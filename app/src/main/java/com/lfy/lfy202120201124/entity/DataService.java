package com.lfy.lfy202120201124.entity;

import com.lfy.lfy202120201124.R;

import java.util.ArrayList;
import java.util.List;

public class DataService {
    //模拟数据
    public static List<ProductInfo> getListData(int position){
        List<ProductInfo> list = new ArrayList<>();
        if (position == 0){
            list.add(new ProductInfo(0, R.mipmap.dt990pro,"xx","111",21));
            list.add(new ProductInfo(1, R.mipmap.dt990pro,"xx","111",21));
        } else if (position==1) {

        } else if (position==2) {

        }else if (position ==3){

        }else {

        }
        return list;
    }
}
