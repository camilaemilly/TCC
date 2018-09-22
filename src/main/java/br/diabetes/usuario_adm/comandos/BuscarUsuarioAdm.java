package br.diabetes.usuario_adm.comandos;

import br.diabetes.usuario_adm.UsuarioAdm;
import br.diabetes.usuario_adm.UsuarioAdmId;

public class BuscarUsuarioAdm {
	private UsuarioAdmId id;
	private String nome;
	private String senha;

	public BuscarUsuarioAdm(UsuarioAdm comandos) {
		 this.id = comandos.getId();
		 this.nome = comandos.getNomeUsuario();
		 this.senha = comandos.getSenha();
	}
	
	public UsuarioAdmId getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void getNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}