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
@Table(name = "t_medico")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medicoID")
	private Long medicoId;
	
	@NotNull(message = "Crm não pode ser nula")
	@NotBlank(message = "Crm não pode está em branco")
	private Long crm;
	
	@NotNull(message = "Nome não pode ser nula")
	@NotBlank(message = "Nome não pode está em branco")
	private String nome;
	
	@NotNull(message = "Email não pode ser nulo")
	@Email(message = "Email não é válido")
	private String email;
	
	@NotNull(message = "Senha não pode ser nula")
	@NotBlank(message = "Senha não pode está em branco")
	private String senha;

	public Long getMedicoId() {
		return medicoId;
	}

	public Long getCrm() {
		return crm;
	}

	public void setCrm(Long string) {
		this.crm = string;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}