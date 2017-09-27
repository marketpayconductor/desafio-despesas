package com.borelanjo.despesas.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.borelanjo.despesas.domain.Account;
import com.borelanjo.despesas.domain.TransactionHistory;
import com.borelanjo.despesas.domain.TransferDTO;
import com.borelanjo.despesas.enumeration.TransactionType;
import com.borelanjo.despesas.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping("/account")
	@ResponseBody
	@Transactional(readOnly = false)
	public Account createAccout(@RequestBody Account account) {
		return accountService.createAccount(account.getAccountNumber(), account.getBalance());
	}

	@GetMapping("/account/{accountNumber}/transactionHistory")
	@ResponseBody
	@Transactional(readOnly = true)
	public List<TransactionHistory> showHistory(@PathVariable("accountNumber") Integer accountNumber) {

		return accountService.showHistory(accountNumber);
	}

	@PatchMapping("/account/{accountNumber}")
	@ResponseBody
	@Transactional(readOnly = false)
	public TransactionHistory setBalance(@PathVariable("accountNumber") Integer accountNumber,
			@RequestBody TransactionHistory transactionHistory) {
		TransactionType transactionType = TransactionType.valueOf(transactionHistory.getType());
		return accountService.addTransaction(accountNumber, transactionType, transactionHistory.getValue());
	}

	@PutMapping("/account/{sourceAccountNumber}/transfer")
	@ResponseBody
	@Transactional(readOnly = false)
	public TransactionHistory transfer(@PathVariable("sourceAccountNumber") Integer sourceAccountNumber,
			@RequestBody TransferDTO transferDTO) {

		return accountService.transfer(sourceAccountNumber, transferDTO.getDestinationAccountNumber(),
				transferDTO.getValue());
	}

}
