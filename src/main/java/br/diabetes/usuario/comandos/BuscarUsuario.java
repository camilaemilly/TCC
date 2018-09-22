package br.diabetes.usuario.comandos;

import java.util.Date;
import br.diabetes.usuario.Usuario;
import br.diabetes.usuario.UsuarioId;

public class BuscarUsuario {
	private UsuarioId id;
	private String nomeCompleto;
	private String nomeUsuario;
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
	
	public BuscarUsuario(Usuario comandos) {
		this.id = comandos.getId();
		this.nomeCompleto = comandos.getNomeCompleto();
		this.nomeUsuario = comandos.getNomeUsuario();
		this.email = comandos.getEmail();
		this.senha = comandos.getSenha();
		this.sexo = comandos.getSexo();
		this.cep = comandos.getCep();
		this.telefone = comandos.getTelefone();
		this.cidade = comandos.getCidade();
		this.estado = comandos.getEstado();
		this.nascimento = comandos.getNascimento();
		this.peso = comandos.getPeso();
		this.altura = comandos.getAltura();
	}
	
	public UsuarioId getId() {
		return id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
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
}