package com.lfy.lfy202120201124;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lfy.lfy202120201124.db.UserDbHelper;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    //private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //获取mSharedPreferences
        //mSharedPreferences = getSharedPreferences("user",MODE_PRIVATE);

        //初始化控件
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        //返回登录
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //销毁页面
                finish();
            }
        });

        //点击注册
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username =et_username.getText().toString();
                String password = et_password.getText().toString();
                if(TextUtils.isEmpty(username)&&TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }else {
//                    SharedPreferences.Editor editor = mSharedPreferences.edit();
//                    editor.putString("username",username);
//                    editor.putString("password",password);
//                    editor.commit();
                    int row = UserDbHelper.getInstance(RegisterActivity.this).register(username, password, "暂无~~");
                    //提交
                    if (row>0){
                        Toast.makeText(RegisterActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }
        });
    }
}