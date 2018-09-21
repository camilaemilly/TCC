package br.diabetes.usuario_adm.comandos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarUsuarioAdm {
	private String nome;
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
