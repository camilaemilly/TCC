package br.diabetes.remedio.comandos;

import java.sql.Time;
import java.util.Date;

import br.diabetes.remedio.RemedioId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarRemedio {
	private RemedioId id;
	private String nome;
	private Date dataInicio;
	private Date dataFim;
	private String composicao;
	private Time horario;
	
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
