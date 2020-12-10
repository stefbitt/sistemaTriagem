package br.com.triagemsystem.controller;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Page<Medico> findAll(@RequestParam String nome,
			@RequestParam(required = false, defaultValue = "0") int pagina,
			@RequestParam(required = false, defaultValue = "10") int quantidade,
			@RequestParam(required = false, defaultValue = "") String campoOrdenado,
			@RequestParam(required = false, defaultValue = "") String directionType) {
		Direction direction = "desc".equalsIgnoreCase(directionType) ? Direction.DESC : Direction.ASC;
		return repository.findAll(PageRequest.of(pagina, quantidade, Sort.by(direction, campoOrdenado)));
	}

	@GetMapping(path = { "/{medicoId}" })
	public ResponseEntity<Medico> findById(@PathVariable @Valid long medicoId) {
		return repository.findById(medicoId).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Medico create(@RequestBody @Valid Medico medico) throws Exception {
		Medico medicoSalvo = null;
		try {
			medicoSalvo = repository.save(medico);
		} catch (DataIntegrityViolationException e) {
			throw new Exception("Error sistemico");
		}
		return medicoSalvo;
	}

	@PutMapping(value = "/{medicoId}")
	public ResponseEntity<Medico> update(@PathVariable("medicoId") long medicoid, @RequestBody @Valid Medico medico) {
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
	public ResponseEntity<Object> delete(@PathVariable @Valid long medicoId) {
		return repository.findById(medicoId).map(record -> {
			repository.deleteById(medicoId);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
