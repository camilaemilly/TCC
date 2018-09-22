package br.diabetes.glicose.comandos;

import java.util.Date;

import br.diabetes.glicose.Glicose;
import br.diabetes.glicose.GlicoseId;

public class BuscarGlicose {
	private GlicoseId id;
	private float valor;
	private Date dataUltimaMedicao;

	public BuscarGlicose(Glicose comandos) {
		this.id = comandos.getId();
		this.valor = comandos.getValor();
		this.dataUltimaMedicao = comandos.getDataUltimaMedicao();
	}
	
	public GlicoseId getId() {
		return id;
	}
	
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