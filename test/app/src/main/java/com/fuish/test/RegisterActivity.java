package com.fuish.test;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fuish.test.db.UserDbHelper;

public class RegisterActivity extends AppCompatActivity {

    private Button register;
    private EditText et_username;
    private EditText et_password;

//    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.register);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        //获取SharedPreferences
//        mSharedPreferences = getSharedPreferences("user",MODE_PRIVATE);

        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //不要跳转
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                if (TextUtils.isEmpty(username)&&TextUtils.isEmpty(password))
                {
                    Toast.makeText(RegisterActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }else
                {
                    int row = UserDbHelper.getInstance(RegisterActivity.this).register(username, password, "暂无~~");
                    if (row>0)
                    {
                        Toast.makeText(RegisterActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                        finish();
                    }else
                    {
                        Toast.makeText(RegisterActivity.this, "用户已注册", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}