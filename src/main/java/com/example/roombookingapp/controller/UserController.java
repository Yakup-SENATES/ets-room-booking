package com.example.roombookingapp.controller;

import com.example.roombookingapp.model.entities.User;
import com.example.roombookingapp.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("")
    public ModelAndView listUsers() {
        return new ModelAndView("users/list", "users", userRepository.findAll());
    }

    @RequestMapping("/add")
    public ModelAndView addUser() {
        return new ModelAndView("users/edit", "user", new User());
    }

    @RequestMapping("/edit")
    public ModelAndView editUser(@RequestParam Long userId){
        User user = userRepository.findById(userId).get();
        return new ModelAndView("users/edit", "user", user);
    }

    @PostMapping("/save")
    public Object saveUser(@Valid User user, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("users/save", "user", user);
        }
        //TODO: the password should of course be encrypted before saving
        userRepository.save(user);
        return "redirect:/users";
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam Long userId) {
        userRepository.deleteById(userId);
        return "redirect:/users";
    }

    @RequestMapping("/resetPW")
    public String resetUserPass(@RequestParam Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setPassword("secret");
        userRepository.save(user);
        return "redirect:/users";
    }

}
