package com.fuish.test2.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fuish.test2.AboutActivity;
import com.fuish.test2.R;
import com.fuish.test2.UpdatapwdActivity;
import com.fuish.test2.entity.UserInfo;
import com.fuish.test2.loginActivity;


public class MineFragment extends Fragment {

private View rootView;
private TextView tv_username;
private TextView tv_nickname;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_mine, container, false);


        //初始化控件
        tv_username=rootView.findViewById(R.id.tv_username);
        tv_nickname=rootView.findViewById(R.id.tv_nickname);
        //退出登录
        rootView.findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("温馨提示")
                        .setMessage("确定退出登录吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                                Intent intent=new Intent(getActivity(), loginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });

        //修改密码
        rootView.findViewById(R.id.tv_updata_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), UpdatapwdActivity.class);
                startActivityForResult(intent,1000);
            }
        });

        rootView.findViewById(R.id.about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UserInfo userInfo=UserInfo.getsUserInfo();
        if (userInfo!=null) {
            tv_username.setText(userInfo.getUsername());
            tv_nickname.setText(userInfo.getNickname());



        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000){
            getActivity().finish();
            Intent intent=new Intent(getActivity(), loginActivity.class);
            startActivity(intent);
        }
    }
}