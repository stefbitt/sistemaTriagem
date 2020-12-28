package br.com.triagemsystem.request;

import javax.validation.constraints.NotNull;

public class InicializarAtendimentoRequest {

	@NotNull
	private Long medicoId;

	public Long getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(Long medicoId) {
		this.medicoId = medicoId;
	}

}
