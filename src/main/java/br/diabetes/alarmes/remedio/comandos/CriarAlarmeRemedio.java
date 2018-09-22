package br.diabetes.alarmes.remedio.comandos;

import java.sql.Time;

import br.diabetes.remedio.RemedioId;

public class CriarAlarmeRemedio {
	private String descricao;
	private Time horario;
	private boolean status;
	private RemedioId idRemedio;
	
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

	public RemedioId getIdRemedio() {
		return idRemedio;
	}

	public void setIdRemedio(RemedioId idRemedio) {
		this.idRemedio = idRemedio;
	}
	
}