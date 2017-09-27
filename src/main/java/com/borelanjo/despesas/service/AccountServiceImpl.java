package com.borelanjo.despesas.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.borelanjo.despesas.domain.Account;
import com.borelanjo.despesas.domain.TransactionHistory;
import com.borelanjo.despesas.enumeration.TransactionType;

@Component("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	private final TransactionHistoryRepository transactionHistoryRepository;

	public AccountServiceImpl(AccountRepository accountRepository, TransactionHistoryRepository transactionHistoryRepository) {
		this.accountRepository = accountRepository;
		this.transactionHistoryRepository = transactionHistoryRepository;
	}

	public Account createAccount(Integer accountNumber, Double balance) {
		Account account = new Account(accountNumber, balance);
		Account result = accountRepository.save(account);
		return result;
	}

	public TransactionHistory addTransaction(Integer accountNumber, TransactionType transactionType, Double value) {
		Account account = this.accountRepository.findOneByAccountNumber(accountNumber);
		Assert.notNull(account, "Conta não deve ser nula");
		String description = transactionType.type()+ ": R$ "+value;
		value = fixValue(transactionType, value);
		account.setBalance(account.getBalance()+ value);
		this.accountRepository.save(account);
		TransactionHistory transactionHistory = new TransactionHistory(account, transactionType.type(), description, value);
		TransactionHistory result = transactionHistoryRepository.save(transactionHistory);
		return result;
	}

	public Double checkBalance(Integer accountNumber) {
		Account account = this.accountRepository.findOneByAccountNumber(accountNumber);
		return account.getBalance();
	}

	public TransactionHistory transfer(Integer sourceAccountNumber, Integer destinationAccountNumber, Double value) {
		Account sourceAccount = this.accountRepository.findOneByAccountNumber(sourceAccountNumber);
		Account destinationAccount = this.accountRepository.findOneByAccountNumber(destinationAccountNumber);
		String sourceDescription = TransactionType.DECREASE.type()+ ": Transferido R$ "+value+" para a conta "+destinationAccountNumber;
		String destinationDescription = TransactionType.INCREASE.type()+ ": Recebido R$ "+value+" da conta "+sourceAccountNumber;
		
		sourceAccount.setBalance(sourceAccount.getBalance()-value);
		destinationAccount.setBalance(destinationAccount.getBalance()+value);
		
		TransactionHistory sourceTransactionHistory = new TransactionHistory(sourceAccount, TransactionType.DECREASE.type(), sourceDescription, value);
		TransactionHistory destinationTransactionHistory = new TransactionHistory(destinationAccount, TransactionType.INCREASE.type(), destinationDescription, value);
		
		this.transactionHistoryRepository.save(destinationTransactionHistory);
		TransactionHistory result = transactionHistoryRepository.save(sourceTransactionHistory);
		return result;
	}

	public List<TransactionHistory> showHistory(Integer accountNumber) {
		Account account = this.accountRepository.findOneByAccountNumber(accountNumber);
		List<TransactionHistory> transactionHistories = transactionHistoryRepository.findByAccount(account);
		return transactionHistories;
	}

	private Double fixValue(TransactionType type, Double value) {
		if (type == TransactionType.DECREASE) {
			if (value > 0) {
				value *= -1;
			}
		} else if (type == TransactionType.INCREASE) {
			if (value < 0) {
				value *= -1;
			}
		}
		return value;

	}
}
