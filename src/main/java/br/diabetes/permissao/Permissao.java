package br.diabetes.permissao;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import br.diabetes.permissao.comandos.CriarPermissao;
import br.diabetes.permissao.comandos.EditarPermissao;

@Entity
public class Permissao {
	@EmbeddedId
	private PermissaoId id;
	private String nome;
	private String email;
	private String papel;

	public Permissao() {
	}

	public Permissao(CriarPermissao comando) {
		this.id = new PermissaoId();
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
}
