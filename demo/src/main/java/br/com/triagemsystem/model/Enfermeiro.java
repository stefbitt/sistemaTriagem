package br.com.triagemsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_enfermerio")
public class Enfermeiro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enfermeiroID")
	private Long enfermeiroId;
	private Long matricula;
	private String nome;
	private String email;
	private String senha;

	public Long getEnfermeiroId() {
		return enfermeiroId;
	}

	public void setEnfermeiroId(Long enfermeiroId) {
		this.enfermeiroId = enfermeiroId;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
