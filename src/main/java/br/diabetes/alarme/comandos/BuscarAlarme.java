package br.diabetes.alarme.comandos;

import java.sql.Time;

import br.diabetes.alarme.Alarme;
import br.diabetes.alarme.AlarmeId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuscarAlarme {
	private AlarmeId id;
	private String descricao;
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