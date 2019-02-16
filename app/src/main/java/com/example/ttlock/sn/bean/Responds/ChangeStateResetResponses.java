package com.example.ttlock.sn.bean.Responds;

public class ChangeStateResetResponses {


    /**
     * code : 0
     * data : {}
     * message : string
     * status : SUCCESS
     */

    private int code;
    private DataBean data;
    private String message;
    private String status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
    }
}
