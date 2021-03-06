package br.diabetes.alarmes.consulta.comandos;

import java.util.Date;

import br.diabetes.alarmes.consulta.AlarmeConsulta;
import br.diabetes.alarmes.consulta.AlarmeConsultaId;
import br.diabetes.conversor.ConverterHora;
import br.diabetes.consulta.comandos.BuscarConsulta;

public class BuscarAlarmeConsulta {
	private AlarmeConsultaId id;
	private String descricao;
	private String horario;
	private Date dataInicio;
	private Date dataFim;
	private boolean status;
	private BuscarConsulta consulta;

	public BuscarAlarmeConsulta(AlarmeConsulta comandos) {
		this.id = comandos.getId();
		this.horario = ConverterHora.converterHora(comandos.getHorario());
		this.dataInicio = comandos.getDataInicio();
		this.dataFim = comandos.getDataFim();
		this.status = comandos.getStatus();
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
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

	public AlarmeConsultaId getId() {
		return id;
	}

	public BuscarConsulta getConsulta() {
		return consulta;
	}

	public void setConsulta(BuscarConsulta consulta) {
		this.consulta = consulta;
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