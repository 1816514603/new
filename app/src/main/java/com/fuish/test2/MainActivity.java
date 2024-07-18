package com.fuish.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.MenuItem;

import com.fuish.test2.fragment.CarFragment;
import com.fuish.test2.fragment.HomeFragment;
import com.fuish.test2.fragment.MineFragment;
import com.fuish.test2.fragment.orderFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private HomeFragment mHomeFragment;
    private CarFragment mCarFragment;
    private orderFragment mOrderFragment;
    private MineFragment mMineFragment;
    private BottomNavigationView mbottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
         mbottomNavigationView=findViewById(R.id.bottomNavigationView);
         mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 if (item.getItemId()==R.id.home){
              SelectedFragment(0);
                 }
                 else if (item.getItemId()==R.id.car) {
                     SelectedFragment(1);
                 } else if (item.getItemId()==R.id.order) {
                     SelectedFragment(2);
                 }
                 else {
                     SelectedFragment(3);
                 }
                 return true;
             }
         });

         //默认首页选中
        SelectedFragment(0);

    }
    private  void SelectedFragment(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        HideFragment(fragmentTransaction);
        if (position == 0) {
            if (mHomeFragment == null) {
                mHomeFragment = new HomeFragment();
                fragmentTransaction.add(R.id.content, mHomeFragment);
            } else {
                fragmentTransaction.show(mHomeFragment);
            }}
            else if (position == 1) {
                if (mCarFragment == null) {
                    mCarFragment = new CarFragment();
                    fragmentTransaction.add(R.id.content, mCarFragment);
                }else {
                    fragmentTransaction.show(mCarFragment);
                    mCarFragment.loadData();
                }
            } else if (position==2) {
                if (mOrderFragment==null){
                    mOrderFragment=new orderFragment();
                    fragmentTransaction.add(R.id.content,mOrderFragment);
                }
                else {fragmentTransaction.show(mOrderFragment);
                }
            }
                else if(position==3){
                    if (mMineFragment==null){
                        mMineFragment=new MineFragment();
                        fragmentTransaction.add(R.id.content,mMineFragment);
                    }
                    else{
                        fragmentTransaction.show(mMineFragment);

                    }
                }



            fragmentTransaction.commit();

    }


    private void HideFragment(FragmentTransaction fragmentTransaction){

        if (mHomeFragment!=null){
         fragmentTransaction.hide(mHomeFragment);
        }
        if (mOrderFragment!=null){
            fragmentTransaction.hide(mOrderFragment);
        }
        if (mCarFragment!=null){
            fragmentTransaction.hide(mCarFragment);
        }
        if (mMineFragment!=null){
            fragmentTransaction.hide(mMineFragment);
        }

    }
}