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

    private final String REDIRECT = "redirect:/admin/servicelist";
    private TvServiceDAO tvServiceDAO;

    @Autowired
    public AdminTvController(TvServiceDAO tvServiceDAO) {
        this.tvServiceDAO = tvServiceDAO;
    }

    // Create
    @PostMapping("admin/tv/add")
    public String addingNewPhoneService(@ModelAttribute("tvServiceDTO") @Valid TvServiceDTO tvServiceDTO,
                                  BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "admin/tv/createTvService";

        tvServiceDAO.createTvService(tvServiceDTO);
        return REDIRECT;
    }
    // Create
    @GetMapping("admin/tv/add")
    public String showNewTvServiceForm(Model model) {
        model.addAttribute("tvServiceDTO", new TvServiceDTO());
        return "admin/tv/createTvService";
    }

    /////// Update
    @PostMapping("admin/tv/update/{id}")
    public String updateTvService(@PathVariable("id") int id,
                                           @ModelAttribute("tvServiceDTO") @Valid TvServiceDTO tvServiceDTO,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "admin/tv/editTvService";

        tvServiceDAO.updateTvService(id, tvServiceDTO);
        return REDIRECT;
    }
    /////// Update
    @GetMapping("admin/tv/update/{id}")
    public String showPhoneServiceFormForUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("tvServiceDTO", tvServiceDAO.showTvService(id));
        return "admin/tv/editTvService";
    }

    /**
     *
     * service with id=0 shouldn't be deleted. It's default for a new user
     */
    @PostMapping ("admin/tv/delete/{id}")
    public String deleteTvService(@PathVariable("id") int id) {
        if(id!=0){
            tvServiceDAO.deleteTvService(id);
        }
        return REDIRECT;
    }
}
