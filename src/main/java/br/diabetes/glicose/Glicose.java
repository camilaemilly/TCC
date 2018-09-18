package br.diabetes.glicose;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Glicose {
	@EmbeddedId
	private GlicoseId id;
	private float valor;
	private Date dataUltimaMedicao;

	public Glicose() {
	}

	public Glicose(Glicose novaGlicose) {
		this.id = new GlicoseId();
		this.valor = novaGlicose.getValor();
		this.dataUltimaMedicao = novaGlicose.getDataUltimaMedicao();
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
