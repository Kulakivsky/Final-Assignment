package com.example.demo.controller;

import com.example.demo.student.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class RegistrationController {


    @PostMapping(value = "/main/registration")
    public ModelAndView showRegistrationForm2(@Valid Person person) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("person", new Person());
        return modelAndView;
    }

    @GetMapping("main/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("person", new Person());
        return "registration";
    }

}
