package br.com.triagemsystem.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MedicoRequest extends UserRequest {

	@NotNull(message = "Crm não pode ser nula")
	@NotBlank(message = "Crm não pode está em branco")
	private Long crm;

	public Long getCrm() {
		return crm;
	}

	public void setCrm(Long crm) {
		this.crm = crm;
	}

}
