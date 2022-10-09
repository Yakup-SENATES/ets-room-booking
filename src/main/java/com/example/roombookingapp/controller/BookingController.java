package com.example.roombookingapp.controller;

import com.example.roombookingapp.model.BookingCommand;
import com.example.roombookingapp.model.Layout;
import com.example.roombookingapp.model.entities.Booking;
import com.example.roombookingapp.repository.BookingRepository;
import com.example.roombookingapp.repository.RoomRepository;
import com.example.roombookingapp.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public BookingController(BookingRepository bookingRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    private Map<String, Object> getBookingFromModel(Booking booking) {
        return Map.of(
                "booking", new BookingCommand(booking),
                "rooms", roomRepository.findAll(),
                "layouts", Layout.values(),
                "users", userRepository.findAll()
        );
    }

    @RequestMapping("/edit")
    public ModelAndView editBooking(@RequestParam("id") Long id) {
        return new ModelAndView("bookings/edit", getBookingFromModel(bookingRepository.findById(id).get()));
    }


    @RequestMapping("/new")
    public ModelAndView newBooking() {
        return new ModelAndView("bookings/edit", getBookingFromModel(new Booking()));
    }


    @RequestMapping("/save")
    public String saveBooking(BookingCommand bookingCommand) {
        bookingRepository.save(bookingCommand.toBooking());
        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String deleteBooking(@RequestParam Long id) {
        bookingRepository.deleteById(id);
        return "redirect:/";
    }

}
