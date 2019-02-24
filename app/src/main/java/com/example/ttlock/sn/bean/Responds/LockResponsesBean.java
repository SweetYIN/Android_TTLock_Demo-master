package com.example.ttlock.sn.bean.Responds;

public class LockResponsesBean {


    /**
     * id : 23
     * created : 2019-02-24T07:06:33.181
     * updated : 2019-02-24T07:06:33.181
     * code : 1194550
     * name : 1234
     * state : EFFECTIVE
     * keyId : 3285246
     * data : {"nbRssi":"0","lockKey":"OCwxMywwLDEyLDE0LDExLDE1LDEsOCwxLDcw","lockMac":"DC:D0:CF:B1:8B:CD","pwdInfo":"W9dcNs6o3TXrXzA5pUHdHPFXYglxUInhm25G17r0OcG1qdyq+huGUU04lrPq92vdaFJ1iMisniQD7VPlFQX1bD4DTCePqpYlGvSCPWYJIvrVrS4nqgnR4ohm844NYymBrW7vJZuepphHOZBvd/IIzpgCZmrpQIqokn8eU7Zj/U7JSKYB+KSAJfIuNkC66gnJWLHUissLEHTlp4X/36oId0gM8/LMJSCcQ6nZzc+z0cbvt4XIes8KIHx3gIwhVPrQ2qXi8QzEMrOwJ+MDjILUDYKw2b0KrJ2lAFrQTWZKBugGyu8Ef0THaGKMpW+kQKJBNHGC7aBQCD+Pb/SyWh0mwfhYt6Z07S7t9S6UHIX2p5xn+RqmZoFFb5FJXzvr0xHyt/Nxdhj5O8SfPtXEKEPtaJ3IgG4RGv4IKEW6/CnLLEUcryRbFDoBoO45bjgMTEIWDjB3HG9HEOPXb0fRjAGaLM7IpCOVyw3jbqaQ36WMOMQrWrjCtbzmZnz9Utax6S0xZgVMjoCqW9owH+N9F3C43GSxMclH0uu0CdMF+b3vYcl08OhZczTx8QlsddVw00b4FxPKzsPurjCv6d1+xt08st+M2NjL5ltK2A3C5CU5QrE3runTpcElN3n0SXH9W583UmCSImLrpkntfGAulgg/bYEMijjWBe59L+3I5lifUAdn1ZCd093oytqqeThbEuPrCEhSCJCQlOuCis5N99eATvEfwCazdjQyOB4qaBTf04oRjPf8c0mXCrJYaXCjfEJXIrz22Q1hF8LvFIrrS9gS2D1C4JhaKJzpjhgvEPG+hVSSEKdZYpR6XMJRe0lfBsAuPJSxxw1ys+veCqitRE0WXXQof78tOme+Z2JsFF1X9A8=","adminPwd":"NDEsMzMsNDQsNDIsNDAsMzMsNDQsNDMsNDMsNDYsMTAz","lockName":"S202T_cd8bb1","modelNum":"SN139-S202_PV53","noKeyPwd":"3526299","aesKeyStr":"2a,84,98,9a,ec,54,70,48,3d,54,81,7d,2a,f8,3c,4c","timestamp":"1550991990367","lockFlagPos":"0","lockVersion":"{\"groupId\":1,\"orgId\":1,\"protocolType\":5,\"protocolVersion\":3,\"scene\":2}","specialValue":"20705","electricQuantity":"35","firmwareRevision":"4.1.18.0131","hardwareRevision":"1.1.1","timezoneRawOffset":"28800000"}
     */

    private int id;
    private String created;
    private String updated;
    private String code;
    private String name;
    private String state;
    private String keyId;
    private DataBean data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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
