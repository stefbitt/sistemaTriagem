package br.com.triagemsystem.controller;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.triagemsystem.model.Enfermeiro;
import br.com.triagemsystem.model.User;
import br.com.triagemsystem.repository.EnfermeiroRep;
import br.com.triagemsystem.request.EnfermeiroRequest;
import br.com.triagemsystem.response.EnfermeiroDto;

@RestController
@RequestMapping({ "/enfermeiros" })
public class EnfermeiroController {

	private EnfermeiroRep repository;

	EnfermeiroController(EnfermeiroRep enfermeiroRep) {
		this.setRepository(enfermeiroRep);
	}

	public EnfermeiroRep getRepository() {
		return repository;
	}

	public void setRepository(EnfermeiroRep repository) {
		this.repository = repository;
	}

	@GetMapping
	public Page<EnfermeiroDto> findAll(@RequestParam(required = false) String nome,
			@RequestParam(required = false, defaultValue = "0") int pagina,
			@RequestParam(required = false, defaultValue = "10") int quantidade,
			@RequestParam(required = false, defaultValue = "user.nome") String campoOrdenado,
			@RequestParam(required = false, defaultValue = "") String directionType) {
		Direction direction = "desc".equalsIgnoreCase(directionType) ? Direction.DESC : Direction.ASC;
		return repository.findAll(PageRequest.of(pagina, quantidade, Sort.by(direction, campoOrdenado))).map(EnfermeiroDto::new);
	}

	@GetMapping(path = { "/{enfermeiroId}" })
	public ResponseEntity<Enfermeiro> findById(@PathVariable @Valid long enfermeiroId) {

		return repository.findById(enfermeiroId).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Enfermeiro create(@RequestBody @Valid EnfermeiroRequest enfermeiroRequest) throws Exception {
		Enfermeiro enfermeiro = new Enfermeiro();
		enfermeiro.setMatricula(enfermeiroRequest.getMatricula());

		User user = new User();
		user.setNome(enfermeiroRequest.getNome());
		user.setEmail(enfermeiroRequest.getEmail());
		user.setSenha(enfermeiroRequest.getSenha());

		enfermeiro.setUser(user);

		try {
			enfermeiro = repository.save(enfermeiro);
		} catch (DataIntegrityViolationException e) {
			throw new Exception("Error sistemico");
		} catch (HttpMessageNotReadableException e) {
			throw new Exception("Error 404");
		}
		return enfermeiro;
	}

	@PutMapping(value = "/{enfermeiroId}")
	public ResponseEntity<Enfermeiro> update(@PathVariable("enfermeiroId") long enfermeiroid,
			@RequestBody @Valid EnfermeiroRequest enfermeiroRequest) {
		return repository.findById(enfermeiroid).map(record -> {
			User user = record.getUser();
			user.setNome(enfermeiroRequest.getNome());
			user.setEmail(enfermeiroRequest.getEmail());

			record.setMatricula(enfermeiroRequest.getMatricula());
			record.setUser(user);

			return ResponseEntity.ok().body(repository.save(record));
		}).orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Deleta enfermeiro cadastrado.
	 * 
	 * @param enfermeiroId id do enfeimeiro a será excluido
	 * @return ResponseEntity
	 */
	@DeleteMapping(path = { "/{enfermeiroId}" })
	public ResponseEntity<?> delete(@PathVariable @Valid long enfermeiroId) {
		return repository.findById(enfermeiroId).map(record -> {
			repository.deleteById(enfermeiroId);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
