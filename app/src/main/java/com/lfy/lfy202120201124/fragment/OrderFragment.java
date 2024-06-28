package com.lfy.lfy202120201124.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        //初始化orderListAdapter
        orderListAdapter =new OrderListAdapter();
        // 设置LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置orderListAdapter
        recyclerView.setAdapter(orderListAdapter);
        //orderListAdapter点击事件
        orderListAdapter.setOnItemClickListener(new OrderListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(OrderInfo orderInfo, int position) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("温馨提示")
                        .setMessage("")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int row = OrderDbHelper.getInstance(getActivity()).delete(orderInfo.getOrder_id() + "");
                                if (row>0){
                                    loadData();
                                    Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getActivity(), "删除失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();
            }
        });

        //获取数据
        loadData();

    }

    public void loadData(){
        UserInfo userInfo = UserInfo.getUserInfo();
        if (null!=userInfo){
            List<OrderInfo> orderInfo = OrderDbHelper.getInstance(getActivity()).queryOrderListData(userInfo.getUsername());
            orderListAdapter.setListData(orderInfo);
        }
    }
}