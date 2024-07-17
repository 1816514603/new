package com.fuish.test2.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fuish.test2.R;
import com.fuish.test2.entity.ProductInfo;

import java.util.ArrayList;
import java.util.List;

public class RightListAdapter extends RecyclerView.Adapter<RightListAdapter.MyHolder> {
    private List<ProductInfo> mProductInfos=new ArrayList<>();

    public void setListData(List<ProductInfo>list){
        this.mProductInfos=list;

        //一定要刷新
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //加载布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_list_item, null);
        return new MyHolder(view);
    }

    @Override

    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        //绑定数据
        ProductInfo productInfo = mProductInfos.get(position);

        if (productInfo != null) {
            holder.product_img.setImageResource(productInfo.getProduct_img());
            holder.product_title.setText(productInfo.getProduct_title());
            holder.product_details.setText(productInfo.getProduct_details());
            holder.product_price.setText(productInfo.getProduct_price());

            // 点击事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnClickItemListener != null) {
                        mOnClickItemListener.OnItemClick(productInfo, position);
                    }
                    Log.d("productInfo1", "Clicked item with title: " + productInfo.getProduct_title());
                }
            });
        } else {
            Log.e("RightListAdapter", "productInfo is null at position: " + position);
        }
    }


    @Override
    public int getItemCount() {
        return mProductInfos.size();
    }



    static class MyHolder extends RecyclerView.ViewHolder{
        ImageView product_img;
        TextView product_title;
        TextView product_details;
        TextView product_price;
        public MyHolder(@NonNull View itemView){
            super(itemView);
            product_img=itemView.findViewById(R.id.product_img);
            product_title=itemView.findViewById(R.id.product_title);
            product_details=itemView.findViewById(R.id.product_details);
            product_price=itemView.findViewById(R.id.product_price);

        }
    }
    private  OnClickItemListener mOnClickItemListener;
    public void setOnItemClickListener(OnClickItemListener onClickItemListener) {
        mOnClickItemListener=onClickItemListener;

    }
    public  interface OnClickItemListener{
        void OnItemClick(ProductInfo productInfo ,int position);
}

}
