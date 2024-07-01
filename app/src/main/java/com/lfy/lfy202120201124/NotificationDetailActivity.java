package com.lfy.lfy202120201124;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class NotificationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);

        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 显示返回按钮

        TextView titleTextView = findViewById(R.id.notification_title);
        TextView dateTextView = findViewById(R.id.notification_date);
        TextView contentTextView = findViewById(R.id.notification_content);

        // 获取传递的数据
        String title = getIntent().getStringExtra("title");
        String date = getIntent().getStringExtra("date");
        String content = getIntent().getStringExtra("content");

        // 设置数据
        titleTextView.setText(title);
        dateTextView.setText(date);
        contentTextView.setText(content);
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
