package br.com.triagemsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "t_enfermeiro")
public class Enfermeiro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enfermeiroID")
	private Long enfermeiroId;
	
	@NotNull(message = "Matricula não pode ser nula")
	@NotBlank(message = "Matricula não pode está em branco")
	private Long matricula;
	
	@NotNull(message = "Nome não pode ser nula")
	@NotBlank(message = "Nome não pode está em branco")
	private String nome;
	
	@NotNull(message = "Email não pode ser nulo")
	@Email(message = "Email não é válido")
	private String email;
	
	@NotNull(message = "Senha não pode ser nula")
	@NotBlank(message = "Senha não pode está em branco")
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
