package com.driver.services.impl;

import com.driver.dto.responseDTO.UpdateSportResponse;
import com.driver.exception.ParkingLotNotFoundException;
import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setAddress(address);

        ParkingLot savedParkingLot = parkingLotRepository1.save(parkingLot);
        return savedParkingLot;
    }

    @Override
    public UpdateSportResponse addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        //Add a new spot within a specific parking lot with a given number of wheels and price per hour.
        // The spot type should be determined by the number of wheels (2 or 4 wheels for "car" and more than 4 wheels for "others").

        /* Logic Breakup
            1. check if parking id is valid or not, and through exception
            2. check no of wheels and decide the enum type with bike, car, and other.
            3. add new spot in sport table and add it in parking also.
        */
        Optional<ParkingLot> optionalParkingLot = parkingLotRepository1.findById(parkingLotId);
        if(!optionalParkingLot.isPresent()){
            throw new ParkingLotNotFoundException("Invalid Parking Id");
        }
        ParkingLot fetchedParkingLot  = optionalParkingLot.get();
        SpotType spotType;
        if(numberOfWheels == 2){
            spotType = SpotType.TWO_WHEELER;
        } else if (numberOfWheels == 4) {
            spotType = SpotType.TWO_WHEELER;
        }else spotType = SpotType.OTHERS;
        Spot newspot = new Spot();

        newspot.setParkingLot(fetchedParkingLot);
        newspot.setSpotType(spotType);
        newspot.setPricePerHour(pricePerHour);
        Spot SavedSpot = spotRepository1.save(newspot); // Saving new Spot

        fetchedParkingLot.getSpots().add(SavedSpot); // Adding Saved Spot to Corresponding Parking Lot
        ParkingLot savedparkinglot = parkingLotRepository1.save(fetchedParkingLot);

        UpdateSportResponse updateSportResponse = new UpdateSportResponse();
        updateSportResponse.setSpotId(SavedSpot.getId());
        updateSportResponse.setSpotType(String.valueOf(SavedSpot.getSpotType()));
        updateSportResponse.setParkingAddress(savedparkinglot.getAddress());
        updateSportResponse.setParkingLotName(fetchedParkingLot.getName());
        updateSportResponse.setPricePerHr(SavedSpot.getPricePerHour());
        return  updateSportResponse;
    }

    @Override
    public void deleteSpot(int spotId) {

    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        return null;
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {

    }
}
