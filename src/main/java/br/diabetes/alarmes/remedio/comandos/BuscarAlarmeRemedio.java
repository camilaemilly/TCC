package br.diabetes.alarmes.remedio.comandos;

import java.util.Date;

import br.diabetes.alarmes.remedio.AlarmeRemedio;
import br.diabetes.alarmes.remedio.AlarmeRemedioId;
import br.diabetes.conversor.ConverterHora;
import br.diabetes.remedio.comandos.BuscarRemedio;

public class BuscarAlarmeRemedio {
	private AlarmeRemedioId id;
	private String descricao;
	private String horario;
	private Date dataInicio;
	private Date dataFim;
	private boolean status;
	private BuscarRemedio remedio;

	public BuscarAlarmeRemedio(AlarmeRemedio comandos) {
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

	public AlarmeRemedioId getId() {
		return id;
	}

	public BuscarRemedio getRemedio() {
		return remedio;
	}

	public void setRemedio(BuscarRemedio remedio) {
		this.remedio = remedio;
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