package br.diabetes.permissao;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import br.diabetes.permissao.comandos.CriarPermissao;
import br.diabetes.permissao.comandos.EditarPermissao;
import br.diabetes.usuario.UsuarioId;

@Entity
public class Permissao {
	@EmbeddedId
	@AttributeOverride(name = "value", column = @Column(name = "id"))
	private PermissaoId id;
	@AttributeOverride(name = "value", column = @Column(name = "id_usuario"))
	private UsuarioId idUsuario;
	private String nome;
	private String email;
	private String papel;

	public Permissao() {
	}

	public Permissao(CriarPermissao comando, UsuarioId id) {
		this.id = new PermissaoId();
		this.idUsuario = id;
		this.nome = comando.getNome();
		this.email = comando.getEmail();
		this.papel = comando.getPapel();
	}
	
	public void apply(EditarPermissao comando) {
		this.id = comando.getId();
		this.nome = comando.getNome();
		this.email = comando.getEmail();
		this.papel = comando.getPapel();
	}
	
	public PermissaoId getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public UsuarioId getIdUsuario() {
		return idUsuario;
	}
}
