package br.diabetes.alarme;

import java.sql.Time;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import br.diabetes.alarme.comandos.CriarAlarme;
import br.diabetes.alarme.comandos.EditarAlarme;
import br.diabetes.usuario.UsuarioId;

@Entity
public class Alarme {
	@EmbeddedId
	@AttributeOverride(name = "value", column = @Column(name = "id"))
	private AlarmeId id;
	@AttributeOverride(name = "value", column = @Column(name = "id_usuario"))
	private UsuarioId idUsuario;
	private Time horario;
	private String descricao;
	private boolean status;

	public Alarme() {
	}

	public Alarme(CriarAlarme comando, UsuarioId id) {
		this.id = new AlarmeId();
		this.idUsuario = id;
		this.descricao = comando.getDescricao();
		this.horario = comando.getHorario();
		this.status = comando.getStatus();
	}
	
	public void apply(EditarAlarme comando) {
		this.id = comando.getId();
		this.descricao = comando.getDescricao();
		this.horario = comando.getHorario();
		this.status = comando.getStatus();
	}
	
	public Time getHorario() {
		return horario;
	}

	public void setHorario(Time horario) {
		this.horario = horario;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public AlarmeId getId() {
		return id;
	}
}
