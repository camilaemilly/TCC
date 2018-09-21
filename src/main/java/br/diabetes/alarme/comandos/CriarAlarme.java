package br.diabetes.alarme.comandos;

import java.sql.Time;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarAlarme {
	private String descricao;
	private Time horario;
	private boolean status;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
