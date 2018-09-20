package br.diabetes.alarme.comandos;

import java.sql.Time;

import br.diabetes.alarme.Alarme;
import br.diabetes.alarme.AlarmeId;

public class BuscarAlarme {
	private AlarmeId id;
	private Time horario;
	private boolean status;

	public BuscarAlarme(Alarme comandos) {
		this.id = comandos.getId();
		this.horario = comandos.getHorario();
		this.status = comandos.getStatus();
	}

	public Time getHorario() {
		return horario;
	}

	public void setHorario(Time horario) {
		this.horario = horario;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public AlarmeId getId() {
		return id;
	}
}