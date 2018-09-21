package br.diabetes.usuario_adm.comandos;

import br.diabetes.usuario_adm.UsuarioAdmId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarUsuarioAdm {
	private UsuarioAdmId id;
	private String nome;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
