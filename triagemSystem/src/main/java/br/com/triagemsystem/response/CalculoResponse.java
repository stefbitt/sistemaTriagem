package br.com.triagemsystem.response;

import br.com.triagemsystem.enumerate.TipoPrioridade;

public class CalculoResponse {
	
	private TipoPrioridade prioridade;
	private String nomePaciente;

	public TipoPrioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(TipoPrioridade prioridade) {
		this.prioridade = prioridade;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}	

}
