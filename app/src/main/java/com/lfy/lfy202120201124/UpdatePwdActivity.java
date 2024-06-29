package com.lfy.lfy202120201124;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lfy.lfy202120201124.db.UserDbHelper;
import com.lfy.lfy202120201124.entity.UserInfo;

public class UpdatePwdActivity extends AppCompatActivity {
    private EditText et_new_pwd;
    private EditText et_confirm_pwd;
    private Button btn_update_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);

        //初始化控件
        et_new_pwd  = findViewById(R.id.et_new_pwd);
        et_confirm_pwd = findViewById(R.id.et_confirm_pwd);

        //修改密码点击事件
        findViewById(R.id.btn_update_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_pwd = et_new_pwd.getText().toString();
                String confirm_pwd = et_confirm_pwd.getText().toString();

                if (TextUtils.isEmpty(new_pwd)  || TextUtils.isEmpty(confirm_pwd)){
                    Toast.makeText(UpdatePwdActivity.this, "输入为空", Toast.LENGTH_SHORT).show();
                } else if (!new_pwd.equals(confirm_pwd)) {
                    Toast.makeText(UpdatePwdActivity.this, "两次输入不一致", Toast.LENGTH_SHORT).show();
                }else {
                    UserInfo userInfo = UserInfo.getUserInfo();
                    if (null!=userInfo){
                        int row = UserDbHelper.getInstance(UpdatePwdActivity.this).updatePwd(userInfo.getUsername(), new_pwd);
                        if (row >0){
                            Toast.makeText(UpdatePwdActivity.this, "修改成功，重新登录", Toast.LENGTH_SHORT).show();
                            setResult(1000);
                            finish();
                        }else {
                            Toast.makeText(UpdatePwdActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        //返回页面
    }
}