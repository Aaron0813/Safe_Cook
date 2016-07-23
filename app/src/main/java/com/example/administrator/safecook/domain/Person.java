package com.example.administrator.safecook.domain;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/7/23.
 */
public class Person extends BmobObject {
    private String name;
    private String address;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
