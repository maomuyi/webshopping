package com.lfy.lfy202120201124.entity;

public class OrderInfo {
    private int order_id;
    private String username;
    private int product_img;
    private String product_title;
    private int product_price;
    private int product_count;
    private String address;
    private String phone;

    public OrderInfo(int order_id, String username, int product_img, String product_title, int product_price, int product_count, String address, String phone) {
        this.order_id = order_id;
        this.username = username;
        this.product_img = product_img;
        this.product_title = product_title;
        this.product_price = product_price;
        this.product_count = product_count;
        this.address = address;
        this.phone = phone;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_count() {
        return product_count;
    }

    public void setProduct_count(int product_count) {
        this.product_count = product_count;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
