package br.diabetes.alarme;

import java.sql.Time;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import br.diabetes.alarme.comandos.CriarAlarme;
import br.diabetes.alarme.comandos.EditarAlarme;

@Entity
public class Alarme {
	@EmbeddedId
	private AlarmeId id;
	private Time horario;
	private boolean status;

	public Alarme() {
	}

	public Alarme(CriarAlarme comando) {
		this.id = new AlarmeId();
		this.horario = comando.getHorario();
		this.status = comando.getStatus();
	}
	
	public void apply(EditarAlarme comando) {
		this.id = comando.getId();
		this.horario = comando.getHorario();
		this.status = comando.getStatus();
	}

	public AlarmeId getId() {
		return id;
	}

	public Time getHorario() {
		return horario;
	}

	public void setHorario(Time horario) {
		this.horario = horario;
	}
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
