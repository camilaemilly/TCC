package br.diabetes.alarme.comandos;

import java.sql.Time;

import br.diabetes.alarme.AlarmeId;

public class EditarAlarme {
	private AlarmeId id;
	private Time horario;
	private boolean status;
	
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
	
	public AlarmeId getId() {
		return id;
	}
}
