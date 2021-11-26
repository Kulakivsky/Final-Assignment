package com.example.demo.model;

import com.example.demo.datasource.ApplicationUserDao;
import com.example.demo.datasource.ApplicationUserDaoService;
import com.example.demo.datasource.BalanceDao;
import com.example.demo.datasource.CartDAO;
import com.example.demo.datasource.services.InternetServiceDAO;
import com.example.demo.datasource.services.PhoneServiceDAO;
import com.example.demo.datasource.services.TvServiceDAO;
import com.example.demo.entity.ApplicationUserDto;
import com.example.demo.entity.BalanceDto;
import com.example.demo.entity.CartDTO;
import com.example.demo.entity.services.InternetServiceDTO;
import com.example.demo.entity.services.PhoneServiceDTO;
import com.example.demo.entity.services.TvServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CartTransaction {

    private final InternetServiceDAO internetServiceDAO;
    private final PhoneServiceDAO phoneServiceDAO;
    private final TvServiceDAO tvServiceDAO;
    private final BalanceDao balanceDao;

    @Autowired
    public CartTransaction(InternetServiceDAO internetServiceDAO,
                           PhoneServiceDAO phoneServiceDAO, TvServiceDAO tvServiceDAO, BalanceDao balanceDao) {
        this.internetServiceDAO = internetServiceDAO;
        this.phoneServiceDAO = phoneServiceDAO;
        this.tvServiceDAO = tvServiceDAO;
        this.balanceDao = balanceDao;
    }

    public boolean isTransactionPossible(CartDTO cartDTO, ApplicationUserDto applicationUserDto){
       BalanceDto balanceDto = balanceDao.showBalance(applicationUserDto.getBalanceId());
       InternetServiceDTO internetServiceDTO = internetServiceDAO.showInternetService(cartDTO.getInternet_service_id());
       PhoneServiceDTO phoneServiceDTO = phoneServiceDAO.showPhoneService(cartDTO.getPhone_service_id());
       TvServiceDTO tvServiceDTO = tvServiceDAO.showTvService(cartDTO.getTv_service_id());

       int serviceSum = internetServiceDTO.getPriceOfTariff() + phoneServiceDTO.getPriceOfTariff() + tvServiceDTO.getPriceOfTariff();
       if ((balanceDto.getBalance() - serviceSum) >= 0 ) {
           return true;
       } else {
           return false;
       }
    }



}
