package com.driver.controllers;

import com.driver.dto.responseDTO.UpdateSportResponse;
import com.driver.services.ParkingLotService;
import com.driver.services.impl.ParkingLotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.driver.model.*;

import java.util.List;

@RestController
@RequestMapping("/parking-lots")
public class ParkingLotController {

    //findById and deleteById should be used wherever necessary
    //findAll should never be used
    @Autowired
    ParkingLotService parkingLotService;

    @PostMapping("/add")
    public ResponseEntity<ParkingLot> addParkingLot(@RequestParam String name, @RequestParam String address) {
        //add a new parking lot to the database
        ParkingLot newParkingLot = parkingLotService.addParkingLot(name, address);
        return new ResponseEntity<>(newParkingLot, HttpStatus.CREATED);
    }

    @PostMapping("/{parkingLotId}/spot/add")
    public ResponseEntity addSpot(@PathVariable int parkingLotId, @RequestParam Integer numberOfWheels, @RequestParam Integer pricePerHour) {
        //create a new spot in the parkingLot with given id
        //the spot type should be the next biggest type in case the number of wheels are not 2 or 4, for 4+ wheels, it is others
        try{
            UpdateSportResponse newSpot =  parkingLotService.addSpot(parkingLotId,numberOfWheels,pricePerHour);
            return new ResponseEntity<>(newSpot, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("/spot/{spotId}/delete")
    public ResponseEntity deleteSpot(@PathVariable int spotId) {
        //delete a spot from given parking lot
        try {
            parkingLotService.deleteSpot(spotId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{parkingLotId}/spot/{spotId}/update")
    public ResponseEntity updateSpot(@PathVariable int parkingLotId, @PathVariable int spotId, @RequestParam int pricePerHour) {
        //update the details of a spot
        try{
            UpdateSportResponse updatedSpot = parkingLotService.updateSpot(parkingLotId,spotId,pricePerHour);
            return new ResponseEntity<>(updatedSpot, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{parkingLotId}/delete")
    public ResponseEntity deleteParkingLot(@PathVariable int parkingLotId) {
        //delete a parkingLot
        try {
            parkingLotService.deleteParkingLot(parkingLotId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/list/{id}")
    public List<String> namesofSpots(@PathVariable int id){
        return parkingLotService.namesofSpots(id);
    }

}
