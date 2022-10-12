package com.example.user.service;

import com.example.user.dto.LoginDTO;
import com.example.user.dto.UserDTO;
import com.example.user.exception.UserException;
import com.example.user.model.UserData;
import com.example.user.repository.UserRepository;
import com.example.user.util.EmailSenderService;
import com.example.user.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserService{


    @Autowired
    private UserRepository userRepo;
    @Autowired
    EmailSenderService mailService;

    @Autowired
    TokenUtil util;

    public String createUser(UserDTO userDTO) {
        UserData newUser = new UserData(userDTO);
        userRepo.save(newUser);
        String token = util.createToken(newUser.getUserId());
        mailService.sendEmail(userDTO.getEmail(), "Account Sign-up successfully", "Hello" + newUser.getFirstName() + " Your Account has been created.Your token is " + token + " Keep this token safe to access your account in future ");
        return token;
    }

    public Optional<UserData> login(LoginDTO logindto) {
        Optional<UserData> newUser = userRepo.findByMail(logindto.getEmail());
        if (logindto.getEmail().equals(newUser.get().getEmail()) && logindto.getPassword().equals(newUser.get().getPassword())) {
            log.info("SuccessFully Logged In");
            return newUser;
        } else {
            throw new UserException("User doesn't exists");

        }
    }
    public UserData getUserById(int id) {
        Optional<UserData> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new UserException("User Record doesn't exists");
        } else {
            log.info("Record retrieved successfully for id " + id);
            return user.get();
        }
    }

    public UserData updateUser(int id, UserDTO userDTO) {
        Optional<UserData> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new UserException("User Record doesn't exists");
        } else {
            UserData newUser = new UserData(id, userDTO);
            userRepo.save(newUser);
            log.info("User data updated successfully");
            return newUser;
        }
    }

    public Optional<UserData> getUserByEmail(String email) {
        Optional<UserData> newUser = userRepo.findByMail(email);
        if (newUser.isEmpty()) {
            throw new UserException("User record does not exist");
        } else {
            return Optional.of(newUser.get());
        }
    }

    public List<UserData> getAllUsers() {
        List<UserData> userList = userRepo.findAll();
        log.info("All Record Retrieved Successfully");
        return userList;
    }
}
