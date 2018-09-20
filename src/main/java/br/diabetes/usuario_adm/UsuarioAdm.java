package br.diabetes.usuario_adm;
import java.security.NoSuchAlgorithmException;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import br.diabetes.security.Criptografia;

@Entity
public class UsuarioAdm {
	@EmbeddedId
	private UsuarioAdmId id;
	private String nome;
	private String email;
	private String senha;

	public UsuarioAdm() {
	}

	public UsuarioAdm(UsuarioAdm novoUsuario) throws NoSuchAlgorithmException {
		this.id = new UsuarioAdmId();
		this.nome = novoUsuario.getNome();
		this.email = novoUsuario.getEmail();
		this.senha = Criptografia.criptografa(novoUsuario.getSenha());
	}

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
