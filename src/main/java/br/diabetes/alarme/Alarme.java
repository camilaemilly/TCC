package br.diabetes.alarme;

import java.sql.Time;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Alarme {
	@EmbeddedId
	private AlarmeId id;
	private Time horario;
	private boolean status;

	public Alarme() {
	}

	public Alarme(Alarme novoAlarme) {
		this.id = new AlarmeId();
		this.horario = novoAlarme.getHorario();
		this.status = novoAlarme.getStatus();
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
