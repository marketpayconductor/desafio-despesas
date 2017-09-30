package br.com.ciceroednilson.repository.entity;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name="tb_pessoa")
public class PessoaEntity {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo")
	private Integer codigo;
 
	@Column(name="nome")	
	private String  nome;
 
	@Column(name="sexo")
	private String  sexo;
 
	public Integer getCodigo() {
		return codigo;
	}
 
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
 
	public String getNome() {
		return nome;
	}
 
	public void setNome(String nome) {
		this.nome = nome;
	}
 
	public String getSexo() {
		return sexo;
	}
 
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
 
}