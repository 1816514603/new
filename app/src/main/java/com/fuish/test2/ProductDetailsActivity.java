package com.fuish.test2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.fuish.test2.db.CarDbHelper;
import com.fuish.test2.entity.ProductInfo;

public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView product_img;
    private TextView product_title;
    private TextView product_price;
    private  TextView product_details;
    private  ProductInfo productInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        //获取传递的数据
         productInfo=(ProductInfo)getIntent().getSerializableExtra("productInfo");
        if (productInfo != null) {
            Log.d("ProductDetailsActivity", "Received productInfo: " + productInfo.getProduct_title());
        } else {
            Log.e("ProductDetailsActivity", "productInfo is null");
        }

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

        //设置数据

        if (productInfo == null) {
            Log.e("ProductInfoError", "productInfo is null");
        } else {
            Log.d("ProductInfo", "111");
            product_img.setImageResource(productInfo.getProduct_img());
            product_title.setText(productInfo.getProduct_title());
            product_price.setText(productInfo.getProduct_price());
            product_details.setText(productInfo.getProduct_details());
        }
        findViewById(R.id.addCar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //加入到购物车
                int row = CarDbHelper.getInstance(ProductDetailsActivity.this).addCar("123", productInfo.getProduct_id(), productInfo.getProduct_img(), productInfo.getProduct_title(), productInfo.getProduct_price());
            Log.d("ProductDetailsActivity",productInfo.getProduct_title());
                if (row>0){
                Toast.makeText(ProductDetailsActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(ProductDetailsActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
            }
            }
        });

        }
    }


