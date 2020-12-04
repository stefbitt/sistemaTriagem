package br.com.triagemsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.triagemsystem.model.Paciente;

@Repository
public interface PacienteRep extends JpaRepository<Paciente, Long> {
	 
} 
      
