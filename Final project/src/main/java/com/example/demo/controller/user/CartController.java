package com.example.demo.controller.user;

import com.example.demo.datasource.ApplicationUserDao;
import com.example.demo.datasource.ApplicationUserDaoService;
import com.example.demo.datasource.BalanceDao;
import com.example.demo.datasource.CartDAO;
import com.example.demo.datasource.services.InternetServiceDAO;
import com.example.demo.datasource.services.PhoneServiceDAO;
import com.example.demo.datasource.services.TvServiceDAO;
import com.example.demo.entity.ApplicationUserDto;
import com.example.demo.entity.CartDTO;
import com.example.demo.entity.services.InternetServiceDTO;
import com.example.demo.entity.services.PhoneServiceDTO;
import com.example.demo.entity.services.TvServiceDTO;
import com.example.demo.model.CartTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {

    private final InternetServiceDAO internetServiceDAO;
    private final PhoneServiceDAO phoneServiceDAO;
    private final TvServiceDAO tvServiceDAO;
    private final BalanceDao balanceDao;
    private CartTransaction cartTransaction;
    private final CartDAO cartDAO;
    private final ApplicationUserDaoService applicationUserDaoService;

    @Autowired
    public CartController(InternetServiceDAO internetServiceDAO, PhoneServiceDAO phoneServiceDAO,
                          TvServiceDAO tvServiceDAO, BalanceDao balanceDao, CartTransaction cartTransaction,
                          CartDAO cartDAO, ApplicationUserDaoService applicationUserDaoService) {
        this.internetServiceDAO = internetServiceDAO;
        this.phoneServiceDAO = phoneServiceDAO;
        this.tvServiceDAO = tvServiceDAO;
        this.balanceDao = balanceDao;
        this.cartTransaction = cartTransaction;
        this.cartDAO = cartDAO;
        this.applicationUserDaoService = applicationUserDaoService;
    }

    @GetMapping("user/cart")
    public String showBalance(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());

        ApplicationUserDto applicationUserDto = applicationUserDaoService.findUserByPasswordAndUsername(userDetails.getUsername(), userDetails.getPassword());
        CartDTO cartDTO = cartDAO.showCard(applicationUserDto.getId());

        InternetServiceDTO internetServiceDTO = internetServiceDAO.showInternetService(cartDTO.getInternet_service_id());
        PhoneServiceDTO phoneServiceDTO = phoneServiceDAO.showPhoneService(cartDTO.getPhone_service_id());
        TvServiceDTO tvServiceDTO = tvServiceDAO.showTvService(cartDTO.getTv_service_id());

        Integer sum = internetServiceDTO.getPriceOfTariff() + tvServiceDTO.getPriceOfTariff() + phoneServiceDTO.getPriceOfTariff();

        model.addAttribute("internetServiceDTO", internetServiceDTO);
        model.addAttribute("phoneServiceDTO", phoneServiceDTO);
        model.addAttribute("tvServiceDTO", tvServiceDTO);
        model.addAttribute("sum", sum);
        model.addAttribute("balance", balanceDao.showBalance(applicationUserDto.getBalanceId()));
        model.addAttribute("card", cartDTO);
        return "user/cart";
    }

//TODO: Change role
    @PreAuthorize("hasAnyRole('ROLE_STUDENT')")
    @PostMapping("main/test")
    public String test(@AuthenticationPrincipal UserDetails userDetails){
        System.out.println(userDetails.getUsername());
        return "mainPage";
    }

    @PostMapping("user/internet/addService/{id}")
    public String addingInternetService(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails userDetails){
        ApplicationUserDto applicationUserDto = applicationUserDaoService.findUserByPasswordAndUsername(userDetails.getUsername(), userDetails.getPassword());
        cartDAO.updateInternetInCart(applicationUserDto.getId(), id);
        return "redirect:/admin/internet/list";
    }

    @PostMapping("user/phone/addService/{id}")
    public String addingPhoneService(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails userDetails){
        ApplicationUserDto applicationUserDto = applicationUserDaoService.findUserByPasswordAndUsername(userDetails.getUsername(), userDetails.getPassword());
        cartDAO.updatePhoneInCart(applicationUserDto.getId(), id);
        return "redirect:/admin/phone/list";
    }

    @PostMapping("user/tv/addService/{id}")
    public String addingTvService(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails userDetails){
        ApplicationUserDto applicationUserDto = applicationUserDaoService.findUserByPasswordAndUsername(userDetails.getUsername(), userDetails.getPassword());
        cartDAO.updateTvInCart(applicationUserDto.getId(), id);
        return "redirect:/admin/tv/list";
    }

    @PostMapping("user/card/{id}/buy")
    public String buyService(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails userDetails){
        ApplicationUserDto applicationUserDto = applicationUserDaoService.findUserByPasswordAndUsername(userDetails.getUsername(), userDetails.getPassword());
        CartDTO cartDTO = cartDAO.showCard(id);

        if(cartTransaction.isTransactionPossible(cartDTO, applicationUserDto)) {
            applicationUserDto.setInternetServiceId(cartDTO.getInternet_service_id());
            applicationUserDto.setPhoneServiceId(cartDTO.getPhone_service_id());
            applicationUserDto.setTvServiceId(cartDTO.getTv_service_id());
            applicationUserDaoService.updateApplicationUser(applicationUserDto.getId(), applicationUserDto);
        } else {
            // Block the user;
        }
        return "redirect:/admin/tv/list";
    }


}