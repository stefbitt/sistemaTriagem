package br.com.triagemsystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.triagemsystem.model.Medico;
import br.com.triagemsystem.repository.MedicoRep;

@RestController
@RequestMapping({ "/medicos" })
public class MedicoController {

	private MedicoRep repository;

	MedicoController(MedicoRep medicoRep) {
		this.setRepository(medicoRep);
	}

	public MedicoRep getRepository() {
		return repository;
	}

	public void setRepository(MedicoRep repository) {
		this.repository = repository;
	}

	@GetMapping
	public List<Medico> findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{medicoId}" })
	public ResponseEntity<Medico> findById(@PathVariable long medicoId) {
		return repository.findById(medicoId).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Medico create(@RequestBody Medico medico) {
		return repository.save(medico);
	}

	@PutMapping(value = "/{medicoId}")
	public ResponseEntity<Medico> update(@PathVariable("medicoId") long medicoid, @RequestBody Medico medico) {
		return repository.findById(medicoid).map(record -> {
			record.setNome(medico.getNome());
			record.setEmail(medico.getEmail());
			record.setCrm(medico.getCrm());
			record.setSenha(medico.getSenha());
			Medico updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{medicoId}" })
	public ResponseEntity<?> delete(@PathVariable long medicoId) {
		return repository.findById(medicoId).map(record -> {
			repository.deleteById(medicoId);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
