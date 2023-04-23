package com.xemphim.WebXemPhim.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;

import java.util.Date;

public class ChangeInfoRequestDTO {
    private String userName;
    private String email;
    private String phoneNumber;
    private Integer sex;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="Asia/Ho_Chi_Minh")
    private Date birthDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birtDate) {
        this.birthDate = birtDate;
    }
}
