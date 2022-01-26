package com.example.demo.repository;

import com.example.demo.entity.BalanceDto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends PagingAndSortingRepository<BalanceDto, Integer> {




//    public void addBalance(BalanceDto balanceDto);
//    public void updateBalance(BalanceDto balanceDto);
//    public void removeBalance(int id);
//    public BalanceDto getBalanceDtoById (int id);
//    public List<BalanceDto> listBalanceDto();


}
