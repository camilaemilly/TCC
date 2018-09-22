package br.diabetes.alarmes.remedio;

import java.sql.Time;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import br.diabetes.alarmes.remedio.comandos.CriarAlarmeRemedio;
import br.diabetes.alarmes.remedio.comandos.EditarAlarmeRemedio;
import br.diabetes.usuario.UsuarioId;
import br.diabetes.remedio.RemedioId;

@Entity
public class AlarmeRemedio {
	@EmbeddedId
	@AttributeOverride(name = "value", column = @Column(name = "id"))
	private AlarmeRemedioId id;
	@AttributeOverride(name = "value", column = @Column(name = "id_usuario"))
	private UsuarioId idUsuario;
	private Time horario;
	private String descricao;
	private boolean status;
	@AttributeOverride(name = "value", column = @Column(name = "id_medicamento"))
	private RemedioId idRemedio;

	public AlarmeRemedio() {
	}

	public AlarmeRemedio(CriarAlarmeRemedio comando, UsuarioId id) {
		this.id = new AlarmeRemedioId();
		this.idUsuario = id;
		this.descricao = comando.getDescricao();
		this.horario = comando.getHorario();
		this.status = comando.getStatus();
		this.setIdRemedio(comando.getIdRemedio());
	}
	
	public void apply(EditarAlarmeRemedio comando) {
		this.id = comando.getId();
		this.descricao = comando.getDescricao();
		this.horario = comando.getHorario();
		this.status = comando.getStatus();
		this.setIdRemedio(comando.getIdRemedio());
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

	public AlarmeRemedioId getId() {
		return id;
	}

	public RemedioId getIdRemedio() {
		return idRemedio;
	}

	public void setIdRemedio(RemedioId idRemedio) {
		this.idRemedio = idRemedio;
	}
}
