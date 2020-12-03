package br.com.triagemsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.triagemsystem.model.Medico;

@Repository
public interface MedicoRep extends JpaRepository<Medico, Long> {
	 
} 
      
