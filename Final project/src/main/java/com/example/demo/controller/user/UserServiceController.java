package com.example.demo.controller.user;

import com.example.demo.datasource.services.InternetServiceDAO;
import com.example.demo.datasource.services.PhoneServiceDAO;
import com.example.demo.datasource.services.TvServiceDAO;
import com.example.demo.entity.services.InternetServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserServiceController {

    private final InternetServiceDAO internetServiceDAO;
    private final PhoneServiceDAO phoneServiceDAO;
    private final TvServiceDAO tvServiceDAO;

    @Autowired
    public UserServiceController(InternetServiceDAO internetServiceDAO, PhoneServiceDAO phoneServiceDAO, TvServiceDAO tvServiceDAO) {
        this.internetServiceDAO = internetServiceDAO;
        this.phoneServiceDAO = phoneServiceDAO;
        this.tvServiceDAO = tvServiceDAO;
    }

    // Read
    @GetMapping("main/list")
    public String showListOfPeople(Model model) {
        model.addAttribute("internetList", internetServiceDAO.getInternetServiceList());
        model.addAttribute("phoneList", phoneServiceDAO.getPhoneServiceList());
        model.addAttribute("tvList", tvServiceDAO.getTvServiceList());
        return "user/serviceList";
    }
}



