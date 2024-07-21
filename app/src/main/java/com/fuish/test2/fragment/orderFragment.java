package com.fuish.test2.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fuish.test2.R;
import com.fuish.test2.adapter.OrderListAdapter;
import com.fuish.test2.db.CarDbHelper;
import com.fuish.test2.db.OrderDbHelper;
import com.fuish.test2.entity.CarInfo;
import com.fuish.test2.entity.OrderInfo;
import com.fuish.test2.entity.UserInfo;

import java.util.List;


public class orderFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private OrderListAdapter mOrderListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_order, container, false);
        recyclerView=rootView.findViewById(R.id.recyclerView);
        return rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化mOrderListAdapter
        mOrderListAdapter=new OrderListAdapter();
        //設置OrderListAdapter
        recyclerView.setAdapter(mOrderListAdapter);
//recyclerView点击事件
        mOrderListAdapter.setOnItemClickListener(new OrderListAdapter.OnItemClickListener() {
            @Override
            public void delOnClick(OrderInfo orderInfo, int position) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("温馨提示")
                        .setMessage("确认要删除订单？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int row=OrderDbHelper.getInstance(getActivity()).delete(orderInfo.getOrder_id()+"");
                                if (row>0){
                                    loadData();
                                    Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getActivity(), "删除失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        }).show();
            }
        });
        loadData();
    }

    private void loadData() {
        //获取数据
//        CarDbHelper dbHelper = CarDbHelper.getInstance(getActivity());
//        List<CarInfo> carList = dbHelper.queryCarList("123");
        UserInfo userInfo = UserInfo.getsUserInfo();
        if (userInfo != null) {
            List<OrderInfo> orderInfos = OrderDbHelper.getInstance(getActivity()).queryOrderData(userInfo.getUsername());
            //设置数据
            mOrderListAdapter.setListData(orderInfos);
        }
    }
}