package com.example.ttlock.sn.bean.Responds;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jl on 2019/1/29.
 */

public class RoomSearchResponses {

    /**
     * id : 0
     * house : {"id":0,"adcode":0,"address":"string","amenities":["string"],"bedroomCount":"ONE","city":"string","contract":{"id":0,"address":"string","endDate":"string","houseNo":"string","ownerCertificateNumber":"string","ownerCertificateType":"IDCARD","ownerId":0,"ownerIncomeRatio":0,"ownerMobile":"string","ownerName":"string","serialNo":"string","startDate":"string","created":"2019-01-29T06:20:35.070Z","updated":"2019-01-29T06:20:35.070Z"},"description":"string","floor":"LOW_RISE","houseNo":"string","houseType":"VILLA","latitude":"string","leaseType":"SHARE_HOUSE","longitude":"string","name":"string","orientation":"NORTH","owner":{"id":0,"certificateNumber":"string","certificateType":"IDCARD","mobile":"string","name":"string","created":"2019-01-29T06:20:35.070Z","updated":"2019-01-29T06:20:35.070Z"},"province":"string","region":"string","serialNumber":"string","state":"INITIAL","street":"string","created":"2019-01-29T06:20:35.070Z","updated":"2019-01-29T06:20:35.070Z"}
     * leaseType : SHARE_HOUSE
     * lock : {"id":0,"alias":"string","code":"string","mac":"string","name":"string","quantity":"string","state":"IDLE","created":"2019-01-29T06:20:35.070Z","updated":"2019-01-29T06:20:35.070Z"}
     * name : string
     * price : 0
     * priceHigh : 0
     * state : READY
     * created : 2019-01-29T06:20:35.070Z
     * updated : 2019-01-29T06:20:35.070Z
     */

    private int id;
    private HouseBean house;
    private String leaseType;
    private LockBean lock;
    private String name;
    private int price;
    private int priceHigh;
    private String state;
    private String created;
    private String updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public static class HouseBean {
        /**
         * id : 0
         * adcode : 0
         * address : string
         * amenities : ["string"]
         * bedroomCount : ONE
         * city : string
         * contract : {"id":0,"address":"string","endDate":"string","houseNo":"string","ownerCertificateNumber":"string","ownerCertificateType":"IDCARD","ownerId":0,"ownerIncomeRatio":0,"ownerMobile":"string","ownerName":"string","serialNo":"string","startDate":"string","created":"2019-01-29T06:20:35.070Z","updated":"2019-01-29T06:20:35.070Z"}
         * description : string
         * floor : LOW_RISE
         * houseNo : string
         * houseType : VILLA
         * latitude : string
         * leaseType : SHARE_HOUSE
         * longitude : string
         * name : string
         * orientation : NORTH
         * owner : {"id":0,"certificateNumber":"string","certificateType":"IDCARD","mobile":"string","name":"string","created":"2019-01-29T06:20:35.070Z","updated":"2019-01-29T06:20:35.070Z"}
         * province : string
         * region : string
         * serialNumber : string
         * state : INITIAL
         * street : string
         * created : 2019-01-29T06:20:35.070Z
         * updated : 2019-01-29T06:20:35.070Z
         */

        private int id;
        private int adcode;
        private String address;
        private String bedroomCount;
        private String city;
        private ContractBean contract;
        private String description;
        private String floor;
        private String houseNo;
        private String houseType;
        private String latitude;
        private String leaseType;
        private String longitude;
        private String name;
        private String orientation;
        private OwnerBean owner;
        private String province;
        private String region;
        private String serialNumber;
        private String state;
        private String street;
        private String created;
        private String updated;
        private List<String> amenities;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public ContractBean getContract() {
            return contract;
        }

        public void setContract(ContractBean contract) {
            this.contract = contract;
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

        public OwnerBean getOwner() {
            return owner;
        }

        public void setOwner(OwnerBean owner) {
            this.owner = owner;
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

        public List<String> getAmenities() {
            return amenities;
        }

        public void setAmenities(List<String> amenities) {
            this.amenities = amenities;
        }

        public static class ContractBean {
            /**
             * id : 0
             * address : string
             * endDate : string
             * houseNo : string
             * ownerCertificateNumber : string
             * ownerCertificateType : IDCARD
             * ownerId : 0
             * ownerIncomeRatio : 0
             * ownerMobile : string
             * ownerName : string
             * serialNo : string
             * startDate : string
             * created : 2019-01-29T06:20:35.070Z
             * updated : 2019-01-29T06:20:35.070Z
             */

            private int id;
            private String address;
            private String endDate;
            private String houseNo;
            private String ownerCertificateNumber;
            private String ownerCertificateType;
            private int ownerId;
            private BigDecimal ownerIncomeRatio;
            private String ownerMobile;
            private String ownerName;
            private String serialNo;
            private String startDate;
            private String created;
            private String updated;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public String getHouseNo() {
                return houseNo;
            }

            public void setHouseNo(String houseNo) {
                this.houseNo = houseNo;
            }

            public String getOwnerCertificateNumber() {
                return ownerCertificateNumber;
            }

            public void setOwnerCertificateNumber(String ownerCertificateNumber) {
                this.ownerCertificateNumber = ownerCertificateNumber;
            }

            public String getOwnerCertificateType() {
                return ownerCertificateType;
            }

            public void setOwnerCertificateType(String ownerCertificateType) {
                this.ownerCertificateType = ownerCertificateType;
            }

            public int getOwnerId() {
                return ownerId;
            }

            public void setOwnerId(int ownerId) {
                this.ownerId = ownerId;
            }

            public BigDecimal getOwnerIncomeRatio() {
                return ownerIncomeRatio;
            }

            public void setOwnerIncomeRatio(BigDecimal ownerIncomeRatio) {
                this.ownerIncomeRatio = ownerIncomeRatio;
            }

            public String getOwnerMobile() {
                return ownerMobile;
            }

            public void setOwnerMobile(String ownerMobile) {
                this.ownerMobile = ownerMobile;
            }

            public String getOwnerName() {
                return ownerName;
            }

            public void setOwnerName(String ownerName) {
                this.ownerName = ownerName;
            }

            public String getSerialNo() {
                return serialNo;
            }

            public void setSerialNo(String serialNo) {
                this.serialNo = serialNo;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
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
        }

        public static class OwnerBean {
            /**
             * id : 0
             * certificateNumber : string
             * certificateType : IDCARD
             * mobile : string
             * name : string
             * created : 2019-01-29T06:20:35.070Z
             * updated : 2019-01-29T06:20:35.070Z
             */

            private int id;
            private String certificateNumber;
            private String certificateType;
            private String mobile;
            private String name;
            private String created;
            private String updated;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCertificateNumber() {
                return certificateNumber;
            }

            public void setCertificateNumber(String certificateNumber) {
                this.certificateNumber = certificateNumber;
            }

            public String getCertificateType() {
                return certificateType;
            }

            public void setCertificateType(String certificateType) {
                this.certificateType = certificateType;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
        }
    }

    public static class LockBean {
        /**
         * id : 0
         * alias : string
         * code : string
         * mac : string
         * name : string
         * quantity : string
         * state : IDLE
         * created : 2019-01-29T06:20:35.070Z
         * updated : 2019-01-29T06:20:35.070Z
         */

        private int id;
        private String alias;
        private String code;
        private String mac;
        private String name;
        private String quantity;
        private String state;
        private String created;
        private String updated;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
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
    }
}
