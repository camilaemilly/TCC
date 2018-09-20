package br.diabetes.glicose;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import br.diabetes.glicose.comandos.CriarGlicose;
import br.diabetes.glicose.comandos.EditarGlicose;

@Entity
public class Glicose {
	@EmbeddedId
	private GlicoseId id;
	private float valor;
	private Date dataUltimaMedicao;

	public Glicose() {
	}

	public Glicose(CriarGlicose comando) {
		this.id = new GlicoseId();
		this.valor = comando.getValor();
		this.dataUltimaMedicao = comando.getDataUltimaMedicao();
	}
	
	public void apply(EditarGlicose comando) {
		this.id = comando.getId();
		this.valor = comando.getValor();
		this.dataUltimaMedicao = comando.getDataUltimaMedicao();
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
