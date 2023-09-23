package com.driver.dto.responseDTO;

public class ReservationResponse {
    private  int parkingLotId;
    private String parkingLotName;
    private  String SpotType;
    private int noOfHrs;
    private int totalCost;

    public ReservationResponse(int parkingLotId, String parkingLotName, String spotType, int noOfHrs, int totalCost) {
        this.parkingLotId = parkingLotId;
        this.parkingLotName = parkingLotName;
        SpotType = spotType;
        this.noOfHrs = noOfHrs;
        this.totalCost = totalCost;
    }

    public ReservationResponse() {
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public String getSpotType() {
        return SpotType;
    }

    public void setSpotType(String spotType) {
        SpotType = spotType;
    }

    public int getNoOfHrs() {
        return noOfHrs;
    }

    public void setNoOfHrs(int noOfHrs) {
        this.noOfHrs = noOfHrs;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
