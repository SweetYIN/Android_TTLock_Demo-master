package com.example.ttlock.sn.bean;

/**
 * Created by jl on 2019/1/9.
 */

public class HouseInfo {

    private String houseName ;
    private String houseAddress;
    private String houseType;

    public HouseInfo(String houseName, String houseAddress, String houseType) {
        this.houseName = houseName;
        this.houseAddress = houseAddress;
        this.houseType = houseType;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }
}
