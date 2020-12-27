package br.com.triagemsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.triagemsystem.model.Atendimento;

@Repository
public interface AtendimentoRep extends JpaRepository<Atendimento, Long> {

}
