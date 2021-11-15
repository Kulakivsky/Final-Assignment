package com.example.demo.controller;

import com.example.demo.auth.ApplicationUser;
import com.example.demo.auth.ApplicationUserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RegistrationController {

    @Autowired
    private ApplicationUserDaoService applicationUserDaoService;

    @PostMapping("main/registration")
    public String addingNewPerson( @ModelAttribute ApplicationUser applicationUser){
        applicationUserDaoService.save(applicationUser);
        return "main/list";
    }

    @GetMapping("main/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("applicationUser", new ApplicationUser());
        return "main/registration";
    }

    @GetMapping("main/list")
    public String showListOfPeople(Model model) {
        model.addAttribute("userList", applicationUserDaoService.getApplicationUsersList());
        return "main/list";
    }

    @GetMapping("main/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", applicationUserDaoService.show(id));
        return "main/show";
    }
}





