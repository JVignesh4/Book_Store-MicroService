package com.example.user.service;

import com.example.user.dto.LoginDTO;
import com.example.user.dto.UserDTO;
import com.example.user.model.UserData;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    String createUser(UserDTO userDTO);

    List<UserData> getAllUsers();

    UserData getUserById(int id);

    Optional<UserData> getUserByEmail(String email);

    UserData updateUser(int id, UserDTO userDTO);

    Optional<UserData> login(LoginDTO loginDTO);


}
