package br.diabetes.usuario_adm;
import java.security.NoSuchAlgorithmException;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import br.diabetes.security.Criptografia;
import br.diabetes.usuario_adm.comandos.CriarUsuarioAdm;
import br.diabetes.usuario_adm.comandos.EditarUsuarioAdm;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class UsuarioAdm {
	@EmbeddedId
	@AttributeOverride(name = "value", column = @Column(name = "id"))
	@Setter(AccessLevel.NONE)
	private UsuarioAdmId id;
	@Column(name = "nome")
	private String nome;
	private String senha;

	public UsuarioAdm() {
	}
	
	public UsuarioAdm(CriarUsuarioAdm comando) throws NoSuchAlgorithmException {
		this.id = new UsuarioAdmId();
		this.nome = comando.getNome();
		this.senha = Criptografia.criptografa(comando.getSenha());
	}
	
	public void apply(EditarUsuarioAdm comando) throws NoSuchAlgorithmException {
		this.id = comando.getId();
		this.nome = comando.getNome();
		this.senha = Criptografia.criptografa(comando.getSenha());
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
