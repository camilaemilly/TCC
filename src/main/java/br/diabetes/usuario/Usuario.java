package br.diabetes.usuario;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import br.diabetes.security.Criptografia;
import br.diabetes.usuario.comandos.CriarUsuario;
import br.diabetes.usuario.comandos.EditarUsuario;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Usuario {
	@EmbeddedId
	@AttributeOverride(name = "value", column = @Column(name = "id"))
	@Setter(AccessLevel.NONE)
	private UsuarioId id;
	@Column(name = "nome_completo")
	private String nomeCompleto;
	@Column(name = "nome")
	private String nome;
	private String email;
	private String senha;
	private String sexo;
	private String cep;
	private String telefone;
	private String cidade;
	private String estado;
	private Date nascimento;
	private float peso;
	private float altura;
	private int ativo;

	public Usuario() {
	}
	
	public Usuario(CriarUsuario comando) throws NoSuchAlgorithmException {
		this.id = new UsuarioId();
		this.nomeCompleto = comando.getNomeCompleto();
		this.nome = comando.getNome();
		this.email = comando.getEmail();
		this.senha = Criptografia.criptografa(comando.getSenha());
		this.sexo = comando.getSexo();
		this.cep = comando.getCep();
		this.telefone = comando.getTelefone();
		this.cidade = comando.getCidade();
		this.estado = comando.getEstado();
		this.nascimento = comando.getNascimento();
		this.peso = comando.getPeso();
		this.altura = comando.getAltura();
		this.ativo = comando.getAtivo();
	}
	
	public void apply(EditarUsuario comando) throws NoSuchAlgorithmException {
		this.id = comando.getId();
		this.nome = comando.getNome();
		this.nomeCompleto = comando.getNomeCompleto();
		this.email = comando.getEmail();
		this.senha = Criptografia.criptografa(comando.getSenha());
		this.sexo = comando.getSexo();
		this.cep = comando.getCep();
		this.telefone = comando.getTelefone();
		this.cidade = comando.getCidade();
		this.estado = comando.getEstado();
		this.nascimento = comando.getNascimento();
		this.peso = comando.getPeso();
		this.altura = comando.getAltura();
		this.ativo = comando.getAtivo();
	}
	
	public UsuarioId getId() {
		return id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
}
