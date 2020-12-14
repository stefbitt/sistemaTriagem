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
@Table(name = "t_medico")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medicoID")
	private Long medicoId;
	
	private Long crm;
	
	@OneToOne
	@JoinColumn(name = "userID")
	private User user;
	
	public Long getMedicoId() {
		return medicoId;
	}

	public Long getCrm() {
		return crm;
	}

	public void setCrm(Long string) {
		this.crm = string;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}