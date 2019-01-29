package com.example.ttlock.sn.bean.Request;

/**
 * Created by jl on 2019/1/29.
 */

public class RoomSearchRequest {

    /**
     * houseId : 0
     * leaseType : SHARE_HOUSE
     * roomState : READY
     */

    private int houseId;
    private String leaseType;
    private String roomState;

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(String leaseType) {
        this.leaseType = leaseType;
    }

    public String getRoomState() {
        return roomState;
    }

    public void setRoomState(String roomState) {
        this.roomState = roomState;
    }
}
