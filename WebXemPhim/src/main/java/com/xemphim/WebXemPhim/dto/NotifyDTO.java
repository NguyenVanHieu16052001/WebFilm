package com.xemphim.WebXemPhim.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class NotifyDTO {
    private String image;
    private String notifications;
    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH-mm-ss",timezone="Asia/Ho_Chi_Minh")
    private Date release_Days;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNotifications() {
        return notifications;
    }

    public void setNotifications(String notifications) {
        this.notifications = notifications;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRelease_Days() {
        return release_Days;
    }

    public void setRelease_Days(Date release_Days) {
        this.release_Days = release_Days;
    }
}
