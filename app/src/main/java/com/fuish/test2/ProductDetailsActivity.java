package com.fuish.test2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fuish.test2.entity.ProductInfo;

public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView product_img;
    private TextView product_title;
    private TextView product_price;
    private  TextView product_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        //返回
        findViewById(R.id.toobal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //初始化控件
        product_img=findViewById(R.id.product_img);
        product_title=findViewById(R.id.product_title);
        product_price=findViewById(R.id.product_price);
        product_details=findViewById(R.id.product_details);
    }
    private onItemClickListener monItemClickListener;


    public void setMonItemClickListener(onItemClickListener monItemClickListener) {
        this.monItemClickListener = monItemClickListener;
    }

    public  interface  onItemClickListener{
        void onItemClick(ProductInfo productInfo,int position);
    }
}