package br.com.triagemsystem.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CalcularPrioridadeRequest {

	@NotNull
	@Min(1)
	private int pontuacao;

	@NotNull
	private Long pacienteId;

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

}
