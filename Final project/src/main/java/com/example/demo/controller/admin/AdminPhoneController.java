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

    private final String REDIRECT = "redirect:/admin/servicelist";
    private PhoneServiceDAO phoneServiceDAO;

    @Autowired
    public AdminPhoneController(PhoneServiceDAO phoneServiceDAO) {
        this.phoneServiceDAO = phoneServiceDAO;
    }

    // Create
    @PostMapping("admin/phone/add")
    public String addingNewPhoneService(@ModelAttribute("phoneServiceDTO") @Valid PhoneServiceDTO phoneServiceDTO,
                                  BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "admin/internet/createInternetService";
        }

        phoneServiceDAO.createPhoneService(phoneServiceDTO);
        return REDIRECT;
    }

    // Create
    @GetMapping("admin/phone/add")
    public String showNewPhoneServiceForm(Model model) {
        model.addAttribute("phoneServiceDTO", new PhoneServiceDTO());
        return "admin/phone/createPhoneService";
    }


    /////// Update
    @PostMapping("admin/phone/update/{id}")
    public String updatePhoneService(@PathVariable("id") int id,
                                           @ModelAttribute("phoneServiceDTO") @Valid PhoneServiceDTO phoneServiceDTO,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "admin/phone/editPhoneService";

        phoneServiceDAO.updatePhoneService(id, phoneServiceDTO);
        return REDIRECT;
    }
    /////// Update
    @GetMapping("admin/phone/update/{id}")
    public String showPhoneServiceFormForUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("phoneServiceDTO", phoneServiceDAO.showPhoneService(id));
        return "admin/phone/editPhoneService";
    }

    /**
     *
     * service with id=0 shouldn't be deleted. It's default for a new user
     */
    @PostMapping ("admin/phone/delete/{id}")
    public String deletePhoneService(@PathVariable("id") int id) {
        if(id!=0){
            phoneServiceDAO.deletePhoneService(id);
        }
        return REDIRECT;
    }
}
