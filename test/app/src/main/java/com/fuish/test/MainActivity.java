package com.fuish.test;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.fuish.test.Adapter.TabNewsFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.fuish.test.entity.UserInfo;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private String[] titles = {"娱乐", "军事", "教育", "文化", "将康", "财经", "体育", "汽车", "科技"};
    private List<TitleInfo>titles=new ArrayList<>();
    private TabLayout tab_layout;
    private ViewPager2 viewPager;

    private NavigationView nav_view;
    private TextView tv_username;
    private TextView tv_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        new LongRunningTask().execute();

        titles.add(new TitleInfo("推荐","top"));
        titles.add(new TitleInfo("国内","guonei"));
        titles.add(new TitleInfo("国际" , "guoji"));
        titles.add(new TitleInfo("娱乐","yule"));
        titles.add(new TitleInfo("体育","tiyu"));
        titles.add(new TitleInfo("军事" ,"junshi"));
        titles.add(new TitleInfo("科技","keji"));
        titles.add(new TitleInfo("财经","caijing"));
        titles.add(new TitleInfo("游戏" ,"youxi"));
        titles.add(new TitleInfo("汽车","qiche"));
        titles.add(new TitleInfo("健康","jiankang"));


        //初始化控件
        viewPager = findViewById(R.id.viewPager);
        tab_layout = findViewById(R.id.tab_layout);
        nav_view = findViewById(R.id.nav_view);
        tv_username=nav_view.getHeaderView(0).findViewById(R.id.tv_username);
        tv_nickname=nav_view.getHeaderView(0).findViewById(R.id.tv_nickname);



nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.nav_history){
            //跳转到历史记录
            Intent intent=new Intent(MainActivity.this,HistoryListActivity.class);
           startActivity(intent);
        } else if (item.getItemId()==R.id.nav_view) {
            //判断是否登录
            UserInfo userInfo=UserInfo.getsUserInfo();
//            if(){}
        }
        return true;
    }
});

        //设置adapter
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {

                //创建 NewsTabFragment页面
                String title = titles.get(position).getPy_title();
                TabNewsFragment tabNewsFragment = TabNewsFragment.newInstance(title);
                return tabNewsFragment;

            }

            @Override
            public int getItemCount() {
                return titles.size();
            }
        });


        //tab_layout点击事件
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //设置viewPager选中当前页
                viewPager.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        //viewPager和tab_layout关联在一起
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tab_layout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position).getTitle());
            }
        });

        //这几话不能少
        tabLayoutMediator.attach();
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserInfo userInfo=UserInfo.getsUserInfo();
        if(null!=userInfo){
            tv_username.setText(userInfo.getUsername());
            tv_nickname.setText(userInfo.getNickname());

        }else{
            tv_username.setText("请登录");
            tv_nickname.setText("");
            //点击事件
            tv_username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            });

        }




        
    }

}
