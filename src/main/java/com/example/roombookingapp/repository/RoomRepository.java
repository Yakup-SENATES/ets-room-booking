package com.example.roombookingapp.repository;

import com.example.roombookingapp.model.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    
}
