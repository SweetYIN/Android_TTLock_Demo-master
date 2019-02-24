package com.example.ttlock.sn.bean.Request;

import com.ttlock.bl.sdk.entity.LockData;

public class LockFormRequest {


    /**
     * data : {"lockDataJson":"string"}
     * lockAlias : string
     */

    private LockData data;
    private String lockAlias;

    public LockData getData() {
        return data;
    }

    public void setData(LockData data) {
        this.data = data;
    }

    public String getLockAlias() {
        return lockAlias;
    }

    public void setLockAlias(String lockAlias) {
        this.lockAlias = lockAlias;
    }


}
