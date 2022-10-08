package com.example.roombookingapp.service;

import com.example.roombookingapp.model.Layout;
import com.example.roombookingapp.model.entities.Booking;
import com.example.roombookingapp.model.entities.LayoutCapacity;
import com.example.roombookingapp.model.entities.Room;
import com.example.roombookingapp.model.entities.User;
import com.example.roombookingapp.repository.BookingRepository;
import com.example.roombookingapp.repository.RoomRepository;
import com.example.roombookingapp.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataInitialization {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public DataInitialization(RoomRepository roomRepository, UserRepository userRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {

        List<Room> rooms = roomRepository.findAll();
        if (rooms.isEmpty()) {
            Room blueRooms = new Room("Blue meeting Room", "1st floor");
            blueRooms.setCapacity(new LayoutCapacity(Layout.THEATER, 8));
            blueRooms.setCapacity(new LayoutCapacity(Layout.USHAPE, 16));
            roomRepository.save(blueRooms);


            Room redRoom = new Room("Red meeting Room", "2nd floor");
            redRoom.setCapacity(new LayoutCapacity(Layout.BOARD, 12));
            redRoom.setCapacity(new LayoutCapacity(Layout.USHAPE, 26));
            roomRepository.save(redRoom);


            Room confRoom = new Room("Main Conference Room", "3rd floor");
            confRoom.setCapacity(new LayoutCapacity(Layout.BOARD, 80));
            confRoom.setCapacity(new LayoutCapacity(Layout.USHAPE, 40));
            roomRepository.save(confRoom);

            User user = new User("John", "secret");
            userRepository.save(user);

            Booking b1 = new Booking();
            b1.setDate(new java.sql.Date(new java.util.Date().getTime()));
            b1.setStartTime(java.sql.Time.valueOf("11:00:00"));
            b1.setEndTime(java.sql.Time.valueOf("11:30:00"));
            b1.setLayout(Layout.USHAPE);
            b1.setParticipants(8);
            b1.setTitle("Conference with Ceo");
            b1.setRoom(blueRooms);
            b1.setUser(user);
            bookingRepository.save(b1);

            Booking b2 = new Booking();
            b2.setDate(new java.sql.Date(new java.util.Date().getTime()));
            b2.setStartTime(java.sql.Time.valueOf("13:00:00"));
            b2.setEndTime(java.sql.Time.valueOf("14:30:00"));
            b2.setLayout(Layout.BOARD);
            b2.setParticipants(8);
            b2.setTitle("Sales Update");
            b2.setRoom(redRoom);
            b2.setUser(user);
            bookingRepository.save(b2);
        }
    }


}
