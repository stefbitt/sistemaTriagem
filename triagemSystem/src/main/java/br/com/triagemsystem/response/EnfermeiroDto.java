package br.com.triagemsystem.response;
import br.com.triagemsystem.model.Enfermeiro;

public class EnfermeiroDto {

	private Long matricula;
	private UserDto userDto;

	public EnfermeiroDto(Enfermeiro enfermeiro) {
		this.matricula = enfermeiro.getMatricula();
		this.userDto = new UserDto(enfermeiro.getUser());
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

//	public static List<EnfermeiroDto> converter(List<Enfermeiro> enfermeiro) {
//        return enfermeiro.stream().map(EnfermeiroDto::new).collect(Collectors.toList());
//    }

}
