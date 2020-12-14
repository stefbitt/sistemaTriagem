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

import br.com.triagemsystem.model.Paciente;
import br.com.triagemsystem.model.User;
import br.com.triagemsystem.repository.PacienteRep;
import br.com.triagemsystem.request.PacienteRequest;

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

	public void setRepository(PacienteRep repository) {
		this.repository = repository;
	}

	@GetMapping
	public Page<Paciente> findAll(@RequestParam(required = false) String nome,
			@RequestParam(required = false, defaultValue = "0") int pagina,
			@RequestParam(required = false, defaultValue = "10") int quantidade,
			@RequestParam(required = false, defaultValue = "user.nome") String campoOrdenado,
			@RequestParam(required = false, defaultValue = "") String directionType) {
		Direction direction = "desc".equalsIgnoreCase(directionType) ? Direction.DESC : Direction.ASC;
		return repository.findAll(PageRequest.of(pagina, quantidade, Sort.by(direction, campoOrdenado)));
	}

	@GetMapping(path = { "/{pacienteId}" })
	public ResponseEntity<Paciente> findById(@PathVariable @Valid long pacienteId) {
		return repository.findById(pacienteId).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Paciente create(@RequestBody @Valid PacienteRequest pacienteRequest) throws Exception {
		Paciente paciente = new Paciente();
		paciente.setCpf(pacienteRequest.getCpf());
		
		User user = new User();
		user.setNome(pacienteRequest.getNome());
		user.setEmail(pacienteRequest.getEmail());
		user.setSenha(pacienteRequest.getSenha());
		
		paciente.setUser(user);
		
		try {
			paciente = repository.save(paciente);
		} catch (DataIntegrityViolationException e) {
			throw new Exception("Error sistemico");
		}
		return paciente;
	}

	@PutMapping(value = "/{pacienteId}")
	public ResponseEntity<Paciente> update(@PathVariable("pacienteId") long pacienteid,
			@RequestBody @Valid PacienteRequest pacienteRequest) {
		return repository.findById(pacienteid).map(record -> {
			User user = record.getUser();
			user.setNome(pacienteRequest.getNome());
			user.setEmail(pacienteRequest.getEmail());
			
			record.setCpf(pacienteRequest.getCpf());
			record.setUser(user);
			
			return ResponseEntity.ok().body(repository.save(record));
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
