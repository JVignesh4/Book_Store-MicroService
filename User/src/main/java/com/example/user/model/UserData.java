package com.example.user.model;

import com.example.user.dto.UserDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "UserData")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;

    public UserData(int id, UserDTO userDTO) {
        this.userId = id;
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.address = userDTO.getAddress();
        this.password = userDTO.getPassword();
    }

    public UserData(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.address = userDTO.getAddress();
        this.password = userDTO.getPassword();
    }

    public UserData() {

    }
}
