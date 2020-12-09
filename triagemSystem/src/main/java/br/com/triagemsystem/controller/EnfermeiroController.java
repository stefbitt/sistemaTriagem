package br.com.triagemsystem.controller;

import javax.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import br.com.triagemsystem.repository.EnfermeiroRep;

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
	public Page<Enfermeiro> findAll(@RequestParam String nome,
			@RequestParam(required = false, defaultValue = "0") int pagina,
			@RequestParam(required = false, defaultValue = "10") int quantidade) {
		return repository.findAll(PageRequest.of(pagina, quantidade));
	}

	@GetMapping(path = { "/{enfermeiroId}" })
	public ResponseEntity<Enfermeiro> findById(@PathVariable @Valid long enfermeiroId) {
		
		return repository.findById(enfermeiroId).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Enfermeiro create(@RequestBody @Valid Enfermeiro enfermeiro) throws Exception {
		Enfermeiro enfermeiroSalvo = null;
		try {
			enfermeiroSalvo = repository.save(enfermeiro);
		} catch (DataIntegrityViolationException e) {
			throw new Exception("Error sistemico");
		} catch(HttpMessageNotReadableException e){
			throw new Exception("Error 404");
		}
		return enfermeiroSalvo;
	}
	

	@PutMapping(value = "/{enfermeiroId}")
	public ResponseEntity<Enfermeiro> update(@PathVariable("enfermeiroId") long enfermeiroid, @RequestBody @Valid Enfermeiro enfermeiro) {
		return repository.findById(enfermeiroid).map(record -> {
			record.setNome(enfermeiro.getNome());
			record.setEmail(enfermeiro.getEmail());
			record.setMatricula(enfermeiro.getMatricula());
			record.setSenha(enfermeiro.getSenha());
		Enfermeiro updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	/**
	 * Deleta enfermeiro cadastrado.
	 * @param enfermeiroId id do enfeimeiro a ser excluido
	 * @return ResponseEntity
	 */
	@DeleteMapping(path ={"/{enfermeiroId}"})
	public ResponseEntity <?> delete(@PathVariable @Valid long enfermeiroId) {
	   return repository.findById(enfermeiroId)
	           .map(record -> {
	               repository.deleteById(enfermeiroId);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}

}
