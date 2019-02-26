package com.example.ttlock.sn.bean.Responds;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jl on 2019/1/29.
 */

public class RoomSearchResponses implements Serializable {


    /**
     * depositAmount : 0
     * house : {"adcode":0,"address":"string","amenities":["WIFI"],"bedroomCount":"ONE","city":"string","contractId":0,"contractNo":"string","depositAmount":0,"description":"string","floor":"LOW_RISE","houseNo":"string","houseType":"VILLA","id":0,"latitude":"string","leaseType":"SHARE_HOUSE","longitude":"string","name":"string","numberPerson":0,"orientation":"NORTH","price":0,"priceHigh":0,"province":"string","region":"string","serialNumber":"string","state":"INITIAL","street":"string","topUrl":"string"}
     * leaseType : SHARE_HOUSE
     * lock : {"id":0,"code":"string","data":{"additionalProp1":"string","additionalProp2":"string","additionalProp3":"string"},"keyId":"string","name":"string","state":"IDLE","created":"2019-02-26T02:34:39.548Z","updated":"2019-02-26T02:34:39.548Z"}
     * lockViewList : [{"keyboardPwd":"string","keyboardPwdId":0,"lockId":0}]
     * name : string
     * numberPerson : 0
     * price : 0
     * priceHigh : 0
     * state : READY
     */

    private int depositAmount;
    private HouseBean house;
    private String leaseType;
    private LockBean lock;
    private String name;
    private int numberPerson;
    private int price;
    private int priceHigh;
    private String state;
    private List<LockViewListBean> lockViewList;

    public int getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(int depositAmount) {
        this.depositAmount = depositAmount;
    }

    public HouseBean getHouse() {
        return house;
    }

    public void setHouse(HouseBean house) {
        this.house = house;
    }

    public String getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(String leaseType) {
        this.leaseType = leaseType;
    }

    public LockBean getLock() {
        return lock;
    }

    public void setLock(LockBean lock) {
        this.lock = lock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberPerson() {
        return numberPerson;
    }

    public void setNumberPerson(int numberPerson) {
        this.numberPerson = numberPerson;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<LockViewListBean> getLockViewList() {
        return lockViewList;
    }

    public void setLockViewList(List<LockViewListBean> lockViewList) {
        this.lockViewList = lockViewList;
    }

    public static class HouseBean {
        /**
         * adcode : 0
         * address : string
         * amenities : ["WIFI"]
         * bedroomCount : ONE
         * city : string
         * contractId : 0
         * contractNo : string
         * depositAmount : 0
         * description : string
         * floor : LOW_RISE
         * houseNo : string
         * houseType : VILLA
         * id : 0
         * latitude : string
         * leaseType : SHARE_HOUSE
         * longitude : string
         * name : string
         * numberPerson : 0
         * orientation : NORTH
         * price : 0
         * priceHigh : 0
         * province : string
         * region : string
         * serialNumber : string
         * state : INITIAL
         * street : string
         * topUrl : string
         */

        private int adcode;
        private String address;
        private String bedroomCount;
        private String city;
        private int contractId;
        private String contractNo;
        private int depositAmount;
        private String description;
        private String floor;
        private String houseNo;
        private String houseType;
        private int id;
        private String latitude;
        private String leaseType;
        private String longitude;
        private String name;
        private int numberPerson;
        private String orientation;
        private int price;
        private int priceHigh;
        private String province;
        private String region;
        private String serialNumber;
        private String state;
        private String street;
        private String topUrl;
        private List<String> amenities;

        public int getAdcode() {
            return adcode;
        }

        public void setAdcode(int adcode) {
            this.adcode = adcode;
        }

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

        public int getContractId() {
            return contractId;
        }

        public void setContractId(int contractId) {
            this.contractId = contractId;
        }

        public String getContractNo() {
            return contractNo;
        }

        public void setContractNo(String contractNo) {
            this.contractNo = contractNo;
        }

        public int getDepositAmount() {
            return depositAmount;
        }

        public void setDepositAmount(int depositAmount) {
            this.depositAmount = depositAmount;
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

        public int getNumberPerson() {
            return numberPerson;
        }

        public void setNumberPerson(int numberPerson) {
            this.numberPerson = numberPerson;
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

        public String getTopUrl() {
            return topUrl;
        }

        public void setTopUrl(String topUrl) {
            this.topUrl = topUrl;
        }

        public List<String> getAmenities() {
            return amenities;
        }

        public void setAmenities(List<String> amenities) {
            this.amenities = amenities;
        }
    }

    public static class LockBean {
        /**
         * id : 0
         * code : string
         * data : {"additionalProp1":"string","additionalProp2":"string","additionalProp3":"string"}
         * keyId : string
         * name : string
         * state : IDLE
         * created : 2019-02-26T02:34:39.548Z
         * updated : 2019-02-26T02:34:39.548Z
         */

        private int id;
        private String code;
        private DataBean data;
        private String keyId;
        private String name;
        private String state;
        private String created;
        private String updated;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getKeyId() {
            return keyId;
        }

        public void setKeyId(String keyId) {
            this.keyId = keyId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public static class DataBean {
            /**
             * nbRssi : 0
             * lockKey : OCwxMywwLDEyLDE0LDExLDE1LDEsOCwxLDcw
             * lockMac : DC:D0:CF:B1:8B:CD
             * pwdInfo : W9dcNs6o3TXrXzA5pUHdHPFXYglxUInhm25G17r0OcG1qdyq+huGUU04lrPq92vdaFJ1iMisniQD7VPlFQX1bD4DTCePqpYlGvSCPWYJIvrVrS4nqgnR4ohm844NYymBrW7vJZuepphHOZBvd/IIzpgCZmrpQIqokn8eU7Zj/U7JSKYB+KSAJfIuNkC66gnJWLHUissLEHTlp4X/36oId0gM8/LMJSCcQ6nZzc+z0cbvt4XIes8KIHx3gIwhVPrQ2qXi8QzEMrOwJ+MDjILUDYKw2b0KrJ2lAFrQTWZKBugGyu8Ef0THaGKMpW+kQKJBNHGC7aBQCD+Pb/SyWh0mwfhYt6Z07S7t9S6UHIX2p5xn+RqmZoFFb5FJXzvr0xHyt/Nxdhj5O8SfPtXEKEPtaJ3IgG4RGv4IKEW6/CnLLEUcryRbFDoBoO45bjgMTEIWDjB3HG9HEOPXb0fRjAGaLM7IpCOVyw3jbqaQ36WMOMQrWrjCtbzmZnz9Utax6S0xZgVMjoCqW9owH+N9F3C43GSxMclH0uu0CdMF+b3vYcl08OhZczTx8QlsddVw00b4FxPKzsPurjCv6d1+xt08st+M2NjL5ltK2A3C5CU5QrE3runTpcElN3n0SXH9W583UmCSImLrpkntfGAulgg/bYEMijjWBe59L+3I5lifUAdn1ZCd093oytqqeThbEuPrCEhSCJCQlOuCis5N99eATvEfwCazdjQyOB4qaBTf04oRjPf8c0mXCrJYaXCjfEJXIrz22Q1hF8LvFIrrS9gS2D1C4JhaKJzpjhgvEPG+hVSSEKdZYpR6XMJRe0lfBsAuPJSxxw1ys+veCqitRE0WXXQof78tOme+Z2JsFF1X9A8=
             * adminPwd : NDEsMzMsNDQsNDIsNDAsMzMsNDQsNDMsNDMsNDYsMTAz
             * lockName : S202T_cd8bb1
             * modelNum : SN139-S202_PV53
             * noKeyPwd : 3526299
             * aesKeyStr : 2a,84,98,9a,ec,54,70,48,3d,54,81,7d,2a,f8,3c,4c
             * timestamp : 1550991990367
             * lockFlagPos : 0
             * lockVersion : {"groupId":1,"orgId":1,"protocolType":5,"protocolVersion":3,"scene":2}
             * specialValue : 20705
             * electricQuantity : 35
             * firmwareRevision : 4.1.18.0131
             * hardwareRevision : 1.1.1
             * timezoneRawOffset : 28800000
             */

            private String nbRssi;
            private String lockKey;
            private String lockMac;
            private String pwdInfo;
            private String adminPwd;
            private String lockName;
            private String modelNum;
            private String noKeyPwd;
            private String aesKeyStr;
            private String timestamp;
            private int lockFlagPos;
            private String lockVersion;
            private String specialValue;
            private String electricQuantity;
            private String firmwareRevision;
            private String hardwareRevision;
            private long timezoneRawOffset;

            public String getNbRssi() {
                return nbRssi;
            }

            public void setNbRssi(String nbRssi) {
                this.nbRssi = nbRssi;
            }

            public String getLockKey() {
                return lockKey;
            }

            public void setLockKey(String lockKey) {
                this.lockKey = lockKey;
            }

            public String getLockMac() {
                return lockMac;
            }

            public void setLockMac(String lockMac) {
                this.lockMac = lockMac;
            }

            public String getPwdInfo() {
                return pwdInfo;
            }

            public void setPwdInfo(String pwdInfo) {
                this.pwdInfo = pwdInfo;
            }

            public String getAdminPwd() {
                return adminPwd;
            }

            public void setAdminPwd(String adminPwd) {
                this.adminPwd = adminPwd;
            }

            public String getLockName() {
                return lockName;
            }

            public void setLockName(String lockName) {
                this.lockName = lockName;
            }

            public String getModelNum() {
                return modelNum;
            }

            public void setModelNum(String modelNum) {
                this.modelNum = modelNum;
            }

            public String getNoKeyPwd() {
                return noKeyPwd;
            }

            public void setNoKeyPwd(String noKeyPwd) {
                this.noKeyPwd = noKeyPwd;
            }

            public String getAesKeyStr() {
                return aesKeyStr;
            }

            public void setAesKeyStr(String aesKeyStr) {
                this.aesKeyStr = aesKeyStr;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public int getLockFlagPos() {
                return lockFlagPos;
            }

            public void setLockFlagPos(int lockFlagPos) {
                this.lockFlagPos = lockFlagPos;
            }

            public String getLockVersion() {
                return lockVersion;
            }

            public void setLockVersion(String lockVersion) {
                this.lockVersion = lockVersion;
            }

            public String getSpecialValue() {
                return specialValue;
            }

            public void setSpecialValue(String specialValue) {
                this.specialValue = specialValue;
            }

            public String getElectricQuantity() {
                return electricQuantity;
            }

            public void setElectricQuantity(String electricQuantity) {
                this.electricQuantity = electricQuantity;
            }

            public String getFirmwareRevision() {
                return firmwareRevision;
            }

            public void setFirmwareRevision(String firmwareRevision) {
                this.firmwareRevision = firmwareRevision;
            }

            public String getHardwareRevision() {
                return hardwareRevision;
            }

            public void setHardwareRevision(String hardwareRevision) {
                this.hardwareRevision = hardwareRevision;
            }

            public long getTimezoneRawOffset() {
                return timezoneRawOffset;
            }

            public void setTimezoneRawOffset(long timezoneRawOffset) {
                this.timezoneRawOffset = timezoneRawOffset;
            }
        }
    }

    public static class LockViewListBean {
        /**
         * keyboardPwd : string
         * keyboardPwdId : 0
         * lockId : 0
         */

        private String keyboardPwd;
        private int keyboardPwdId;
        private int lockId;

        public String getKeyboardPwd() {
            return keyboardPwd;
        }

        public void setKeyboardPwd(String keyboardPwd) {
            this.keyboardPwd = keyboardPwd;
        }

        public int getKeyboardPwdId() {
            return keyboardPwdId;
        }

        public void setKeyboardPwdId(int keyboardPwdId) {
            this.keyboardPwdId = keyboardPwdId;
        }

        public int getLockId() {
            return lockId;
        }

        public void setLockId(int lockId) {
            this.lockId = lockId;
        }
    }
}
