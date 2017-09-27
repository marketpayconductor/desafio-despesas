package com.borelanjo.despesas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;

@Entity
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "account_generator", sequenceName = "account_sequence", initialValue = 23)
	@GeneratedValue(generator = "account_generator")
	private Long id;

	@Column(nullable = false, unique = true)
	private Integer accountNumber;

	@Column(nullable = false)
	private Double balance;

	@Column(nullable = true)
	private Date created;

	@Column(nullable = true)
	private Date updated;

	protected Account() {
	}

	public Account(Integer accountNumber, Double balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	@PrePersist
	protected void onCreate() {
		created = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}

	@Override
	public String toString() {
		return getAccountNumber() + "," + getBalance();
	}

}
