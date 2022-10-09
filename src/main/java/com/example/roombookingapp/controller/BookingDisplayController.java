package com.example.roombookingapp.controller;

import com.example.roombookingapp.model.DateRequestCommand;
import com.example.roombookingapp.repository.BookingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.Map;

@Controller
public class BookingDisplayController {

    private final BookingRepository bookingRepository;

    public BookingDisplayController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public ModelAndView showCalender(Date date){
        Map<String , Object> model = Map.of(
                "bookings", bookingRepository.findAllByDate(date),
                "dateRequest", new DateRequestCommand(date)
        );
        return new ModelAndView("home", model);
    }

    @RequestMapping("")
    public ModelAndView homePage(){
        Date date = new Date(new java.util.Date().getTime());
        return showCalender(date);
    }

    @RequestMapping("calender")
    public ModelAndView calender(@RequestParam("date") Date date){
        return showCalender(date);
    }


}

