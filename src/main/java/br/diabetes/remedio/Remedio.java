package br.diabetes.remedio;

import java.sql.Time;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Remedio {
	@EmbeddedId
	private RemedioId id;
	private String nome;
	private Date dataInicio;
	private Date dataFim;
	private String composicao;
	private Time horario;

	public Remedio() {
	}

	public Remedio(Remedio novoRemedio) {
		this.id = new RemedioId();
		this.nome = novoRemedio.getNome();
		this.dataInicio = novoRemedio.getDataInicio();
		this.dataFim = novoRemedio.getDataFim();
		this.composicao = novoRemedio.getComposicao();
		this.horario = novoRemedio.getHorario();
	}

	public RemedioId getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setData(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	public String getComposicao() {
		return composicao;
	}

	public void setComposicao(String composicao) {
		this.composicao = composicao;
	}
	
	public Time getHorario() {
		return horario;
	}

	public void setHorario(Time horario) {
		this.horario = horario;
	}
}
