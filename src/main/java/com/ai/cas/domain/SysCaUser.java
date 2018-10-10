package com.ai.cas.domain;

import java.util.Date;

public class SysCaUser {
    private String password;
    private String causer;
    private Integer id;
    private String phoneNo;
    private String status;
    private Date lastLoginTime;
    private Integer excepNum;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCauser() {
        return causer;
    }

    public void setCauser(String causer) {
        this.causer = causer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getExcepNum() {
        return excepNum;
    }

    public void setExcepNum(Integer excepNum) {
        this.excepNum = excepNum;
    }
}
