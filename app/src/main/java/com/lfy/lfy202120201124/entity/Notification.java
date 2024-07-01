// Notification.java
package com.lfy.lfy202120201124.entity;

public class Notification {
    private String title;
    private String date;
    private String content;

    public Notification(String title, String date, String content) {
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }
}
