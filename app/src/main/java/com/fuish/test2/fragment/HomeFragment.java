package com.fuish.test2.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fuish.test2.ProductDetailsActivity;
import com.fuish.test2.R;
import com.fuish.test2.adapter.LeftListAdapter;
import com.fuish.test2.adapter.RightListAdapter;
import com.fuish.test2.entity.DataService;
import com.fuish.test2.entity.ProductInfo;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

private  View rootView;
private RecyclerView leftRecyclerView;
private RecyclerView rightRecyclerView;
private LeftListAdapter mLeftListAdapter;
private RightListAdapter mRightListAdapter;
private List<String> leftDataList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =inflater.inflate(R.layout.fragment_home, container, false);

        //初始化控件
        leftRecyclerView=rootView.findViewById(R.id.leftRecyclerView);
        rightRecyclerView=rootView.findViewById(R.id.rightRecyclerView);
        return  rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        leftDataList.add("食品");
        leftDataList.add("饮料");
        leftDataList.add("服装");
        leftDataList.add("电器");
        leftDataList.add("化妆品");
        leftDataList.add("日用品");

        mLeftListAdapter=new LeftListAdapter(leftDataList);
        leftRecyclerView.setAdapter(mLeftListAdapter);

        mRightListAdapter=new RightListAdapter();
        rightRecyclerView.setAdapter(mRightListAdapter);
        //默认加载食品数据
        mRightListAdapter.setListData(DataService.getListData(0));


        mRightListAdapter.setOnItemClickListener(new RightListAdapter.OnClickItemListener() {
            @Override
            public void OnItemClick(ProductInfo productInfo, int position) {
                Log.d("HomeFragment","111"+productInfo.getProduct_title());
            //跳转传值
                Intent intent=new Intent(getActivity(), ProductDetailsActivity.class);
                //传递对象的时候，实体类一定要implements Serializable
                intent.putExtra("productInfo",productInfo);
                startActivity(intent);
            }
        });

        //recyclerView点击事件
        mLeftListAdapter.setLeftListOnClickItemListener(new LeftListAdapter.LeftListOnClickItemListener() {
            @Override
            public void onItemClick(int position) {
                mLeftListAdapter.setCurrentIndex(position);
                //点击那个类型展示那个类型的列表
                mRightListAdapter.setListData(DataService.getListData(position));
            }
        });
    }
}