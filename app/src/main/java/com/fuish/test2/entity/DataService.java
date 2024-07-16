package com.fuish.test2.entity;

import com.fuish.test2.R;

import java.util.ArrayList;
import java.util.List;

public class DataService {

    public static List<ProductInfo> getListData(int position){
        List<ProductInfo>list=new ArrayList<>();
        if (position==0){
            list.add(new ProductInfo(0, R.mipmap.img_product,"特仑苏",38));
        } else if (position==1) {
            
        } else if (position==2) {
            
        } else if (position==3) {
            
        } else if (position==4) {
            
        }
        return list;
    }
}
