package br.com.triagemsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_enfermeiro")
public class Enfermeiro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enfermeiroID")
	private Long enfermeiroId;
	
	private Long matricula;
	
	@OneToOne
	@JoinColumn(name = "userID")
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
