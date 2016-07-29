package com.example.administrator.safecook.domain;

/**
 * Created by Administrator on 2016/7/29.
 */
public class MessageItem {
    private String content;
    private String time;

    public MessageItem(String content, String time) {
        this.content = content;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MessageItem{" +
                "content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
