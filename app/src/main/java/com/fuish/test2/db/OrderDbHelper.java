package com.fuish.test2.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.fuish.test2.entity.CarInfo;
import com.fuish.test2.entity.OrderInfo;

import java.util.ArrayList;
import java.util.List;

public class OrderDbHelper extends SQLiteOpenHelper {
    private static OrderDbHelper sHelper;
    private static final String DB_NAME = "order_info.db";   //数据库名
    private static final int VERSION = 1;    //版本号

    //必须实现其中一个构方法
    public OrderDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static OrderDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new OrderDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建user_table表
        db.execSQL("create table order_table(order_id integer primary key autoincrement, " +
                "username text," +       //用户名
                "product_img integer," +
                "product_title text," +
                "product_price integer," +
                "product_count integer," +
                "address text," +
                "mobile text" +
                ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertByAll(List<CarInfo> list, String address, String mobile) {
        //获取数据库实例
        SQLiteDatabase db = getWritableDatabase();
        //开始事务
        db.beginTransaction();
        try {
            for (int i = 0; i < list.size(); i++) {
                ContentValues values = new ContentValues();
                values.put("username", list.get(i).getUsername());
                values.put("product_img", list.get(i).getProduct_img());
                values.put("product_title", list.get(i).getProduct_title());
                values.put("product_price", list.get(i).getProduct_price());
                values.put("product_count", list.get(i).getProduct_count());
                values.put("address", address);
                values.put("mobile", mobile);
                db.insert("order_table", null, values);

            }
            //标记事物成功
            db.setTransactionSuccessful();
        } finally {
            //结束事务
            db.endTransaction();
        }

        //关闭数据库
//        db.close();

    }
    /**
     * 根据用户名查询訂單
     */
    @SuppressLint("Range")
    public List<OrderInfo> queryOrderData(String username) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<OrderInfo> list = new ArrayList<>();
        String sql = "SELECT order_id, username, product_img, product_title, product_price, product_count,address,mobile FROM order_table WHERE username=? ";
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            int Order_id = cursor.getInt(cursor.getColumnIndex("order_id"));
            String Username = cursor.getString(cursor.getColumnIndex("username"));
            int Product_img = cursor.getInt(cursor.getColumnIndex("product_img"));
            String Product_title = cursor.getString(cursor.getColumnIndex("product_title"));
            int Product_price = cursor.getInt(cursor.getColumnIndex("product_price"));
            int Product_count = cursor.getInt(cursor.getColumnIndex("product_count"));
            String Address = cursor.getString(cursor.getColumnIndex("address"));
            String Mobile = cursor.getString(cursor.getColumnIndex("mobile"));
            list.add(new OrderInfo(Order_id, Username, Product_img, Product_title,Product_price,Product_count,Address,Mobile));
        }
        cursor.close();
//        db.close();
        return list;
    }
    /**
     * 删除订单商品
     */
    public int delete(String order_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("order_table", " order_id=?", new String[]{order_id});
        // 关闭数据库连接
//        db.close();
        return delete;
    }
}

