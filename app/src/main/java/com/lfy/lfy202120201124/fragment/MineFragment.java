package com.lfy.lfy202120201124.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lfy.lfy202120201124.R;
import com.lfy.lfy202120201124.entity.UserInfo;

public class MineFragment extends Fragment {

    private View rootView;
    private TextView tv_nickname;
    private TextView tv_username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       rootView =  inflater.inflate(R.layout.fragment_mine, container, false);

        //初始化控件
        tv_username = rootView.findViewById(R.id.tv_username);
        tv_nickname= rootView.findViewById(R.id.tv_nickname);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //设置用户数据
        UserInfo userInfo = UserInfo.getUserInfo();
        if (null!= userInfo){
            tv_username.setText(userInfo.getUsername());
            tv_nickname.setText(userInfo.getNickname());

        }
    }
}