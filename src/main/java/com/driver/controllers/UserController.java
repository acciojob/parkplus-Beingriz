package com.driver.controllers;
import com.driver.dto.responseDTO.UpdateUserResponse;
import com.driver.model.User;
import com.driver.services.UserService;
import com.driver.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestParam String name, @RequestParam String phoneNumber, @RequestParam String password){
        userService.register(name,phoneNumber,password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updatePassword(@RequestParam Integer userId, @RequestParam String password){
       try{
           UpdateUserResponse updatedUser = userService.updatePassword(userId,password);
           return new ResponseEntity<>(updatedUser, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
       }

    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestParam Integer userId){

        try{
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }
}
