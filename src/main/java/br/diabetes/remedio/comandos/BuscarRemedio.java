package br.diabetes.remedio.comandos;

import java.util.Date;

import br.diabetes.remedio.Remedio;
import br.diabetes.remedio.RemedioId;

public class BuscarRemedio {
	private RemedioId id;
	private String nome;
	private Date dataInicio;
	private Date dataFim;
	private String composicao;

	public BuscarRemedio(Remedio comandos) {
		this.id = comandos.getId();
		this.nome = comandos.getNome();
		this.dataInicio = comandos.getDataInicio();
		this.dataFim = comandos.getDataFim();
		this.composicao = comandos.getComposicao();
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
}