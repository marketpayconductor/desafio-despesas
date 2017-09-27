package com.borelanjo.despesas.service;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.borelanjo.despesas.domain.Account;

public interface AccountRepository extends Repository<Account, Long>{
	
	List<Account> findAll();
	
	Account findOneByAccountNumber(Integer accountNumber);
	
	
	Account save(Account account);
	

}
