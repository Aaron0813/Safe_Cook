package com.example.administrator.safecook.domain;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/7/24.
 */
public class Message extends BmobObject {
    private String content;
    private String data;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
