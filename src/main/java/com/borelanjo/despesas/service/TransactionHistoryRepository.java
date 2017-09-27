package com.borelanjo.despesas.service;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.borelanjo.despesas.domain.Account;
import com.borelanjo.despesas.domain.TransactionHistory;

public interface TransactionHistoryRepository extends Repository<TransactionHistory, Long>{
	
	List<TransactionHistory> findAll();
	
	List<TransactionHistory> findByAccount(Account account);
	
	TransactionHistory save(TransactionHistory transactionHistory);

}
