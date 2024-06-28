package com.lfy.lfy202120201124.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lfy.lfy202120201124.R;
import com.lfy.lfy202120201124.adapter.CarListAdapter;
import com.lfy.lfy202120201124.db.CarDbHelper;
import com.lfy.lfy202120201124.entity.CarInfo;

import java.util.List;


public class CarFragment extends Fragment {
    private View rootView;
    private RecyclerView recyclerView;
    private CarListAdapter carListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         rootView= inflater.inflate(R.layout.fragment_car, container, false);
         //初始化控件
        recyclerView = rootView.findViewById(R.id.recyclerView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化carListAdapter
        carListAdapter = new CarListAdapter();
        //设置适配器
        recyclerView.setAdapter(carListAdapter);
        //recyclerView点击事件
        carListAdapter.setOnItemClickListener(new CarListAdapter.onItemClickListener() {
            @Override
            public void onPlusOnClick(CarInfo carInfo, int position) {
                //加
                CarDbHelper.getInstance(getActivity()).updateProduct(carInfo.getCar_id(),carInfo);
                loadData();
            }

            @Override
            public void onSubTractOnClick(CarInfo carInfo, int position) {
                //减
                CarDbHelper.getInstance(getActivity()).subStartUpdateProduct(carInfo.getCar_id(),carInfo);
                loadData();
            }
        });

        loadData();
    }
    public  void  loadData(){
        //获取数据
        List<CarInfo> carInfoList = CarDbHelper.getInstance(getActivity()).queryCarList("dq");
        //设置数据
        carListAdapter.setCarInfoList(carInfoList);
    }
}