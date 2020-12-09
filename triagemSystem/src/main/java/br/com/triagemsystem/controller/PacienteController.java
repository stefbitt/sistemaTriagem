package br.com.triagemsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.triagemsystem.model.Paciente;
import br.com.triagemsystem.repository.PacienteRep;

//@Autowired e @valid
@RestController
@RequestMapping({ "/pacientes" })
public class PacienteController {

	private PacienteRep repository;

	PacienteController(PacienteRep pacienteRep) {
		this.setRepository(pacienteRep);
	}

	public PacienteRep getRepository() {
		return repository;
	}

	public void setRepository(PacienteRep  repository) {
		this.repository = repository;
	}

	@GetMapping
	public List<Paciente> findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{pacienteId}" })
	public ResponseEntity<Paciente> findById(@PathVariable @Valid long medicoId) {
		return repository.findById(medicoId).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Paciente create(@RequestBody @Valid Paciente paciente) throws Exception {
		Paciente pacienteSalvo = null;
		try {
			pacienteSalvo = repository.save(paciente);
		} catch (DataIntegrityViolationException e) {
			throw new Exception("Error sistemico");
		}
		return pacienteSalvo;
	}

	@PutMapping(value = "/{pacienteId}")
	public ResponseEntity<Paciente> update(@PathVariable("pacienteId") long pacienteid, @RequestBody @Valid Paciente paciente) {
		return repository.findById(pacienteid).map(record -> {
			record.setNome(paciente.getNome());
			record.setEmail(paciente.getEmail());
			record.setCpf(paciente.getCpf());
			record.setSenha(paciente.getSenha());
			Paciente updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{pacienteId}" })
	public ResponseEntity<Object> delete(@PathVariable @Valid long pacienteId) {
		return repository.findById(pacienteId).map(record -> {
			repository.deleteById(pacienteId);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
