package br.diabetes.consulta.comandos;

import java.util.Date;

import br.diabetes.consulta.ConsultaId;

public class EditarConsulta {
	private ConsultaId id;
	private String nome;
	private Date data;
	private String medico;
	private String local;
	
	public ConsultaId getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}
	
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
}
