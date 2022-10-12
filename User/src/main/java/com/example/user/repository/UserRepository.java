package com.example.user.repository;

import com.example.user.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserData, Integer> {


    Optional<UserData> findByEmailAndPassword(String email, String password);

    Optional<UserData> findByMail(String email);

}
