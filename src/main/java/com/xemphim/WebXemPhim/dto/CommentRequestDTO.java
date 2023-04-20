package com.xemphim.WebXemPhim.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CommentRequestDTO {
    private int lever;
    private String content;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="Asia/Ho_Chi_Minh")
    private Date date;

    private String filmName;

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public int getLever() {
        return lever;
    }

    public void setLever(int lever) {
        this.lever = lever;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
