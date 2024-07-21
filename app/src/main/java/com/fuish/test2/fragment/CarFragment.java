package com.fuish.test2.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fuish.test2.R;
import com.fuish.test2.adapter.CarListAdapter;
import com.fuish.test2.db.CarDbHelper;
import com.fuish.test2.db.OrderDbHelper;
import com.fuish.test2.entity.CarInfo;
import com.fuish.test2.entity.UserInfo;

import java.util.List;


public class CarFragment extends Fragment {

private  View rootView;
private RecyclerView recyclerView;
private TextView total;
private Button btn_total;
private CarListAdapter mCarListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_car, container, false);

        recyclerView=rootView.findViewById(R.id.recyclerView);
        total=rootView.findViewById(R.id.total);
        btn_total=rootView.findViewById(R.id.btn_total);
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
        //recyclerView点击事件
        mCarListAdapter.setmOnItemClickListener(new CarListAdapter.onItemClickListener() {
            @Override
            public void onPlusOnClick(CarInfo carInfo, int position) {
                //加
                CarDbHelper.getInstance(getActivity()).updateProduct(carInfo.getCar_id(),carInfo);
                loadData();
            }

            @Override
            public void onSubTractOnClick(CarInfo carInfo, int position) {
                //减
                CarDbHelper.getInstance(getActivity()).subtractUpdateProduct(carInfo.getCar_id(),carInfo);
                loadData();
            }

            @Override
            public void delOnClick(CarInfo carInfo, int position) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("溫馨提示")
                        .setMessage("确认要删除商品？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CarDbHelper.getInstance(getActivity()).delete(carInfo.getCar_id()+"");
                        loadData();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();

            }
        });
        //結算
        btn_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo userInfo=UserInfo.getsUserInfo();
                if (userInfo!=null){
                    List<CarInfo> carList = CarDbHelper.getInstance(getActivity()).queryCarList(userInfo.getUsername());
                    if (carList.size()==0){
                        Toast.makeText(getActivity(), "你还没有选择商品！！！", Toast.LENGTH_SHORT).show();
                    }else {

                        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pay_dialog, null);

                        EditText et_mobile=view.findViewById(R.id.et_mobile);
                        EditText et_address=view.findViewById(R.id.et_address);
                        TextView tv_total=view.findViewById(R.id.tv_total);
                        tv_total.setText(total.getText().toString());
                        new AlertDialog.Builder(getActivity())
                                .setTitle("支付温馨提示")
                                .setView(view)
                                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String mobile=et_mobile.getContext().toString();
                                        String address=et_address.getContext().toString();
                                        if(TextUtils.isEmpty(mobile)||TextUtils.isEmpty(address)){
                                            Toast.makeText(getActivity(), "请填写完整信息", Toast.LENGTH_SHORT).show();
                                        }else {
                                            //生成訂單
                                            OrderDbHelper.getInstance(getActivity()).insertByAll(carList,address,mobile);

                                            //清空購物車
                                            for (int i = 0; i < carList.size(); i++) {
                                                CarDbHelper.getInstance(getActivity()).delete(carList.get(i).getCar_id()+"");
                                            }
                                            loadData();
                                            Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .show();
                    }


                }


            }
        });
        loadData();
    }
    private void setTotalData(List<CarInfo>list){
        int TotalCount=0;
        for (int i = 0; i <list.size() ; i++) {
            int price=list.get(i).getProduct_price() * list.get(i).getProduct_count();
            TotalCount=TotalCount+price;
        }
        total.setText(TotalCount+".00");
    }
    public void  loadData() {
        //获取数据
//        CarDbHelper dbHelper = CarDbHelper.getInstance(getActivity());
//        List<CarInfo> carList = dbHelper.queryCarList("123");
        UserInfo userInfo = UserInfo.getsUserInfo();
        if (userInfo != null) {
            List<CarInfo> carList = CarDbHelper.getInstance(getActivity()).queryCarList(userInfo.getUsername());
            //设置数据
//        mCarListAdapter.setData(carList);
            Log.d("CarFragment", "Loaded car list: " + carList.toString());
//        mCarListAdapter.notifyDataSetChanged();
            mCarListAdapter.setCarInfoList(carList);
            setTotalData(carList);
        }
    }
}