package com.lfy.lfy202120201124.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lfy.lfy202120201124.ProductDetailsActivity;
import com.lfy.lfy202120201124.R;
import com.lfy.lfy202120201124.adapter.LeftListAdapter;
import com.lfy.lfy202120201124.adapter.RightListAdapter;
import com.lfy.lfy202120201124.entity.DataService;
import com.lfy.lfy202120201124.entity.ProductInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeFragment extends Fragment {

    private View rootView;
    private RecyclerView rightRecyclerView;
    private RightListAdapter rightListAdapter;
    private RecyclerView leftRecyclerView;
    private LeftListAdapter leftListAdapter;
    private List<String> leftDataList = new ArrayList<>();
    private List<ProductInfo> currentListData = new ArrayList<>();
    private EditText searchEditText;
    private ImageView sortImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // 初始化控件
        leftRecyclerView = rootView.findViewById(R.id.leftRecyclerView);
        rightRecyclerView = rootView.findViewById(R.id.rightRecyclerView);
        searchEditText = rootView.findViewById(R.id.searchEditText);
        sortImageView = rootView.findViewById(R.id.sortImageView);

        // 设置布局管理器
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // 设置排序图标点击事件
        sortImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSortMenu(v);
            }
        });

        // 设置搜索框监听器
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterListBySearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 初始化数据
        leftDataList.add("SENNHEISER");
        leftDataList.add("audio-technica");
        leftDataList.add("beyerdynamic");
        leftDataList.add("Sony");
        leftDataList.add("FiiO");

        // 初始化适配器
        leftListAdapter = new LeftListAdapter(leftDataList);
        leftRecyclerView.setAdapter(leftListAdapter);

        rightListAdapter = new RightListAdapter();
        rightRecyclerView.setAdapter(rightListAdapter);

        // 默认加载第一个分类
        currentListData = DataService.getListData(0);
        rightListAdapter.setListData(currentListData);

        // RecyclerView点击事件
        rightListAdapter.setOnItemClickListener(new RightListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(ProductInfo productInfo, int position) {
                //跳转传值
                Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                intent.putExtra("productInfo", productInfo);
                startActivity(intent);
            }
        });

        // RecyclerView点击事件
        leftListAdapter.setLeftListOnClickItemLietener(new LeftListAdapter.LeftListOnClickItemLietener() {
            @Override
            public void OnItemClick(int position) {
                Toast.makeText(getActivity(), position + "---", Toast.LENGTH_SHORT).show();
                leftListAdapter.setCurrentIndex(position);
                // 点击分类切换对应数据
                currentListData = DataService.getListData(position);
                rightListAdapter.setListData(currentListData);
            }
        });
    }

    // 显示排序菜单
    private void showSortMenu(View view) {
        PopupMenu popup = new PopupMenu(getActivity(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_sort, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.sort_price_asc) {
                    sortListByPrice(true);
                    return true;
                } else if (id == R.id.sort_price_desc) {
                    sortListByPrice(false);
                    return true;
                }
                return false;
            }
        });
        popup.show();
    }

    // 按价格排序
    private void sortListByPrice(boolean ascending) {
        if (ascending) {
            Collections.sort(currentListData, new Comparator<ProductInfo>() {
                @Override
                public int compare(ProductInfo p1, ProductInfo p2) {
                    return Double.compare(p1.getProduct_price(), p2.getProduct_price());
                }
            });
        } else {
            Collections.sort(currentListData, new Comparator<ProductInfo>() {
                @Override
                public int compare(ProductInfo p1, ProductInfo p2) {
                    return Double.compare(p2.getProduct_price(), p1.getProduct_price());
                }
            });
        }
        rightListAdapter.notifyDataSetChanged();
    }

    // 按搜索过滤
    private void filterListBySearch(String query) {
        List<ProductInfo> filteredList = new ArrayList<>();
        for (ProductInfo product : currentListData) {
            if (product.getProduct_title().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(product);
            }
        }
        rightListAdapter.setListData(filteredList);
    }
}
