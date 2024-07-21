package com.fuish.test2;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WelcomeActivity extends AppCompatActivity {
private TextView tv_countdown;
private CountDownTimer countDownTimer;
private long timeLeftInMillis=4000;//设置倒计时时长，单位为毫秒
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //初始化控件
        tv_countdown=findViewById(R.id.tv_countdown);
        //启动倒计时
        startCountdown();

    }

    private void startCountdown() {
        countDownTimer=new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            timeLeftInMillis=millisUntilFinished;
            int secondsRemaining=(int) (millisUntilFinished/1000);
            tv_countdown.setText(secondsRemaining+"s");
            }

            @Override
            public void onFinish() {
//跳转到登录页面
                Intent intent=new Intent(WelcomeActivity.this,loginActivity.class);
                startActivity(intent);
                //倒计时结束后的操作
                finish();

            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer!=null){
            countDownTimer.cancel();
        }
    }
}