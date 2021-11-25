package com.example.demo.controller.user;

import com.example.demo.datasource.ApplicationUserDaoService;
import com.example.demo.datasource.BalanceDao;
import com.example.demo.datasource.services.InternetServiceDAO;
import com.example.demo.datasource.services.PhoneServiceDAO;
import com.example.demo.datasource.services.TvServiceDAO;
import com.example.demo.entity.ApplicationUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private final ApplicationUserDaoService applicationUserDaoService;
    private final InternetServiceDAO internetServiceDAO;
    private final PhoneServiceDAO phoneServiceDAO;
    private final TvServiceDAO tvServiceDAO;
    private final BalanceDao balanceDao;

    @Autowired
    public UserController(ApplicationUserDaoService applicationUserDaoService,
                          InternetServiceDAO internetServiceDAO,
                          PhoneServiceDAO phoneServiceDAO,
                          TvServiceDAO tvServiceDAO,
                          BalanceDao balanceDao) {
        this.applicationUserDaoService = applicationUserDaoService;
        this.internetServiceDAO = internetServiceDAO;
        this.phoneServiceDAO = phoneServiceDAO;
        this.tvServiceDAO = tvServiceDAO;
        this.balanceDao = balanceDao;
    }

    // Read
    @GetMapping("admin/userlist")
    public String showListOfPeople(Model model) {
        model.addAttribute("userList", applicationUserDaoService.getApplicationUsersList());
        return "admin/userlist";
    }
    // Read
    @GetMapping("user/details")
    public String show(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        ApplicationUserDto applicationUserDto = applicationUserDaoService.findUserByPasswordAndUsername(userDetails.getUsername(), userDetails.getPassword());

        model.addAttribute("user", applicationUserDto);
        model.addAttribute("internet", internetServiceDAO.showInternetService(applicationUserDto.getInternetServiceId()));
        model.addAttribute("phone", phoneServiceDAO.showPhoneService(applicationUserDto.getPhoneServiceId()));
        model.addAttribute("tv", tvServiceDAO.showTvService(applicationUserDto.getTvServiceId()));
        model.addAttribute("balance", balanceDao.showBalance(applicationUserDto.getBalanceId()));
        return "user/showUserDetails";
    }

    // Update
    @GetMapping("main/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        ApplicationUserDto applicationUserDto = applicationUserDaoService.showApplicationUser(id);
        model.addAttribute("applicationUserDto", applicationUserDto);
        return "main/edit";
    }
    // Update
    @PostMapping("main/{id}")
    public String updateUser(@ModelAttribute("applicationUserDto")  @Valid ApplicationUserDto applicationUserDto,
                             BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "main/edit";

        applicationUserDaoService.updateApplicationUser(id, applicationUserDto);
        return "redirect:/main";
    }

    // Delete
    @PostMapping("main/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        applicationUserDaoService.deleteApplicationUser(id);
        return "redirect:/main";
    }
}
