package br.diabetes.permissao.comandos;

import br.diabetes.permissao.PermissaoId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarPermissao {
	private PermissaoId id;
	private String nome;
	private String email;
	private String papel;
	
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
