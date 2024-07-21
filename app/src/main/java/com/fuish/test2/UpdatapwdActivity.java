package com.fuish.test2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.fuish.test2.db.UserDbHelper;
import com.fuish.test2.entity.UserInfo;


public class UpdatapwdActivity extends AppCompatActivity {
private TextView et_updata_pwd;
private TextView et_comfirm_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatapwd);

        et_updata_pwd=findViewById(R.id.et_updata_pwd);
        et_comfirm_password=findViewById(R.id.et_comfirm_password);
        
        //点击事件
        findViewById(R.id.btn_updata_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et_updata=et_updata_pwd.getText().toString();
                String et_comfirm=et_comfirm_password.getText().toString();
                Log.d("UpdatapwdActivity", "Update button clicked");
                if (TextUtils.isEmpty(et_updata)||TextUtils.isEmpty(et_comfirm)){
                    Toast.makeText(UpdatapwdActivity.this, "信息不能为空", Toast.LENGTH_SHORT).show();
                } else if (!et_updata.equals(et_comfirm)) {
                    Toast.makeText(UpdatapwdActivity.this, "密码与新密码不一致", Toast.LENGTH_SHORT).show();
                }else{

                    UserInfo userInfo=UserInfo.getsUserInfo();
                    if (userInfo!=null) {
                        Log.d("UpdatapwdActivity", "UserInfo not null. Attempting password update");
                        int row=UserDbHelper.getInstance(UpdatapwdActivity.this).updatePwd(userInfo.getUsername(), et_updata);
                        if (row>0){
                            Toast.makeText(UpdatapwdActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                            // 更新UserInfo对象中的密码
//                            userInfo.setPassword(et_updata);
//                            UserInfo.setsUserInfo(userInfo);
//                            // 更新SharedPreferences中的密码
//                            SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
//                            editor.putString("password", et_updata);
//                            editor.apply();
                            //回传的时候要用startActivityForResult启动一个页面，并且在该页面中设置setResult
                            Log.d("UpdatapwdActivity", "Password updated, finishing activity.");
                            setResult(1000);
                            finish();
                        }else {
                            Toast.makeText(UpdatapwdActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
findViewById(R.id.toobal).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Log.d("UpdatapwdActivity", "Toolbar back button clicked, finishing activity.");
        finish();

    }
});
    }
}