package br.diabetes.alarmes.remedio.comandos;

import java.sql.Time;
import java.util.Date;

import br.diabetes.remedio.RemedioId;

public class CriarAlarmeRemedio {
	private String descricao;
	private Time horario;
	private Date dataInicio;
	private Date dataFim;
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
	
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
}
