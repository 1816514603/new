package com.fuish.test2.entity;

import com.fuish.test2.R;

import java.util.ArrayList;
import java.util.List;

public class DataService {

    public static List<ProductInfo> getListData(int position){
        List<ProductInfo>list=new ArrayList<>();
        if (position==0){
            list.add(new ProductInfo(0, R.mipmap.img_product,"特仑苏","牛奶","38"));
        } else if (position==1) {
            list.add(new ProductInfo(1, R.mipmap.product_yinl,"苹果山楂汁","厂名：河南省多源食品有限公司" + "厂址：孟州市河阳街道上函路中段路西6号\n" + "厂家联系方式：18736360572\n" + "配料表：见瓶身【可咨询客服】\n" + "储藏方法：保存于阴凉干燥处，避免阳光直晒，宜冷藏不宜冷冻。\n" + "保质期： 365","38"));
            list.add(new ProductInfo(2, R.mipmap.product_yinl2,"天地壹号","饮料","38"));
            list.add(new ProductInfo(3, R.mipmap.product_yinl3,"补水啦","饮料","18"));
            list.add(new ProductInfo(4, R.mipmap.product_yinl4,"外星人电解质","饮料","20"));
        } else if (position==2) {
            list.add(new ProductInfo(5, R.mipmap.product_yifu,"婷美婼雅正品塑身衣收腹束腰收小肚子美体塑形内衣薄款女连体衣服","衣服","68"));
        } else if (position==3) {
            list.add(new ProductInfo(6, R.mipmap.product_riyongp,"公牛插座usb插排插线板拖线板插板带线家用多功能正品转换器多用","电器","18"));
        } else if (position==4) {
            list.add(new ProductInfo(7, R.mipmap.product_huazhuangp,"SK-II神仙水面部精华液保湿限定礼盒礼物skll sk2","化妆品","1688"));
        }
        else {list.add(new ProductInfo(8, R.mipmap.product_dianq,"收纳盒","日用品","20"));}

        return list;
    }
}
