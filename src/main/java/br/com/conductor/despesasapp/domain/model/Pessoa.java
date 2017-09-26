package br.com.conductor.despesasapp.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade que representa uma pessoa.
 * 
 * @author alexsandro (alexsandroguedes@ffm.com.br) 
 *
 */
@Entity
@Table(name = "T_PESSOA")
public class Pessoa implements Serializable{

	private static final long serialVersionUID = -904806967672364410L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PESSOA")
	private long idPessoa;

	@NotNull
	@Column(name = "NOME")
	private String nome;

	@NotNull
	@Column(name = "CPF", unique = true)
	private String cpf;

	public long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
