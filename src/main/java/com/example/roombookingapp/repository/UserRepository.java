package com.example.roombookingapp.repository;

import com.example.roombookingapp.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {

}
