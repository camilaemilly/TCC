package br.diabetes.consulta;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import br.diabetes.consulta.comandos.CriarConsulta;
import br.diabetes.consulta.comandos.EditarConsulta;

@Entity
public class Consulta {
	@EmbeddedId
	private ConsultaId id;
	private String nome;
	private Date data;
	private String medico;
	private String local;

	public Consulta() {
	}

	public Consulta(CriarConsulta comando) {
		this.id = new ConsultaId();
		this.nome = comando.getNome();
		this.data = comando.getData();
		this.medico = comando.getMedico();
		this.local = comando.getLocal();
	}
	
	public void apply(EditarConsulta comando) {
		this.id = comando.getId();
		this.nome = comando.getNome();
		this.data = comando.getData();
		this.medico = comando.getMedico();
		this.local = comando.getLocal();
	}

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
