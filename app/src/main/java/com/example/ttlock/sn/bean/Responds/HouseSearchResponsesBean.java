package com.example.ttlock.sn.bean.Responds;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jl on 2019/1/17.
 */

public class HouseSearchResponsesBean implements Serializable {

    /**
     * data : [{"address":"string","amenities":["WIFI"],
     * "bedroomCount":"ONE","city":"string",
     * "contractNo":"string","description":"string",
     * "floor":"LOW_RISE","houseNo":"string",
     * "houseType":"VILLA","id":0,"latitude":"string",
     * "leaseType":"SHARE_HOUSE","longitude":"string",
     * "name":"string","orientation":"NORTH",
     * "price":0,"priceHigh":0,"province":"string",
     * "region":"string","serialNumber":"string","state":"INITIAL","street":"string"}]
     * total : 0
     */

    private int total;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address : string
         * amenities : ["WIFI"]
         * bedroomCount : ONE
         * city : string
         * contractNo : string
         * description : string
         * floor : LOW_RISE
         * houseNo : string
         * houseType : VILLA
         * id : 0
         * latitude : string
         * leaseType : SHARE_HOUSE
         * longitude : string
         * name : string
         * orientation : NORTH
         * price : 0
         * priceHigh : 0
         * province : string
         * region : string
         * serialNumber : string
         * state : INITIAL
         * street : string
         */

        private String address;
        private String bedroomCount;
        private String city;
        private String contractNo;
        private String description;
        private String floor;
        private String houseNo;
        private String houseType;
        private int id;
        private String latitude;
        private String leaseType;
        private String longitude;
        private String name;
        private String orientation;
        private int price;
        private int priceHigh;
        private String province;
        private String region;
        private String serialNumber;
        private String state;
        private String street;
        private List<String> amenities;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBedroomCount() {
            return bedroomCount;
        }

        public void setBedroomCount(String bedroomCount) {
            this.bedroomCount = bedroomCount;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getContractNo() {
            return contractNo;
        }

        public void setContractNo(String contractNo) {
            this.contractNo = contractNo;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getHouseNo() {
            return houseNo;
        }

        public void setHouseNo(String houseNo) {
            this.houseNo = houseNo;
        }

        public String getHouseType() {
            return houseType;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLeaseType() {
            return leaseType;
        }

        public void setLeaseType(String leaseType) {
            this.leaseType = leaseType;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrientation() {
            return orientation;
        }

        public void setOrientation(String orientation) {
            this.orientation = orientation;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getPriceHigh() {
            return priceHigh;
        }

        public void setPriceHigh(int priceHigh) {
            this.priceHigh = priceHigh;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
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

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public List<String> getAmenities() {
            return amenities;
        }

        public void setAmenities(List<String> amenities) {
            this.amenities = amenities;
        }
    }
}
