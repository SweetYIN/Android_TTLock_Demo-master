package com.example.ttlock.sn.bean.Request;

/**
 * Created by jl on 2019/1/12.
 */

public class LoginRequestBean {

    private String username;

    private String password;

    private String grant_type = "password";

    private String scope = "all";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
