package br.diabetes.remedio;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import br.diabetes.remedio.comandos.CriarRemedio;
import br.diabetes.remedio.comandos.EditarRemedio;
import br.diabetes.usuario.UsuarioId;

@Entity
public class Remedio {
	@EmbeddedId
	@AttributeOverride(name = "value", column = @Column(name = "id"))
	private RemedioId id;
	@AttributeOverride(name = "value", column = @Column(name = "id_usuario"))
	private UsuarioId idUsuario;
	private String nome;
	private Date dataInicio;
	private Date dataFim;
	private String composicao;

	public Remedio() {
	}

	public Remedio(CriarRemedio comando, UsuarioId id) {
		this.id = new RemedioId();
		this.idUsuario = id;
		this.nome = comando.getNome();
		this.dataInicio = comando.getDataInicio();
		this.dataFim = comando.getDataFim();
		this.composicao = comando.getComposicao();
	}
	
	public void apply(EditarRemedio comando) {
		this.id = comando.getId();
		this.nome = comando.getNome();
		this.dataInicio = comando.getDataInicio();
		this.dataFim = comando.getDataFim();
		this.composicao = comando.getComposicao();
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

	public UsuarioId getIdUsuario() {
		return idUsuario;
	}
}
