package br.com.triagemsystem.response;

import br.com.triagemsystem.enumerate.TipoPrioridade;
import br.com.triagemsystem.model.Atendimento;

public class AtendimentoResponse {

	private TipoPrioridade tipoPrioridade;
	private Long cod;
	private PacienteDto pacienteDto;

	public TipoPrioridade getTipoPrioridade() {
		return tipoPrioridade;
	}

	public void setTipoPrioridade(TipoPrioridade tipoPrioridade) {
		this.tipoPrioridade = tipoPrioridade;
	}

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public PacienteDto getPacienteDto() {
		return pacienteDto;
	}

	public void setPacienteDto(PacienteDto pacienteDto) {
		this.pacienteDto = pacienteDto;
	}

	public static AtendimentoResponse save(Atendimento atendimento) {
		// TODO Auto-generated method stub
		return null;
	}

}
