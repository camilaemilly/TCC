package br.diabetes.permissao;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Permissao {
	@EmbeddedId
	private PermissaoId id;
	private String nome;
	private String email;
	private String papel;

	public Permissao() {
	}

	public Permissao(Permissao novaPermissao) {
		this.id = new PermissaoId();
		this.nome = novaPermissao.getNome();
		this.email = novaPermissao.getEmail();
		this.papel = novaPermissao.getPapel();
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
