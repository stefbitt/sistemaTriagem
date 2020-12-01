package br.com.triagemsystem;

import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.triagemsystem.model.Medico;
import br.com.triagemsystem.repository.MedicoRep;

@SpringBootApplication
public class TriagemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TriagemApplication.class, args);
	}

	@Bean
	CommandLineRunner init(MedicoRep repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 11).mapToObj(i -> {
				Medico medicoTest = new Medico();
				medicoTest.setNome("Médico: " + i);
				medicoTest.setEmail("Médico:" + i + "@email.com");
				medicoTest.setCrm(i);
				return medicoTest;
			}).map(v -> repository.save(v)).forEach(System.out::println);
		};
	}
}