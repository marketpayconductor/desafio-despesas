package com.borelanjo.despesas.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.borelanjo.despesas.domain.Account;
import com.borelanjo.despesas.service.AccountRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryIntegrationTests {
	
	@Autowired
	AccountRepository repository;
	
	@Test
	public void findAllAccounts() {

		List<Account> accounts = this.repository.findAll();
		assertThat(accounts.size()).isGreaterThan(0);
	}
	
	@Test
	public void createAccount() {
		
		List<Account> accounts = this.repository.findAll();
		Integer accountNumber = 123456 + accounts.size();
		
		Account account = new Account(accountNumber, 25.0);

		Account result = this.repository.save(account);
		assertThat(result.getAccountNumber()).isEqualTo(accountNumber);
	}
	
	@Test
	public void findAccountNumber() {
		
		Integer accountNumber = 123456;

		Account account = this.repository.findOneByAccountNumber(accountNumber);
		assertThat(account.getAccountNumber()).isEqualTo(accountNumber);
	}
	
	@Test
	public void updateBalance() {
		
		Integer accountNumber = 123457;

		Account account = this.repository.findOneByAccountNumber(accountNumber);
		account.setBalance(12.0);
		Account result = this.repository.save(account);
		assertThat(result.getBalance()).isEqualTo(12.0);
	}
	


}
