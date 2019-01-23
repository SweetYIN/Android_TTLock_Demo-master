package com.example.ttlock.sn.bean.Request;

import java.util.List;

/**
 * Created by jl on 2019/1/17.
 */

public class HouseSearchRequestBean {


    /**
     * adcodes : ["string"]
     * bedroomCount : ONE
     * floor : LOW_RISE
     * houseType : VILLA
     * orientation : NORTH
     * paging : {"number":0,"size":0,"sort":["string"]}
     * roomState : READY
     * serialNumber : string
     * state : INITIAL
     */

    private String bedroomCount;
    private String floor;
    private String houseType;
    private String orientation;
    private PagingBean paging;
    private String roomState;
    private String serialNumber;
    private String state;
    private List<String> adcodes;

    public String getBedroomCount() {
        return bedroomCount;
    }

    public void setBedroomCount(String bedroomCount) {
        this.bedroomCount = bedroomCount;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public PagingBean getPaging() {
        return paging;
    }

    public void setPaging(PagingBean paging) {
        this.paging = paging;
    }

    public String getRoomState() {
        return roomState;
    }

    public void setRoomState(String roomState) {
        this.roomState = roomState;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getAdcodes() {
        return adcodes;
    }

    public void setAdcodes(List<String> adcodes) {
        this.adcodes = adcodes;
    }

    public static class PagingBean {
        /**
         * number : 0
         * size : 0
         * sort : ["string"]
         */

        private int number;
        private int size;
        private List<String> sort;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public List<String> getSort() {
            return sort;
        }

        public void setSort(List<String> sort) {
            this.sort = sort;
        }
    }
}
