package com.driver.dto.responseDTO;

public class UpdateUserResponse {
    private int userId;
    private String name;
    private String phoneNo;
    private String password;

    public UpdateUserResponse() {
    }

    public UpdateUserResponse(int userId, String name, String phoneNo, String password) {
        this.userId = userId;
        this.name = name;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
