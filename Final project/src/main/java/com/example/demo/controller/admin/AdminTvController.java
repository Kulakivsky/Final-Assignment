package com.example.demo.controller.admin;

import com.example.demo.datasource.services.PhoneServiceDAO;
import com.example.demo.datasource.services.TvServiceDAO;
import com.example.demo.entity.services.PhoneServiceDTO;
import com.example.demo.entity.services.TvServiceDTO;
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
public class AdminTvController {

    private TvServiceDAO tvServiceDAO;

    @Autowired
    public AdminTvController(TvServiceDAO tvServiceDAO) {
        this.tvServiceDAO = tvServiceDAO;
    }

    // Create
    @PostMapping("admin/tv/add")
    public String addingNewPerson(@ModelAttribute("tvServiceDTO") @Valid TvServiceDTO tvServiceDTO,
                                  BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "admin/tv/createTvService";

        tvServiceDAO.createTvService(tvServiceDTO);
        return "redirect:/admin/tv/list";
    }
    // Create
    @GetMapping("admin/tv/add")
    public String showRegistrationForm(Model model) {
        model.addAttribute("tvServiceDTO", new TvServiceDTO());
        return "admin/tv/createTvService";
    }

    // Read
    @GetMapping("admin/tv/list")
    public String showListOfPeople(Model model) {
        model.addAttribute("tvList", tvServiceDAO.getTvServiceList());
        return "admin/tv/showTvList";
    }

    /////// Update
    @PostMapping("admin/tv/update/{id}")
    public String addingNewInternetService(@PathVariable("id") int id,
                                           @ModelAttribute("tvServiceDTO") @Valid TvServiceDTO tvServiceDTO,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "admin/tv/editTvService";

        tvServiceDAO.updateTvService(id, tvServiceDTO);
        return "redirect:/admin/tv/list";
    }
    /////// Update
    @GetMapping("admin/tv/update/{id}")
    public String showRegistrationForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("tvServiceDTO", tvServiceDAO.showTvService(id));
        return "admin/tv/editTvService";
    }

    //Delete
    @PostMapping ("admin/tv/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        tvServiceDAO.deleteTvService(id);
        return "redirect:/admin/tv/list";
    }
}
