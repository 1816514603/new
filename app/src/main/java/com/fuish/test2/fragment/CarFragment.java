package com.fuish.test2.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fuish.test2.R;
import com.fuish.test2.adapter.CarListAdapter;
import com.fuish.test2.db.CarDbHelper;
import com.fuish.test2.entity.CarInfo;

import java.util.List;


public class CarFragment extends Fragment {

private  View rootView;
private RecyclerView recyclerView;
private CarListAdapter mCarListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_car, container, false);

        recyclerView=rootView.findViewById(R.id.recyclerView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//初始化mCarListAdapter
        mCarListAdapter=new CarListAdapter();
        //设置适配器
        recyclerView.setAdapter(mCarListAdapter);
        loadData();

    }
    public void  loadData(){
        //获取数据
//        CarDbHelper dbHelper = CarDbHelper.getInstance(getActivity());
//        List<CarInfo> carList = dbHelper.queryCarList("123");
        List<CarInfo> carList = CarDbHelper.getInstance(getActivity()).queryCarList("123");
        //设置数据
//        mCarListAdapter.setData(carList);
        Log.d("CarFragment", "Loaded car list: " + carList.toString());
//        mCarListAdapter.notifyDataSetChanged();
        mCarListAdapter.setCarInfoList(carList);
    }
}