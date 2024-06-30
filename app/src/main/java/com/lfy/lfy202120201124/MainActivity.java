package com.lfy.lfy202120201124;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lfy.lfy202120201124.fragment.CarFragment;
import com.lfy.lfy202120201124.fragment.HomeFragment;
import com.lfy.lfy202120201124.fragment.MineFragment;
import com.lfy.lfy202120201124.fragment.OrderFragment;
import com.lfy.lfy202120201124.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {
    private HomeFragment homeFragment;
    private CarFragment carFragment;
    private SearchFragment searchFragment;
    private OrderFragment orderFragment;
    private MineFragment mineFragment;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //设置点击事件
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.home){
                    selectedFragment(0);

                }else if(item.getItemId()==R.id.car){
                    selectedFragment(1);

                } else if (item.getItemId()==R.id.search) {
                    selectedFragment(2);

                } else if (item.getItemId()==R.id.order) {
                    selectedFragment(3);

                }else {
                    selectedFragment(4);

                }
                return true;
            }
        });
        //默认页面
        selectedFragment(0);
    }
    private void selectedFragment(int position){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);
        if(position==0){
            if (homeFragment ==null){
                homeFragment=new HomeFragment();
                fragmentTransaction.add(R.id.content,homeFragment);
            }else {
                fragmentTransaction.show(homeFragment);
            }
            
        } else if (position==1) {
            if (carFragment ==null){
                carFragment=new CarFragment();
                fragmentTransaction.add(R.id.content,carFragment);
            }else {
                fragmentTransaction.show(carFragment);
                //刷新数据
                carFragment.loadData();
            }

        } else if (position==2) {
            if (searchFragment ==null){
                searchFragment=new SearchFragment();
                fragmentTransaction.add(R.id.content,searchFragment);
            }else {
                fragmentTransaction.show(searchFragment);

            }
            
        } else if (position==3) {
            if (orderFragment ==null){
                orderFragment=new OrderFragment();
                fragmentTransaction.add(R.id.content,orderFragment);
            }else {
                fragmentTransaction.show(orderFragment);
            }
            
        } else if (position==4) {
            if (mineFragment ==null){
                mineFragment=new MineFragment();
                fragmentTransaction.add(R.id.content,mineFragment);
            }else {
                fragmentTransaction.show(mineFragment);
            }
            
        }
        fragmentTransaction.commit();
    }
    private void hideFragment(FragmentTransaction fragmentTransaction){
        if (homeFragment!=null){
            fragmentTransaction.hide(homeFragment);
        }
        if (carFragment!=null){
            fragmentTransaction.hide(carFragment);
        }
        if (searchFragment!=null){
            fragmentTransaction.hide(searchFragment);
        }
        if (orderFragment!=null){
            fragmentTransaction.hide(orderFragment);
        }
        if (mineFragment!=null){
            fragmentTransaction.hide(mineFragment);
        }
    }
}