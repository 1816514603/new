package com.fuish.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fuish.test2.db.UserDbHelper;
import com.fuish.test2.entity.UserInfo;

public class loginActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化控件
        et_username=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);

        //获取mSharedPreferences
        mSharedPreferences=getSharedPreferences("user",MODE_PRIVATE);

        //点击注册
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(loginActivity.this,registerActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=et_username.getText().toString();
                String password=et_password.getText().toString();
                if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
                    Toast.makeText(loginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }else{
                    UserInfo login= UserDbHelper.getInstance(loginActivity.this).login(username);
                    if(login!=null) {
                        if (username.equals(login.getUsername()) && password.equals(login.getPassword())) {
                            //保存是否勾选了记住密码框
                            SharedPreferences.Editor edit = mSharedPreferences.edit();
//                        edit.putBoolean("is_login",is_login);
                            //这句话不能少
                            edit.commit();
                            //登录成功
                            Intent intent = new Intent(loginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(loginActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(loginActivity.this, "该账号未注册", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //记住密码点击事件
}
    }
