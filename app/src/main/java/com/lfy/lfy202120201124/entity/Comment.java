package com.lfy.lfy202120201124.entity;

public class Comment {
    private String videoUrl;
    private String userName;
    private String content;

    public Comment(String videoUrl, String userName, String content) {
        this.videoUrl = videoUrl;
        this.userName = userName;
        this.content = content;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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
}
