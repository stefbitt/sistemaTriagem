package br.com.triagemsystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.triagemsystem.request.CalcularPrioridadeRequest;
import br.com.triagemsystem.response.AtendimentoResponse;
import br.com.triagemsystem.service.AtendimentoService;
import javassist.NotFoundException;

@Controller
@RequestMapping("/triagem")
public class TriagemController {
	
	@Autowired
	private AtendimentoService atendimentoService;

	@PostMapping("/initial")
	public ResponseEntity<AtendimentoResponse> calcularPriodidade(@RequestBody @Valid CalcularPrioridadeRequest request) throws NotFoundException {
		return ResponseEntity.ok(atendimentoService.save(request));
	}
	
	@PostMapping("/inicarAtentimento")
	public ResponseEntity<AtendimentoResponse> calcularPriodidade1() {
		return null;
	}
	
	@PostMapping("/finalizarAtentimento")
	public ResponseEntity<AtendimentoResponse> calcularPriodidade2() {
		return null;
	}
	
	@PostMapping("/chamarProximo")
	public ResponseEntity<AtendimentoResponse> calcularPriodidade3() {
		return null;
	}
	

}
