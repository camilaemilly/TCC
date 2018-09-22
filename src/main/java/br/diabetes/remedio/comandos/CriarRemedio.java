package br.diabetes.remedio.comandos;

import java.util.Date;

public class CriarRemedio {
	private String nome;
	private Date dataInicio;
	private Date dataFim;
	private String composicao;

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
