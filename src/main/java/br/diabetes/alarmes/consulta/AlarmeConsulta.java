package br.diabetes.alarmes.consulta;

import java.sql.Time;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import br.diabetes.alarmes.consulta.comandos.CriarAlarmeConsulta;
import br.diabetes.alarmes.consulta.comandos.EditarAlarmeConsulta;
import br.diabetes.usuario.UsuarioId;
import br.diabetes.consulta.ConsultaId;

@Entity
public class AlarmeConsulta {
	@EmbeddedId
	@AttributeOverride(name = "value", column = @Column(name = "id"))
	private AlarmeConsultaId id;
	@AttributeOverride(name = "value", column = @Column(name = "id_usuario"))
	private UsuarioId idUsuario;
	private Time horario;
	private Date dataInicio;
	private Date dataFim;
	private String descricao;
	private boolean status;
	@AttributeOverride(name = "value", column = @Column(name = "id_medicamento"))
	private ConsultaId idConsulta;

	public AlarmeConsulta() {
	}

	public AlarmeConsulta(CriarAlarmeConsulta comando, UsuarioId id) {
		this.id = new AlarmeConsultaId();
		this.setIdUsuario(id);
		this.descricao = comando.getDescricao();
		this.horario = comando.getHorario();
		this.dataInicio = comando.getDataInicio();
		this.dataFim = comando.getDataFim();
		this.status = comando.getStatus();
		this.setIdConsulta(comando.getIdConsulta());
	}
	
	public void apply(EditarAlarmeConsulta comando) {
		this.id = comando.getId();
		this.descricao = comando.getDescricao();
		this.horario = comando.getHorario();
		this.dataInicio = comando.getDataInicio();
		this.dataFim = comando.getDataFim();
		this.status = comando.getStatus();
		this.setIdConsulta(comando.getIdConsulta());
	}
	
	public Time getHorario() {
		return horario;
	}

	public void setHorario(Time horario) {
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

	public ConsultaId getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(ConsultaId idConsulta) {
		this.idConsulta = idConsulta;
	}

	public UsuarioId getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(UsuarioId idUsuario) {
		this.idUsuario = idUsuario;
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
