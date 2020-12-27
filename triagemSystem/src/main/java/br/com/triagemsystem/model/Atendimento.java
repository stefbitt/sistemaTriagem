package br.com.triagemsystem.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.triagemsystem.enumerate.TipoAtendimento;

@Entity
@Table(name = "t_atendimento")
public class Atendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "atendimentoID")
	private Long atendimentoId;
	
	private TipoAtendimento tipoAtendimento;

	@OneToOne
	@JoinColumn(name = "pacienteID")
	private Paciente paciente;
	//Horário de fila de espera por ordem de chegada do paciente
	private LocalDateTime horarioChegada;
	// Inicio do atendimento medico p/ paciente
	private LocalDateTime horarioInicioAtendimento;
	//Fim atendimento
	private LocalDateTime horarioFimAtendimento;
	
	@OneToOne
	@JoinColumn(name = "medicoID", nullable = true)
	private Medico medico;

	public Long getAtendimentoId() {
		return atendimentoId;
	}
	
	public TipoAtendimento getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}



	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalDateTime getHorarioChegada() {
		return horarioChegada;
	}

	public void setHorarioChegada(LocalDateTime horarioChegada) {
		this.horarioChegada = horarioChegada;
	}

	public LocalDateTime getHorarioInicioAtendimento() {
		return horarioInicioAtendimento;
	}

	public void setHorarioInicioAtendimento(LocalDateTime horarioInicioAtendimento) {
		this.horarioInicioAtendimento = horarioInicioAtendimento;
	}

	public LocalDateTime getHorarioFimAtendimento() {
		return horarioFimAtendimento;
	}

	public void setHorarioFimAtendimento(LocalDateTime horarioFimAtendimento) {
		this.horarioFimAtendimento = horarioFimAtendimento;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

}
