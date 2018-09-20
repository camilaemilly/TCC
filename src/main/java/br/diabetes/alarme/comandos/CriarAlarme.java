package br.diabetes.alarme.comandos;

import java.sql.Time;

public class CriarAlarme {
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
	
}
