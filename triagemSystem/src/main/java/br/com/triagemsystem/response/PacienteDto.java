package br.com.triagemsystem.response;
import br.com.triagemsystem.model.Paciente;

public class PacienteDto {

	    private Long cpf;
		private UserDto userDto;
	    
		
		public PacienteDto(Paciente paciente) {
	        this.cpf = paciente.getCpf();
	        this.userDto = new UserDto(paciente.getUser());
	        }

		public Long getMatricula() {
			return cpf;
		}



		public void setMatricula(Long matricula) {
			this.cpf = matricula;
		}



		public UserDto getUser() {
			return userDto;
		}



		public void setUser(UserDto userDto) {
			this.userDto = userDto;
		}



//		public static List<PacienteDto> converter(List<Paciente> enfermeiro) {
//	        return enfermeiro.stream().map(PacienteDto::new).collect(Collectors.toList());
//	    }
//		

	}