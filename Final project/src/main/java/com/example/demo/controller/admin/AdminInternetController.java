package com.example.demo.controller.admin;

import com.example.demo.datasource.services.InternetServiceDAO;
import com.example.demo.datasource.services.PhoneServiceDAO;
import com.example.demo.datasource.services.TvServiceDAO;
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

    private final String REDIRECT = "redirect:/admin/servicelist";
    private final InternetServiceDAO internetServiceDAO;

    @Autowired
    public AdminInternetController(InternetServiceDAO internetServiceDAO) {
        this.internetServiceDAO = internetServiceDAO;
    }

    // Create
    @PostMapping("admin/internet/add")
    public String addingNewInternetService(@ModelAttribute("internetServiceDTO") @Valid InternetServiceDTO internetServiceDTO,
                                           BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "admin/internet/createInternetService";

        internetServiceDAO.createInternetService(internetServiceDTO);
        return REDIRECT;
    }

    // Create
    @GetMapping("admin/internet/add")
    public String showNewInternetServiceForm(Model model) {
        model.addAttribute("internetServiceDTO", new InternetServiceDTO());
        return "admin/internet/createInternetService";
    }

    /////// Update
    @PostMapping("admin/internet/update/{id}")
    public String updateInternetService(@PathVariable("id") int id,
                                        @ModelAttribute("internetServiceDTO") @Valid InternetServiceDTO internetServiceDTO,
                                        BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "admin/internet/editInternetService";

        internetServiceDAO.updateInternetService(id, internetServiceDTO);
        return REDIRECT;
    }

    /////// Update
    @GetMapping("admin/internet/update/{id}")
    public String showInternetServiceFormForUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("internetServiceDTO", internetServiceDAO.showInternetService(id));
        return "admin/internet/editInternetService";
    }

    /**
     *
     * service with id=0 shouldn't be deleted. It's default for a new user
     */
    @PostMapping ("admin/internet/delete/{id}")
    public String deleteInternetService(@PathVariable("id") int id) {
        if(id == 0 ){
            return REDIRECT;
        }
        internetServiceDAO.deleteInternetService(id);
        return REDIRECT;
    }
}
