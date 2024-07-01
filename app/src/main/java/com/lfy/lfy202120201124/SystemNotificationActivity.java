package com.lfy.lfy202120201124;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lfy.lfy202120201124.adapter.NotificationAdapter;
import com.lfy.lfy202120201124.entity.Notification;

import java.util.ArrayList;
import java.util.List;

public class SystemNotificationActivity extends AppCompatActivity {

    private NotificationAdapter adapter;
    private List<Notification> notificationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_notification);

        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 显示返回按钮

        notificationList = new ArrayList<>();
        // Sample data
        notificationList.add(new Notification("系统升级通知", "2024-07-01", "系统将在2024年7月1日进行升级，请注意备份数据。"));
        notificationList.add(new Notification("新功能发布", "2024-06-30", "我们很高兴地宣布，新的功能已经上线，快来体验吧！"));

        adapter = new NotificationAdapter(notificationList);
        RecyclerView recyclerView = findViewById(R.id.notification_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_system_notification, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // Filter notifications based on query
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    // Filter notifications based on newText
                    return false;
                }
            });
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();  // 返回上一个活动
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
