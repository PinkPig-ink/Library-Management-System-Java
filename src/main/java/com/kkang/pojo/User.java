package com.kkang.pojo;

public class User {
    private String userId;
    private String getTime;
    private String userName;
    private String userTel;
    private String idFlag;

    public User() {
    }

    public User(String userId, String getTime, String userName, String userTel, String idFlag) {
        this.userId = userId;
        this.getTime = getTime;
        this.userName = userName;
        this.userTel = userTel;
        this.idFlag = idFlag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getIdFlag() {
        return idFlag;
    }

    public void setIdFlag(String idFlag) {
        this.idFlag = idFlag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", getTime='" + getTime + '\'' +
                ", userName='" + userName + '\'' +
                ", userTel='" + userTel + '\'' +
                ", idFlag='" + idFlag + '\'' +
                '}';
    }
}
