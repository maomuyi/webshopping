package com.lfy.lfy202120201124.entity;

public class Comment {
    private String url;
    private String userName;
    private String content;
    private String timestamp;

    public Comment(String url, String userName, String content, String timestamp) {
        this.url = url;
        this.userName = userName;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
