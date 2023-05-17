package com.xemphim.WebXemPhim.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CommentRequestDTO {
    private String filmName;
    private String content;
    private int parentCommentID;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="Asia/Ho_Chi_Minh")
    private Date date;

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
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

    public int getParentCommentID() {
        return parentCommentID;
    }

    public void setParentCommentID(int parentCommentID) {
        this.parentCommentID = parentCommentID;
    }
}
