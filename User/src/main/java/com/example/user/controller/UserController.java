package com.example.user.controller;

import com.example.user.dto.LoginDTO;
import com.example.user.dto.ResponseDTO;
import com.example.user.dto.UserDTO;
import com.example.user.model.UserData;
import com.example.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addUserRegistration(@Valid @RequestBody UserDTO userDTO) {
        String userRegistrationData = userService.createUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Created UserRegistration Data Succesfully", userRegistrationData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }


    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        List<UserData> userData = userService.getAllUsers();
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success", userData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }


    @GetMapping("/getby/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable int id) {
        UserData userData = userService.getUserById(id);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success For Id Successfully", userData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseDTO> getUserByEmail(@PathVariable String email) {
        Optional<UserData> userData = userService.getUserByEmail(email);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success For Id Successfully", userData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        Optional<UserData> userData = userService.login(loginDTO);
        if(userData != null) {
            ResponseDTO responseDTO = new ResponseDTO("Login Succesfully", userData);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.ACCEPTED);
        } else {
            ResponseDTO responseDTO = new ResponseDTO("login Failed", userData);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.ACCEPTED);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable int id, @Valid @RequestBody UserDTO userDTO) {
        UserData userData = userService.updateUser(id, userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated User Data Successfully", userData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

}
