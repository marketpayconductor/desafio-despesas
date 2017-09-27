package com.borelanjo.despesas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;

@Entity
public class TransactionHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "transaction_history_generator", sequenceName = "transaction_history_sequence", initialValue = 28)
	@GeneratedValue(generator = "transaction_history_generator")
	private Long id;

	@ManyToOne(optional = false)
	private Account account;

	@Column(nullable = false)
	private String type;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private Double value;

	@Column(nullable = false)
	private Date created;

	protected TransactionHistory() {
	}

	public TransactionHistory(Account account, String type, String description, Double value) {
		this.account = account;
		this.type = type;
		this.description = description;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Date getCreated() {
		return created;
	}

	@PrePersist
	protected void onCreate() {
		created = new Date();
	}

}
