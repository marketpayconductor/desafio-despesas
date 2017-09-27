package com.borelanjo.despesas.enumeration;

public enum TransactionType {
	DECREASE("Despesa"), INCREASE("Receita");

	private String type;

	TransactionType(String type) {
		this.type = type;
	}

	public String type() {
		return type;
	}

}
