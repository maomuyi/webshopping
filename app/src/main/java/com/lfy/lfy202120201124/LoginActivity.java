package com.lfy.lfy202120201124;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.lfy.lfy202120201124.db.UserDbHelper;
import com.lfy.lfy202120201124.entity.UserInfo;

public class LoginActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private SharedPreferences mSharedPreferences;
    private CheckBox checkbox;
    private boolean is_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        //获取mSharedPreferences
        mSharedPreferences = getSharedPreferences("user",MODE_PRIVATE);

        //初始化控件
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        checkbox = findViewById(R.id.checkbox);

        //是否勾选记住密码
        is_login = mSharedPreferences.getBoolean("is_login",false);
        if (is_login){
            String username = mSharedPreferences.getString("username",null);
            String password = mSharedPreferences.getString("password",null);
            et_username.setText(username);
            et_password.setText(password);
            checkbox.setChecked(true);
        }

        //点击注册
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到注册页面
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        //登录
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                if(TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }else{
//                    String name = mSharedPreferences.getString("username",null);
//                    String pwd = mSharedPreferences.getString("password", null);
                    UserInfo login = UserDbHelper.getInstance(LoginActivity.this).login(username);
                    if (login!=null){
                        if (username.equals(login.getUsername()) && password.equals(login.getPassword())){
                            //勾选保存密码
                            SharedPreferences.Editor editor = mSharedPreferences.edit();
                            editor.putBoolean("is_login",is_login);
                            editor.putString("username",username);
                            editor.putString("password",password);
                            editor.commit();

                            //保存用户名和密码
                            UserInfo.setUserInfo(login);

                            //登录成功
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(LoginActivity.this, "请先注册账号", Toast.LENGTH_SHORT).show();
                    }



                }
            }
        });
        //勾选记住密码
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                is_login=isChecked;
            }
        });
    }
}