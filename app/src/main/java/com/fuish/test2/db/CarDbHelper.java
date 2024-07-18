package com.fuish.test2.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.fuish.test2.entity.CarInfo;

import java.util.ArrayList;
import java.util.List;

public class CarDbHelper extends SQLiteOpenHelper {
    private static CarDbHelper sHelper;
    private static final String DB_NAME = "Car_info.db";   //数据库名
    private static final int VERSION = 2;    //版本号

    //必须实现其中一个构方法
    public CarDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static CarDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new CarDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("CarDbHelper", "Creating Car_table");
        //创建Car_table表
        db.execSQL("create table Car_table(_id integer primary key autoincrement, " +
                "username text," +       //用户名
                "product_id integer," +
                "product_img integer," +
                "product_title text," +
                "product_price integer," +
                "product_count integer" +
                ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i < i1) {
            Log.d("CarDbHelper1", "Upgrading database from version " + i + " to " + i1);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Car_table");
            onCreate(sqLiteDatabase);
        }
    }

    /**
     *添加商品到购物车
     */
    public int addCar(String username, int product_id,  int product_img,String product_title,String product_price) {
        Log.d("CarDbHelper2", "Adding car for user: " + username);
        //判断是否添加过商品，如果添加过,只修改商品数量即可
        CarInfo addCar = isAddCar(username, product_id);
        if (addCar == null) {
            //获取SQLiteDatabase实例
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            //填充占位符
            values.put("username", username);
            values.put("product_id", product_id);
            values.put("product_img", product_img);
            values.put("product_title", product_title);
            values.put("product_price", product_price);
            values.put("product_count", 1);
            String nullColumnHack = "values(null,?,?,?,?,?,?)";

            //执行
            int insert = (int) db.insert("Car_table", nullColumnHack, values);
            Log.d("CarDbHelper3", "Inserted car with ID: " + insert);
            db.close();
            return insert;
        }else {
            return updateProduct(addCar.getCar_id(),addCar);
        }

    }
/**
 * 修改购物车
 */
 public int updateProduct(int car_id,CarInfo carInfo) {
    //获取SQLiteDatabase实例
    SQLiteDatabase db = getWritableDatabase();
    // 填充占位符
    ContentValues values = new ContentValues();
    values.put("product_count",carInfo.getProduct_count()+1);
    // 执行SQL
    int update = db.update("Car_table", values, " _id=?", new String[]{car_id+""});
    // 关闭数据库连接
    db.close();
    return update;

}

/**
 * 根据用户名和商品ID判断有没有添加过商品
 */
@SuppressLint("Range")
public CarInfo isAddCar(String username,int product_id) {
    //获取SQLiteDatabase实例
    SQLiteDatabase db = getReadableDatabase();
    CarInfo carInfo = null;
    String sql = "SELECT _id, username, product_id, product_img, product_title, product_price, product_count FROM Car_table WHERE username=? AND product_id=?";
    String[] selectionArgs = {username,String.valueOf(product_id)};
    Cursor cursor = db.rawQuery(sql, selectionArgs);
    try {
        if (cursor.moveToNext()) {
            int car_id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("username"));
            int Product_id = cursor.getInt(cursor.getColumnIndex("product_id"));
            int Product_img = cursor.getInt(cursor.getColumnIndex("product_img"));
            String Product_title = cursor.getString(cursor.getColumnIndex("product_title"));
            String Product_price = cursor.getString(cursor.getColumnIndex("product_price"));
            int Product_count = cursor.getInt(cursor.getColumnIndex("product_count"));

            carInfo = new CarInfo(car_id, name, Product_id, Product_img, Product_title, Product_price, Product_count);
        }
    }finally {
        // 确保Cursor和Database都被关闭，即使在发生异常时也是如此
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        if (db.isOpen()) {
//            db.close();
        }
    }
    return carInfo;
}

    /**
     * 根据用户名查询购物车
     * @return
     */
    @SuppressLint("Range")
    public List<CarInfo> queryCarList(String username) {
        Log.d("CarDbHelper5", "Querying car list for user: " + username);
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<CarInfo> list = new ArrayList<>();
        String sql = "SELECT _id, username, product_id, product_img, product_title, product_price, product_count FROM Car_table WHERE username=? ";
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Log.d("CarDbHelper6", "Retrieved car with ID: " + cursor.getInt(cursor.getColumnIndex("_id")));
            int car_id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("username"));
            int Product_id = cursor.getInt(cursor.getColumnIndex("product_id"));
            int Product_img = cursor.getInt(cursor.getColumnIndex("product_img"));
            String Product_title = cursor.getString(cursor.getColumnIndex("product_title"));
            String Product_price = cursor.getString(cursor.getColumnIndex("product_price"));
            int Product_count = cursor.getInt(cursor.getColumnIndex("product_count"));
            list.add(new CarInfo(car_id, name, Product_id, Product_img, Product_title, Product_price, Product_count));
            Log.d("CarDbHelper7", "Retrieved car with ID: " + car_id);
        }
        cursor.close();
        db.close();
        return list;
    }

}
