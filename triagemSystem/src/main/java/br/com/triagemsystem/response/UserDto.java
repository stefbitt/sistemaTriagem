package br.com.triagemsystem.response;

import br.com.triagemsystem.model.User;

public class UserDto {

	private String nome;
	
	public UserDto(User usuario) {
		this.nome = usuario.getNome();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
