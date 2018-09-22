package br.diabetes.glicose;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import br.diabetes.glicose.comandos.CriarGlicose;
import br.diabetes.glicose.comandos.EditarGlicose;
import br.diabetes.usuario.UsuarioId;

@Entity
public class Glicose {
	@EmbeddedId
	@AttributeOverride(name = "value", column = @Column(name = "id"))
	private GlicoseId id;
	@AttributeOverride(name = "value", column = @Column(name = "id_usuario"))
	private UsuarioId idUsuario;
	private float valor;
	private Date dataUltimaMedicao;

	public Glicose() {
	}

	public Glicose(CriarGlicose comando, UsuarioId id) {
		this.id = new GlicoseId();
		this.idUsuario = id;
		this.valor = comando.getValor();
		this.dataUltimaMedicao = comando.getDataUltimaMedicao();
	}
	
	public void apply(EditarGlicose comando) {
		this.id = comando.getId();
		this.valor = comando.getValor();
		this.dataUltimaMedicao = comando.getDataUltimaMedicao();
	}

	public UsuarioId getIdUsuario() {
		return idUsuario;
	}

	public GlicoseId getId() {
		return id;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public Date getDataUltimaMedicao() {
		return dataUltimaMedicao;
	}

	public void setDataUltimaMedicao(Date dataUltimaMedicao) {
		this.dataUltimaMedicao = dataUltimaMedicao;
	}	
}
