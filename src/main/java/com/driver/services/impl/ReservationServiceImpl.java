package com.driver.services.impl;

import com.driver.dto.responseDTO.ReservationResponse;
import com.driver.exception.ParkingLotNotFoundException;
import com.driver.exception.SpotNotFoundException;
import com.driver.exception.UserNotFoundException;
import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public ReservationResponse reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        /*
        * Check user is Valid,
        * Check Parking lot is Valid
        * check is spot occipied is false
        * check wheels and get price
        * calculate price
        * make DTP
        * return
        * */

        // Check User
        Optional<User> optionalUser =  userRepository3.findById(userId);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("reservation cannot be made");
        }
        User user = optionalUser.get();

        Optional<ParkingLot> optionalParkingLot = parkingLotRepository3.findById(parkingLotId);
        if(!optionalParkingLot.isPresent()){
            throw new ParkingLotNotFoundException("reservation cannot be made");
        }
        ParkingLot fetchedParkingLot  = optionalParkingLot.get();

        SpotType spotType;
        if(numberOfWheels >= 1 && numberOfWheels <=2){
            spotType = SpotType.TWO_WHEELER;
        } else if (numberOfWheels >= 3 && numberOfWheels <= 4) {
            spotType = SpotType.FOUR_WHEELER;
        }else spotType = SpotType.OTHERS;

        int id = 0, price = 0;
        boolean found = false;
        for (Spot sp: fetchedParkingLot.getSpots()) {
            if(sp.getSpotType().equals(spotType)){
                if(!sp.isOccupied()){
                  sp.setOccupied(true);
                  price = sp.getPricePerHour();
                  id = sp.getId();
                  found = true;
                  break;
                }
            }
        }
        if(!found) throw new SpotNotFoundException("reservation cannot be made");
        int total = price*timeInHours;

        Reservation reservation = new Reservation();
        reservation.setNoOfHours(timeInHours);
        reservation.setUser(user);
        reservation.setSpot(fetchedParkingLot.getSpots().get(id));
        Reservation savedReservation = reservationRepository3.save(reservation);

        Spot spot = savedReservation.getSpot();
        spot.getReservations().add(savedReservation);
        Spot savedSpot = spotRepository3.save(spot);

        User Reserveduser =  savedReservation.getUser();
        Reserveduser.getReservations().add(savedReservation);
        userRepository3.save(Reserveduser);

        // DTO

        ReservationResponse response = new ReservationResponse();
        response.setNoOfHrs(savedReservation.getNoOfHours());
        response.setParkingLotName(fetchedParkingLot.getName());
        response.setSpotType(String.valueOf(savedSpot.getSpotType()));
        response.setTotalCost(total);
        response.setParkingLotId(fetchedParkingLot.getId());
        return  response;
    }
}
