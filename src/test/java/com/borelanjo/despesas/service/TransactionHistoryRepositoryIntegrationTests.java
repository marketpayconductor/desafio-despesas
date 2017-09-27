package com.borelanjo.despesas.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.borelanjo.despesas.domain.Account;
import com.borelanjo.despesas.domain.TransactionHistory;
import com.borelanjo.despesas.enumeration.TransactionType;
import com.borelanjo.despesas.service.AccountRepository;
import com.borelanjo.despesas.service.TransactionHistoryRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionHistoryRepositoryIntegrationTests {
	
	@Autowired
	TransactionHistoryRepository repository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Test
	public void createTransactionHistory() {
		
		Integer accountNumber = 123456;
		Account account = accountRepository.findOneByAccountNumber(accountNumber);
		
		TransactionHistory transactionHistory = new TransactionHistory(account, TransactionType.DECREASE.type(), "Saque conta corrente no valor de R$ 25,00", -25.5);

		TransactionHistory result = this.repository.save(transactionHistory);
		assertThat(result.getValue()).isEqualTo(-25.5);
	}
	
	@Test
	public void findAllTransactionHistory() {

		List<TransactionHistory> transactionHistory = this.repository.findAll();
		assertThat(transactionHistory.size()).isGreaterThan(0);
	}
	
	@Test
	public void findAllTransactionHistoryByAccount() {
		Integer accountNumber = 123456;
		Account account = accountRepository.findOneByAccountNumber(accountNumber);

		List<TransactionHistory> transactionHistory = this.repository.findByAccount(account);
		assertThat(transactionHistory.size()).isGreaterThan(0);
	}
	


}
