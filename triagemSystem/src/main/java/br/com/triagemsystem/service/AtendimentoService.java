package br.com.triagemsystem.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.triagemsystem.enumerate.TipoAtendimento;
import br.com.triagemsystem.enumerate.TipoPrioridade;
import br.com.triagemsystem.model.Atendimento;
import br.com.triagemsystem.model.Medico;
import br.com.triagemsystem.model.Paciente;
import br.com.triagemsystem.repository.AtendimentoRep;
import br.com.triagemsystem.repository.MedicoRep;
import br.com.triagemsystem.repository.PacienteRep;
import br.com.triagemsystem.request.CalcularPrioridadeRequest;
import br.com.triagemsystem.request.InicializarAtendimentoRequest;
import br.com.triagemsystem.response.AtendimentoResponse;
import br.com.triagemsystem.response.PacienteDto;
import javassist.NotFoundException;

@Service
public class AtendimentoService {

	@Autowired
	private PacienteRep pacienteRep;

	@Autowired
	private AtendimentoRep atendimentoRep;

	@Autowired
	private MedicoRep medicoRep;

	public AtendimentoResponse save(CalcularPrioridadeRequest request) throws NotFoundException {
		Atendimento atendimento = new Atendimento();
		AtendimentoResponse atendimentoResponse = new AtendimentoResponse();
		TipoPrioridade prioridade = calcularPrioridadeAtendimento(request.getPontuacao());

		Paciente paciente = pacienteRep.findById(request.getPacienteId())
				.orElseThrow(() -> new NotFoundException("Paciente não encontrado"));

		if (prioridade.equals(TipoPrioridade.VERMELHO)) {
			atendimento.setTipoAtendimento(TipoAtendimento.URGENTE);
		} else if (prioridade.equals(TipoPrioridade.AMARELO)) {
			atendimento.setTipoAtendimento(TipoAtendimento.PRIORITARIO);
		} else {
			atendimento.setTipoAtendimento(TipoAtendimento.NORMAL);
		}

		atendimento.setPaciente(paciente);
		atendimento.setHorarioChegada(LocalDateTime.now());
		atendimento = atendimentoRep.save(atendimento);

		atendimentoResponse.setCod(atendimento.getAtendimentoId());
		atendimentoResponse.setTipoPrioridade(prioridade);
		atendimentoResponse.setPacienteDto(new PacienteDto(paciente));

		return atendimentoResponse;
	}

	private TipoPrioridade calcularPrioridadeAtendimento(Integer pontuacao) {
		TipoPrioridade prioridade;
		if (pontuacao >= 1 && pontuacao <= 10) {
			prioridade = TipoPrioridade.VERDE;
		} else if (pontuacao >= 11 && pontuacao <= 30) {
			prioridade = TipoPrioridade.AMARELO;
		} else {
			prioridade = TipoPrioridade.VERMELHO;
		}
		return prioridade;
	}

	public void inicializarAtendimento(Long atendimentoId, InicializarAtendimentoRequest inicializarAtendimento)
			throws NotFoundException {
		Atendimento atendimento = atendimentoRep.findById(atendimentoId)
				.orElseThrow(() -> new NotFoundException("Atendimento não encontrado"));

		Medico medico = medicoRep.findById(inicializarAtendimento.getMedicoId())
				.orElseThrow(() -> new NotFoundException("Médico não encontrado"));

		atendimento.setHorarioInicioAtendimento(LocalDateTime.now());
		atendimento.setMedico(medico);

		atendimentoRep.save(atendimento);

	}
}
