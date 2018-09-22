package br.diabetes.login.comandos;

import br.diabetes.usuario.UsuarioId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdentificarUsuario {
	private UsuarioId id;

	public UsuarioId getId() {
		return id;
	}

	public void setId(UsuarioId id) {
		this.id = id;
	}
}