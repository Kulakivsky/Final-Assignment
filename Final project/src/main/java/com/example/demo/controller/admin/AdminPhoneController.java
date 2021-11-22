package com.example.demo.controller.admin;

import com.example.demo.datasource.services.InternetServiceDAO;
import com.example.demo.datasource.services.PhoneServiceDAO;
import com.example.demo.entity.services.InternetServiceDTO;
import com.example.demo.entity.services.PhoneServiceDTO;
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
public class AdminPhoneController {

    private PhoneServiceDAO phoneServiceDAO;

    @Autowired
    public AdminPhoneController(PhoneServiceDAO phoneServiceDAO) {
        this.phoneServiceDAO = phoneServiceDAO;
    }

    // Create
    @PostMapping("admin/phone/add")
    public String addingNewPerson(@ModelAttribute("phoneServiceDTO") @Valid PhoneServiceDTO phoneServiceDTO,
                                  BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "admin/internet/createInternetService";

        phoneServiceDAO.createPhoneService(phoneServiceDTO);
        return "redirect:/admin/phone/list";
    }
    // Create
    @GetMapping("admin/phone/add")
    public String showRegistrationForm(Model model) {
        model.addAttribute("phoneServiceDTO", new PhoneServiceDTO());
        return "admin/phone/createPhoneService";
    }

    // Read
    @GetMapping("admin/phone/list")
    public String showListOfPeople(Model model) {
        model.addAttribute("phoneList", phoneServiceDAO.getPhoneServiceList());
        return "admin/phone/showPhoneList";
    }

    /////// Update
    @PostMapping("admin/phone/update/{id}")
    public String addingNewInternetService(@PathVariable("id") int id,
                                           @ModelAttribute("phoneServiceDTO") @Valid PhoneServiceDTO phoneServiceDTO,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "admin/phone/editPhoneService";

        phoneServiceDAO.updatePhoneService(id, phoneServiceDTO);
        return "redirect:/admin/phone/list";
    }
    /////// Update
    @GetMapping("admin/phone/update/{id}")
    public String showRegistrationForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("phoneServiceDTO", phoneServiceDAO.showPhoneService(id));
        return "admin/phone/editPhoneService";
    }

    //Delete
    @PostMapping ("admin/phone/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        phoneServiceDAO.deletePhoneService(id);
        return "redirect:/admin/phone/list";
    }
}
