package br.diabetes.login.comandos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogarUsuario {
	private String identificador;
	private String senha;

	public LogarUsuario() {
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
