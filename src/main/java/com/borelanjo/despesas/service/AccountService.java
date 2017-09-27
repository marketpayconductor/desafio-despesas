package com.borelanjo.despesas.service;

import java.util.List;

import com.borelanjo.despesas.domain.Account;
import com.borelanjo.despesas.domain.TransactionHistory;
import com.borelanjo.despesas.enumeration.TransactionType;

public interface AccountService {
	
	Account createAccount(Integer accountNumber, Double balance);
	
	Account getAccount(Integer accountNumber);
	
	TransactionHistory addTransaction(Integer accountNumber, TransactionType type, Double value);
	
	Double checkBalance(Integer accountNumber);
	
	TransactionHistory transfer(Integer sourceAccount, Integer destinationAccount, Double value);
	
	List<TransactionHistory> showHistory(Integer accountNumber);

}
