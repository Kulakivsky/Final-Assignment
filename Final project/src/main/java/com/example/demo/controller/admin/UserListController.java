package com.example.demo.controller.admin;

import com.example.demo.datasource.ApplicationUserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserListController {

    ApplicationUserDaoService applicationUserDaoService;

    @Autowired
    public UserListController(ApplicationUserDaoService applicationUserDaoService) {
        this.applicationUserDaoService = applicationUserDaoService;
    }
    // Read
    @GetMapping("admin/userlist")
    public String showListOfPeople(Model model) {
        model.addAttribute("userList", applicationUserDaoService.getApplicationUsersList());
        return "admin/userlist";
    }
}
