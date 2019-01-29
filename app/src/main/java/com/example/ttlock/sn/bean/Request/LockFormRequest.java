package com.example.ttlock.sn.bean.Request;

public class LockFormRequest {



    /**
     * code : string
     * name : string
     * alias : string
     * mac : string
     * state : IDLE
     * batteryCapacity:电量
     */

    private String code;
    private String name;
    private String alias;
    private String mac;
    private String state;
    private byte  batteryCapacity;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public byte getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(byte batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
