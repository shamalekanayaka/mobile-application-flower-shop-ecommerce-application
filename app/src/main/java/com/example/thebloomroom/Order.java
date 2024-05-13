package com.example.thebloomroom;

public class Order {
    String address,name,category,phone;

    public Order(){}

    public Order(String address, String name, String category, String phone) {
        this.address = address;
        this.name = name;
        this.category = category;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
