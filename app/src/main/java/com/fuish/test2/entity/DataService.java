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
            list.add(new ProductInfo(0, R.mipmap.product_yinl,"\n" +
                    "苹果山楂汁厂名\n" +
                    "河南省多源食品有限公司\n" +
                    "厂址\n" +
                    "孟州市河阳街道上函路中段路西6号\n" +
                    "厂家联系方式\n" +
                    "18736360572\n" +
                    "配料表\n" +
                    "见瓶身【可咨询客服】\n" +
                    "储藏方法\n" +
                    "保存于阴凉干燥处，避免阳光直晒，宜冷藏不宜冷冻。\n" +
                    "保质期\n" +
                    "365",38));
            list.add(new ProductInfo(0, R.mipmap.product_yinl2,"天地壹号",38));
            list.add(new ProductInfo(0, R.mipmap.product_yinl3,"补水啦",18));
            list.add(new ProductInfo(0, R.mipmap.product_yinl4,"外星人电解质",20));
        } else if (position==2) {
            list.add(new ProductInfo(0, R.mipmap.product_yifu,"婷美婼雅正品塑身衣收腹束腰收小肚子美体塑形内衣薄款女连体衣服",68));
        } else if (position==3) {
            list.add(new ProductInfo(0, R.mipmap.product_riyongp,"公牛插座usb插排插线板拖线板插板带线家用多功能正品转换器多用",18));
        } else if (position==4) {
            list.add(new ProductInfo(0, R.mipmap.product_huazhuangp,"SK-II神仙水面部精华液保湿限定礼盒礼物skll sk2",1688));
        }
        else {list.add(new ProductInfo(0, R.mipmap.product_dianq,"收纳盒",20));}
        return list;
    }
}
