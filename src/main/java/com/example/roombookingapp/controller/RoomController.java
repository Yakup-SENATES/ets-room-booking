package com.example.roombookingapp.controller;

import com.example.roombookingapp.model.entities.Room;
import com.example.roombookingapp.repository.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @RequestMapping("")
    public ModelAndView listRooms() {
        return new ModelAndView("rooms/list", "rooms", roomRepository.findAll());
    }

    @RequestMapping("/add")
    public ModelAndView addRoom() {
        Map<String, Object> model = new HashMap<>();
        model.put("room", new Room("", ""));
        return new ModelAndView("rooms/add", model);
    }

    @RequestMapping("/edit")
    public ModelAndView editRoom(@RequestParam Long roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        Map<String, Object> model = new HashMap<>();
        model.put("room", room);
        return new ModelAndView("rooms/edit", model);
    }

    @PostMapping("/save")
    public Object saveRoom(@Valid Room room, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> model = new HashMap<>();
            model.put("room", room);
            return new ModelAndView("rooms/save", model);
        }
         roomRepository.save(room);
        return "redirect:/rooms";
    }

    @RequestMapping("/delete")
    public String deleteRoom(@RequestParam Long roomId) {
        roomRepository.deleteById(roomId);
        return "redirect:/rooms";
    }
}