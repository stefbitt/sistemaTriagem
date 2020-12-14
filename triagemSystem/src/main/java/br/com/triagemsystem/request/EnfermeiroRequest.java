package br.com.triagemsystem.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EnfermeiroRequest extends UserRequest {

	@NotNull(message = "Matricula não pode ser nula")
	@NotBlank(message = "Matricula não pode está em branco")
	private Long matricula;

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

}
