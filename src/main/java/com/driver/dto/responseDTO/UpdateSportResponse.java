package com.driver.dto.responseDTO;

public class UpdateSportResponse {
    private  int spotId;

    private String spotType;
    private int pricePerHr;
    private String parkingLotName;
    private String parkingAddress;

    public UpdateSportResponse() {
    }

    public UpdateSportResponse(int spotId, String spotType, int pricePerHr, String parkingLotName, String parkingAddress) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.pricePerHr = pricePerHr;
        this.parkingLotName = parkingLotName;
        this.parkingAddress = parkingAddress;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }


    public String getSpotType() {
        return spotType;
    }

    public void setSpotType(String spotType) {
        this.spotType = spotType;
    }

    public int getPricePerHr() {
        return pricePerHr;
    }

    public void setPricePerHr(int pricePerHr) {
        this.pricePerHr = pricePerHr;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public String getParkingAddress() {
        return parkingAddress;
    }

    public void setParkingAddress(String parkingAddress) {
        this.parkingAddress = parkingAddress;
    }
}
