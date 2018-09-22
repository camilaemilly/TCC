package br.diabetes.alarmes.consulta.comandos;

import java.sql.Time;
import java.util.Date;

import br.diabetes.consulta.ConsultaId;

public class CriarAlarmeConsulta {
	private String descricao;
	private Time horario;
	private Date dataInicio;
	private Date dataFim;
	private boolean status;
	private ConsultaId idConsulta;
	
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

	public ConsultaId getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(ConsultaId idConsulta) {
		this.idConsulta = idConsulta;
	}
	
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
}
