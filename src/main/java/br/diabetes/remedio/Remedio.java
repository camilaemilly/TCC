package br.diabetes.remedio;

import java.sql.Time;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import br.diabetes.remedio.comandos.CriarRemedio;
import br.diabetes.remedio.comandos.EditarRemedio;
import br.diabetes.usuario.UsuarioId;
import lombok.AccessLevel;
import lombok.Setter;

@Entity
public class Remedio {
	@EmbeddedId
	@AttributeOverride(name = "value", column = @Column(name = "id"))
	@Setter(AccessLevel.NONE)
	private RemedioId id;
	@AttributeOverride(name = "value", column = @Column(name = "id_usuario"))
	private UsuarioId idUsuario;
	private String nome;
	private Date dataInicio;
	private Date dataFim;
	private String composicao;
	private Time horario;

	public Remedio() {
	}

	public Remedio(CriarRemedio comando, UsuarioId id) {
		this.id = new RemedioId();
		this.idUsuario = id;
		this.nome = comando.getNome();
		this.dataInicio = comando.getDataInicio();
		this.dataFim = comando.getDataFim();
		this.composicao = comando.getComposicao();
		this.horario = comando.getHorario();
	}
	
	public void apply(EditarRemedio comando) {
		this.id = comando.getId();
		this.nome = comando.getNome();
		this.dataInicio = comando.getDataInicio();
		this.dataFim = comando.getDataFim();
		this.composicao = comando.getComposicao();
		this.horario = comando.getHorario();
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

	public UsuarioId getIdUsuario() {
		return idUsuario;
	}
}
