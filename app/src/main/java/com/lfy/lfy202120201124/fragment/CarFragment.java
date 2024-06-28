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
import android.widget.Button;
import android.widget.TextView;

import com.lfy.lfy202120201124.R;
import com.lfy.lfy202120201124.adapter.CarListAdapter;
import com.lfy.lfy202120201124.db.CarDbHelper;
import com.lfy.lfy202120201124.db.OrderDbHelper;
import com.lfy.lfy202120201124.entity.CarInfo;
import com.lfy.lfy202120201124.entity.UserInfo;

import java.util.List;


public class CarFragment extends Fragment {
    private View rootView;
    private RecyclerView recyclerView;
    private CarListAdapter carListAdapter;
    private Button btn_total;
    private TextView total;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         rootView= inflater.inflate(R.layout.fragment_car, container, false);
         //初始化控件
        recyclerView = rootView.findViewById(R.id.recyclerView);
        total = rootView.findViewById(R.id.total);
        btn_total = rootView.findViewById(R.id.btn_total);
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

            @Override
            public void delOnClick(CarInfo carInfo, int position) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("危险警告")
                                .setMessage("你真的想好了吗，真的要离开我吗")
                                        .setPositiveButton("就这样吧", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                CarDbHelper.getInstance(getActivity()).delete(carInfo.getCar_id()+" ");
                                                loadData();
                                            }
                                        })
                                                .setNegativeButton("不要~", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                    }
                                                })
                        .show();

            }


        });
        //点击结算
        btn_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //批量将购物车里面的数据生成订单
                UserInfo userInfo = UserInfo.getUserInfo();
                if (null !=  userInfo){
                    List<CarInfo> carList= CarDbHelper.getInstance(getActivity()).queryCarList(userInfo.getUsername());
                    //生成订单
                    OrderDbHelper.getInstance(getActivity()).insertByAll(carList,"四川内江","13113333");
                    //清除购物车
                    for (int i =0;i<carList.size();i++){
                        CarDbHelper.getInstance(getActivity()).delete(carList.get(i).getCar_id()+"");
                    }
                    //重新加载页面
                    loadData();
                }

            }
        });

        loadData();
    }
    private void setTotalData(List<CarInfo> list){
        int totalCount = 0;
        for (int i= 0;i< list.size();i++){
            int price =list.get(i).getProduct_price()*list.get(i).getProduct_count();
            totalCount=totalCount+price;
        }
        total.setText(totalCount+".00");
    }
    public  void  loadData(){
        UserInfo userInfo = UserInfo.getUserInfo();
        if (userInfo!=null){
            //获取数据
            List<CarInfo> carInfoList = CarDbHelper.getInstance(getActivity()).queryCarList(userInfo.getUsername());
            //设置数据
            carListAdapter.setCarInfoList(carInfoList);
            //计算总价
            setTotalData(carInfoList);
        }

    }
}