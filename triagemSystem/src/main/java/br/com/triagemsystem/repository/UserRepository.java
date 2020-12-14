package br.com.triagemsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.triagemsystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String username);

}
