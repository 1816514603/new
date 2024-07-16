package com.fuish.test2;

import static com.fuish.test2.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fuish.test2.db.UserDbHelper;
import com.fuish.test2.entity.UserInfo;

public class registerActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_register);

        //获取mSharedPreferences
        mSharedPreferences=getSharedPreferences("user",MODE_PRIVATE);

        et_username=findViewById(id.et_username);
        et_password=findViewById(id.et_password);
        //返回
        findViewById(id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //不要跳转到登录页面，直接销毁就好
                finish();
            }
        });

        //点击注册
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String username= et_username.getText().toString();
                String password= et_password.getText().toString();

                if(TextUtils.isEmpty(username)|| TextUtils.isEmpty(password)){
                    Toast.makeText(registerActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }else{

                    int row= UserDbHelper.getInstance(registerActivity.this).register(username,password,"暂无~~~");
                    Log.d("registerActivity","注册成功");
//                    SharedPreferences.Editor edit = mSharedPreferences.edit();
//                    edit.putString("username",username);
//                    edit.putString("password",password);
//                    edit.commit();
//                    Toast.makeText(registerActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
//                    finish();
                    if(row>0){
                        SharedPreferences.Editor edit = mSharedPreferences.edit();
                        edit.putString("username",username);
                        edit.putString("password",password);
                        edit.commit();
                        Toast.makeText(registerActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                        finish();
                    }


                }


            }
        });

    }
}