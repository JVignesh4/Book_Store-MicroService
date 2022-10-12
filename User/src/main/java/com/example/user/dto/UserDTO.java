package com.example.user.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Data
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "FirstName is Invalid")
    private String firstName;


    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "LastName is Invalid")
    private String lastName;

    private String email;

    private String address;

    private String password;
}