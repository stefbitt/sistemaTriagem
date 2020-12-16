package br.com.triagemsystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.triagemsystem.enumerate.TipoPrioridade;
import br.com.triagemsystem.model.Paciente;
import br.com.triagemsystem.repository.PacienteRep;
import br.com.triagemsystem.request.CalcularPrioridadeRequest;
import br.com.triagemsystem.response.CalculoResponse;
import javassist.NotFoundException;

@Controller
public class TriagemController {
	
	@Autowired
	private PacienteRep pacienteRep;

	@PostMapping("/calcularPrioridade")
	public ResponseEntity<CalculoResponse> calcularPriodidade(@RequestBody @Valid CalcularPrioridadeRequest request) throws NotFoundException {

		CalculoResponse response = new CalculoResponse();
		TipoPrioridade prioridade = null;
		
		
		Paciente paciente = pacienteRep.findById(request.getPacienteId())
				.orElseThrow(() -> new NotFoundException("Paciente não encontrado"));

		if (request.getPontuacao() >= 1 && request.getPontuacao() <= 10) {
			prioridade = TipoPrioridade.VERDE;
		} else if (request.getPontuacao() >= 11 && request.getPontuacao() <= 30) {
			prioridade = TipoPrioridade.AMARELO;
		} else {
			prioridade = TipoPrioridade.VERMELHO;
		}
		
		response.setPrioridade(prioridade);
		response.setNomePaciente(paciente.getUser().getNome());

		return ResponseEntity.ok(response);
	}

}
