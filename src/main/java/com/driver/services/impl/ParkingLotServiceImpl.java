package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import com.driver.transformer.ParkingLotTransformer;
import com.driver.transformer.SpotTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;

    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLotToBeSaved = ParkingLotTransformer.toParkingLot(name, address);
        return parkingLotRepository1.save(parkingLotToBeSaved);
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        Spot spotToBeSaved = SpotTransformer.toSpot(numberOfWheels, pricePerHour);

        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();

        parkingLot.getSpotList().add(spotToBeSaved);
        spotToBeSaved.setParkingLot(parkingLot);

        ParkingLot savedParkingLot = parkingLotRepository1.save(parkingLot);

        return spotToBeSaved;
    }

    @Override
    public void deleteSpot(int spotId) {
        spotRepository1.deleteById(spotId);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
        Spot spot = parkingLot.getSpotList().stream().filter(s -> s.getId() == spotId).findFirst().get();
        spot.setPricePerHour(pricePerHour);
        spotRepository1.save(spot);
        return spot;
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        parkingLotRepository1.deleteById(parkingLotId);
    }
}
