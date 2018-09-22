package br.diabetes.usuario_adm;
import java.security.NoSuchAlgorithmException;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import br.diabetes.security.Criptografia;
import br.diabetes.usuario_adm.comandos.CriarUsuarioAdm;
import br.diabetes.usuario_adm.comandos.EditarUsuarioAdm;

@Entity
public class UsuarioAdm {
	@EmbeddedId
	@AttributeOverride(name = "value", column = @Column(name = "id"))
	private UsuarioAdmId id;
	@Column(name = "nome_usuario")
	private String nomeUsuario;
	private String senha;

	public UsuarioAdm() {
	}
	
	public UsuarioAdm(CriarUsuarioAdm comando) throws NoSuchAlgorithmException {
		this.id = new UsuarioAdmId();
		this.nomeUsuario = comando.getNome();
		this.senha = Criptografia.criptografa(comando.getSenha());
	}
	
	public void apply(EditarUsuarioAdm comando) throws NoSuchAlgorithmException {
		this.id = comando.getId();
		this.nomeUsuario = comando.getNome();
		this.senha = Criptografia.criptografa(comando.getSenha());
	}

	public UsuarioAdmId getId() {
		return id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
