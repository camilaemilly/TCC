package br.diabetes.usuario_adm.comandos;

import br.diabetes.usuario_adm.UsuarioAdmId;

public class EditarUsuarioAdm {
	private UsuarioAdmId id;
	private String nome;
	private String email;
	private String senha;
	
	public UsuarioAdmId getId() {
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
