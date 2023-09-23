package com.driver.services;


import com.driver.dto.responseDTO.UpdateUserResponse;
import com.driver.model.User;

public interface UserService {

	void deleteUser(Integer userId);
	UpdateUserResponse updatePassword(Integer userId, String password);
    void register(String name, String phoneNumber, String password);
}
