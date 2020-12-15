package br.com.triagemsystem.response;

import br.com.triagemsystem.model.Medico;

public class MedicoDto {

	private Long crm;
	private UserDto userDto;

	public MedicoDto(Medico medico) {
		this.crm = medico.getCrm();
		this.userDto = new UserDto(medico.getUser());
	}

	public Long getCrm() {
		return crm;
	}

	public void setCrm(Long crm) {
		this.crm = crm;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

}
