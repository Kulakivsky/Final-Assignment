package com.example.demo.controller.guest;

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

    private ApplicationUserDaoService applicationUserDaoService;

    @Autowired
    public RegistrationController(ApplicationUserDaoService applicationUserDaoService) {
        this.applicationUserDaoService = applicationUserDaoService;
    }

    @PostMapping("main/registration")
    public String addingNewPerson(@ModelAttribute("applicationUserDto") @Valid ApplicationUserDto applicationUserDto,
                                  BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "main/registration";

        applicationUserDaoService.createApplicationUser(applicationUserDto);
        return "redirect:/main/list";
    }

    @GetMapping("main/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("applicationUserDto", new ApplicationUserDto());
        return "main/registration";
    }

}





