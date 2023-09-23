package com.driver.services.impl;

import com.driver.dto.responseDTO.UpdateUserResponse;
import com.driver.exception.UserNotFoundException;
import com.driver.model.User;
import com.driver.repository.UserRepository;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository4;
    @Override
    public void deleteUser(Integer userId) {
        Optional<User> optionalUser =  userRepository4.findById(userId);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("Invalid User Id");
        }
        userRepository4.deleteById(userId);

    }

    @Override
    public UpdateUserResponse updatePassword(Integer userId, String password) {
        Optional<User> optionalUser =  userRepository4.findById(userId);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("Invalid User Id");
        }
        User user = optionalUser.get();
        user.setPassword(password);
        user = userRepository4.save(user);

//        DTO
        UpdateUserResponse response = new UpdateUserResponse();
        response.setUserId(user.getId());
        response.setName(user.getName());
        response.setPassword(user.getPassword());
        response.setPhoneNo(user.getPhoneNo());
        return response;
    }

    @Override
    public void register(String name, String phoneNumber, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setPhoneNo(phoneNumber);
        userRepository4.save(user);
    }
}
