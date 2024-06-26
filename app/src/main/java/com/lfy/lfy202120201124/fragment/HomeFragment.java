package com.lfy.lfy202120201124.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.lfy.lfy202120201124.R;
import com.lfy.lfy202120201124.adapter.LeftListAdapter;
import com.lfy.lfy202120201124.adapter.RightListAdapter;
import com.lfy.lfy202120201124.entity.DataService;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private View rootView;
    private RecyclerView rightRecyclerView;
    private RightListAdapter rightListAdapter;
    private RecyclerView leftRecyclerView;
    private LeftListAdapter leftListAdapter;
    private List<String> leftDataList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_home,container,false);
        //初始化控件
        leftRecyclerView  = rootView.findViewById(R.id.leftRecyclerView);
        rightRecyclerView  = rootView.findViewById(R.id.rightRecyclerView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        leftDataList.add("SENNHEISER");
        leftDataList.add("audio-technica");
        leftDataList.add("beyerdynamic");
        leftDataList.add("Sony");
        leftDataList.add("FiiO");

        leftListAdapter = new LeftListAdapter(leftDataList);
        leftRecyclerView.setAdapter(leftListAdapter);

        rightListAdapter = new RightListAdapter();
        leftRecyclerView.setAdapter(rightListAdapter);
        //默认加载第一个分类
        rightListAdapter.setListData(DataService.getListData(0));

        //recyclerView点击事件
        leftListAdapter.setLeftListOnClickItemLietener(new LeftListAdapter.LeftListOnClickItemLietener() {
            @Override
            public void OnItemClick(int position) {
                Toast.makeText(getActivity(), position+"---", Toast.LENGTH_SHORT).show();
                leftListAdapter.setCurrentIndex(position);
                //点击分类切换对应数据
                rightListAdapter.setListData(DataService.getListData(position));
            }
        });
    }
}