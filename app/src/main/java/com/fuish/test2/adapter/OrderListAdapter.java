package com.fuish.test2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fuish.test2.R;
import com.fuish.test2.entity.CarInfo;
import com.fuish.test2.entity.OrderInfo;

import java.util.ArrayList;
import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyHolder> {
    private RecyclerView rootView;
    private List<OrderInfo> mOrderInfo=new ArrayList<>();
    private OnItemClickListener onItemClickListener;
//    public  void setmOrderInfo(List<OrderInfo>list ){
//        this.mOrderInfo=list;
//        notifyDataSetChanged();
//    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_item,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //綁定數據
        OrderInfo orderInfo=mOrderInfo.get(position);
        holder.product_img.setImageResource(orderInfo.getProduct_img());
        holder.product_title.setText(orderInfo.getProduct_title());
        holder.product_price.setText(orderInfo.getProduct_price()+"");
        holder.product_count.setText(""+orderInfo.getProduct_count());
        holder.address.setText("["+orderInfo.getAddres()+"]"+orderInfo.getMobile());


        //长按删除
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.delOnClick(orderInfo,position);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mOrderInfo.size();
    }

    public void setListData(List<OrderInfo>list) {
        this.mOrderInfo=list;
        notifyDataSetChanged();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        ImageView product_img;
        TextView product_title;
        TextView product_price;
        TextView product_count;
        TextView address;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
        product_img=itemView.findViewById(R.id.product_img);
        product_title=itemView.findViewById(R.id.product_title);
        product_price=itemView.findViewById(R.id.product_price);
        product_count=itemView.findViewById(R.id.product_count);
        address=itemView.findViewById(R.id.address);
        }
    }



    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener{

        void delOnClick(OrderInfo orderInfo,int position);

    }
}
