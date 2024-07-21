package com.fuish.test2.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fuish.test2.R;
import com.fuish.test2.entity.CarInfo;

import java.util.ArrayList;
import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.MyHolder> {
    private List<CarInfo> mCarInfoList=new ArrayList<>();
    public void  setCarInfoList(List<CarInfo>list){
        this.mCarInfoList=list;
        notifyDataSetChanged();
    }
//    public void setData(List<CarInfo> list) {
//        this.mCarInfoList = list;
//    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_list_item, null);
        return new MyHolder(view);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        //绑定数据
        CarInfo carInfo=mCarInfoList.get(position);
        holder.product_img.setImageResource(carInfo.getProduct_img());
        holder.product_title.setText(carInfo.getProduct_title());
        holder.product_price.setText(String.valueOf(carInfo.getProduct_price()));
//        holder.product_count.setText(carInfo.getProduct_count());
        holder.product_count.setText(String.valueOf(carInfo.getProduct_count()));


        //设置点击事件
        holder.btn_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=mOnItemClickListener){
                    mOnItemClickListener.onSubTractOnClick(carInfo,position);
                }
            }
        });
        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=mOnItemClickListener){
                    mOnItemClickListener.onPlusOnClick(carInfo,position);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=mOnItemClickListener){
                    mOnItemClickListener.delOnClick(carInfo,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCarInfoList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        ImageView product_img;
        TextView product_title;
        TextView product_price;
        TextView product_count;
        TextView btn_subtract;
        TextView btn_add;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            product_img=itemView.findViewById(R.id.product_img);
            product_title=itemView.findViewById(R.id.product_title);
            product_price=itemView.findViewById(R.id.product_price);
            product_count=itemView.findViewById(R.id.product_count);
            btn_subtract=itemView.findViewById(R.id.btn_subtract);
            btn_add=itemView.findViewById(R.id.btn_add);
        }
    }
    private onItemClickListener mOnItemClickListener;


    public void setmOnItemClickListener(onItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface onItemClickListener{
        void onPlusOnClick(CarInfo carInfo,int position);
        void onSubTractOnClick(CarInfo carInfo,int position);
        void delOnClick(CarInfo carInfo,int position);
    }
}
