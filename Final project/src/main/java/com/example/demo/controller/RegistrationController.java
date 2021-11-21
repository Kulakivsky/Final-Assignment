package com.example.demo.controller;

import com.example.demo.entity.ApplicationUserDto;
import com.example.demo.datasource.ApplicationUserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class RegistrationController {

    @Autowired
    private ApplicationUserDaoService applicationUserDaoService;

    @PostMapping("main/registration")
    public String addingNewPerson(@ModelAttribute("applicationUserDto") @Valid ApplicationUserDto applicationUserDto,
                                  BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "main/registration";

        applicationUserDaoService.saveApplicationUser(applicationUserDto);
        return "redirect:/main/list";
    }

    @GetMapping("main/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("applicationUserDto", new ApplicationUserDto());
        return "main/registration";
    }

    @GetMapping("main/list")
    public String showListOfPeople(Model model) {
        model.addAttribute("userList", applicationUserDaoService.getApplicationUsersList());
        return "main/list";
    }

    @GetMapping("main/{id}")
    public String show(@PathVariable("id")int id, Model model) {
        model.addAttribute("user", applicationUserDaoService.showApplicationUser(id));
        return "main/show";
    }

    @GetMapping("main/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("applicationUserDto", applicationUserDaoService.showApplicationUser(id));
        return "main/edit";
    }

    @PostMapping ("main/{id}")
    public String updateUser(@ModelAttribute("applicationUserDto")  @Valid ApplicationUserDto applicationUserDto,
                             BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "main/edit";

        applicationUserDaoService.updateApplicationUser(id, applicationUserDto);
        return "redirect:/main/list";
    }

    @PostMapping ("main/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        applicationUserDaoService.deleteApplicationUser(id);
        return "redirect:/main/list";
    }
}





