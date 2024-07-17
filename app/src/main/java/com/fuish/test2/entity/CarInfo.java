package com.fuish.test2.entity;

public class CarInfo {
    private int car_id;
    private String username;
    private int product_id;
    private int product_img;
    private String product_title;
    private String product_price;
    private int Product_count;

    public CarInfo(int car_id, String username, int product_id, int product_img, String product_title, String product_price, int product_count) {
        this.car_id = car_id;
        this.username = username;
        this.product_id = product_id;
        this.product_img = product_img;
        this.product_title = product_title;
        this.product_price = product_price;
        this.Product_count = product_count;
    }

    public int getProduct_count() {
        return Product_count;
    }

    public void setProduct_count(int product_count) {
        this.Product_count = product_count;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_img() {
        return product_img;
    }

    public void setProduct_img(int product_img) {
        this.product_img = product_img;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }
}
