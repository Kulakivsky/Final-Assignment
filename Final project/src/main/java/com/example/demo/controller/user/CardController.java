package com.example.demo.controller.user;

import com.example.demo.datasource.BalanceDao;
import com.example.demo.entity.BalanceDto;
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
public class CardController {

//    private BalanceDao balanceDao;
//
//    @Autowired
//    public CardController(BalanceDao balanceDao) {
//        this.balanceDao = balanceDao;
//    }
//
//    @PostMapping("user/card/{id}/editBalance")
//    public String addingNewPerson(@PathVariable("id") int id,
//                                  @ModelAttribute("balanceDto") @Valid BalanceDto balanceDto,
//                                  BindingResult bindingResult){
//        if (bindingResult.hasErrors())
//            return "main/balance/editBalance";
//
//        balanceDao.updateBalance(id, balanceDto);
//        return "redirect:/main/list";
//    }
//
//    @GetMapping("main/balance/{id}/editBalance")
//    public String showBalance(@PathVariable("id") int id, Model model) {
//        model.addAttribute("balanceDto", balanceDao.showBalance(id));
//        return "main/balance/editBalance";
//    }
}