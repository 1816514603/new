package com.fuish.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fuish.test.db.UserDbHelper;
import com.fuish.test.entity.UserInfo;

public class LoginActivity extends AppCompatActivity {

    private TextView register ;
    private Button login;
    private EditText et_username ;
    private EditText et_password;
    private Boolean is_login;
    private CheckBox checkbox;
    private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        checkbox = findViewById(R.id.checkbox);
        mSharedPreferences = getSharedPreferences("user",MODE_PRIVATE);


        //是否勾选了密码
        is_login = mSharedPreferences.getBoolean("is_login", false);
        if (is_login)
        {
            String username = mSharedPreferences.getString("username", null);
            String password = mSharedPreferences.getString("password", null);
            et_username.setText(username);
            et_password.setText(password);
            checkbox.setChecked(true);
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转注册页面
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                if (TextUtils.isEmpty(username)&&TextUtils.isEmpty(password))
                {
                    Toast.makeText(LoginActivity.this, "请输入登录信息", Toast.LENGTH_SHORT).show();
                }else
                {
                    UserInfo login = UserDbHelper.getInstance(LoginActivity.this).login(username);
                    if (login!=null)
                    {
                        if (username.equals(login.getUsername())&&password.equals(login.getPassword()))
                        {
                            //记住密码
                            SharedPreferences.Editor edit = mSharedPreferences.edit();
                            edit.putBoolean("is_login",is_login);
                            edit.putString("username",username);
                            edit.putString("password",password);

                            //这句不能忘
                            edit.commit();

                            //保存用户名和密码
                            UserInfo.setsUserInfo(login);
                            //登录完成
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else
                        {
                            Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "没有注册信息，请先注册", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                is_login=isChecked;
            }
        });

    }
    }
