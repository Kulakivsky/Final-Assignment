package com.example.demo.controller.admin;

import com.example.demo.datasource.services.InternetServiceDAO;
import com.example.demo.entity.ApplicationUserDto;
import com.example.demo.entity.services.InternetServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AdminInternetController {

    private InternetServiceDAO internetServiceDAO;

    @Autowired
    public AdminInternetController(InternetServiceDAO internetServiceDAO) {
        this.internetServiceDAO = internetServiceDAO;
    }

    // Create
    @PostMapping("admin/internet/add")
    public String addingNewPerson(@ModelAttribute("internetServiceDTO") @Valid InternetServiceDTO internetServiceDTO,
                                  BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "admin/internet/createInternetService";

        internetServiceDAO.createInternetService(internetServiceDTO);
        return "redirect:/admin/internet/list";
    }
    // Create
    @GetMapping("admin/internet/add")
    public String showRegistrationForm(Model model) {
        model.addAttribute("internetServiceDTO", new InternetServiceDTO());
        return "admin/internet/createInternetService";
    }

    // Read
    @GetMapping("admin/internet/list")
    public String showListOfPeople(Model model) {
        model.addAttribute("internetList", internetServiceDAO.getInternetServiceList());
        return "admin/internet/showInternetList";
    }

    /////// Update
    @PostMapping("admin/internet/update/{id}")
    public String addingNewInternetService(@PathVariable("id") int id,
                                           @ModelAttribute("internetServiceDTO") @Valid InternetServiceDTO internetServiceDTO,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "admin/internet/editInternetService";

        internetServiceDAO.updateInternetService(id, internetServiceDTO);
        return "redirect:/admin/internet/list";
    }
    /////// Update
    @GetMapping("admin/internet/update/{id}")
    public String showRegistrationForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("internetServiceDTO", internetServiceDAO.showInternetService(id));
        return "admin/internet/editInternetService";
    }

    //Delete
    @PostMapping ("admin/internet/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        internetServiceDAO.deleteInternetService(id);
        return "redirect:/admin/internet/list";
    }
}
