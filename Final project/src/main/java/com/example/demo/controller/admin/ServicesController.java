package com.example.demo.controller.admin;

import com.example.demo.datasource.services.InternetServiceDAO;
import com.example.demo.datasource.services.PhoneServiceDAO;
import com.example.demo.datasource.services.TvServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicesController {

    private final InternetServiceDAO internetServiceDAO;
    private final PhoneServiceDAO phoneServiceDAO;
    private final TvServiceDAO tvServiceDAO;

    @Autowired
    public ServicesController(InternetServiceDAO internetServiceDAO, PhoneServiceDAO phoneServiceDAO, TvServiceDAO tvServiceDAO) {
        this.internetServiceDAO = internetServiceDAO;
        this.phoneServiceDAO = phoneServiceDAO;
        this.tvServiceDAO = tvServiceDAO;
    }

    @GetMapping("admin/servicelist")
    public String showListOfPeople(Model model) {
        model.addAttribute("internetList", internetServiceDAO.getInternetServiceList());
        model.addAttribute("phoneList", phoneServiceDAO.getPhoneServiceList());
        model.addAttribute("tvList", tvServiceDAO.getTvServiceList());
        return "admin/adminServiceList";
    }
}
