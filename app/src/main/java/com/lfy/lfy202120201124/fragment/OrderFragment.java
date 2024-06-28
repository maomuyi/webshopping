package com.lfy.lfy202120201124.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lfy.lfy202120201124.R;
import com.lfy.lfy202120201124.adapter.OrderListAdapter;
import com.lfy.lfy202120201124.db.OrderDbHelper;
import com.lfy.lfy202120201124.entity.OrderInfo;
import com.lfy.lfy202120201124.entity.UserInfo;

import java.util.List;


public class OrderFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private OrderListAdapter orderListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_order, container, false);
        //初始化控件
        recyclerView = rootView.findViewById(R.id.recyclerView);
        return rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        //初始化m0rderListAdapter
        orderListAdapter =new OrderListAdapter();
        //设置0rderListAdapter
        recyclerView.setAdapter(orderListAdapter);
        //获取数据
        UserInfo userInfo = UserInfo.getUserInfo();
        if (null!=userInfo){
            List<OrderInfo> orderInfos = OrderDbHelper.getInstance(getActivity()).queryOrderListData(userInfo.getUsername());
            orderListAdapter.setListData(orderInfos);
        }
    }
}