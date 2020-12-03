package br.com.triagemsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.triagemsystem.model.Enfermeiro;

@Repository
public interface EnfermeiroRep extends JpaRepository<Enfermeiro, Long> {

}
