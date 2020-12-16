package br.com.triagemsystem.enumerate;

public enum TipoPrioridade {

	VERMELHO(1), AMARELO(2), VERDE(3);

	private Integer cod;

	TipoPrioridade(int codPrioridade) {
		this.cod = codPrioridade;
	}

}
