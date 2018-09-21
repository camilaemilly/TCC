package br.diabetes.glicose.comandos;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarGlicose {
	private float valor;
	private Date dataUltimaMedicao;

	public float getValor() {
		return valor;
	}

	public void setNome(float valor) {
		this.valor = valor;
	}
	
	public Date getDataUltimaMedicao() {
		return dataUltimaMedicao;
	}

	public void setDataUltimaMedicao(Date dataUltimaMedicao) {
		this.dataUltimaMedicao = dataUltimaMedicao;
	}
}
