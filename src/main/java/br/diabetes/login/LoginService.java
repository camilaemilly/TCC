package br.diabetes.login;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.diabetes.login.comandos.LogarUsuario;
import br.diabetes.security.Criptografia;

@Service
@Transactional
public class LoginService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String sqlAdm = "select nome, senha from usuario_adm " + "where nome = ? and senha = ?";

	private String sqlNomeUsuario = "select nome, senha, ativo from usuario "
			+ "where nome = ? and senha = ? and ativo = 1";

	private String sqlEmail = "select email, senha, ativo from usuario "
			+ "where email = ? and senha = ? and ativo = 1";

	private static final String COLUNASENHA = "senha";
	private static final String COLUNANOMEUSUARIO = "nome";
	private static final String COLUNAEMAIL = "email";

	public boolean consultarUsuario(LogarUsuario comando) throws NoSuchAlgorithmException {
		return logarUser(comando, sqlNomeUsuario);
	}

	public boolean consultarEmail(LogarUsuario comando) throws NoSuchAlgorithmException {
		String senha = Criptografia.criptografa(comando.getSenha());
		String email = comando.getIdentificador();
		LogarUsuario usuario = new LogarUsuario();
		List<LogarUsuario> user = jdbcTemplate.query(sqlEmail, new Object[] { email, senha }, (rs, rowNum) -> {
			String senhaUsuario = rs.getString(COLUNASENHA);
			String emailUsuario = rs.getString(COLUNAEMAIL);
			if (senhaUsuario.equals(senha) && emailUsuario.equals(email)) {
				usuario.setIdentificador(rs.getString(COLUNAEMAIL));
				usuario.setSenha(rs.getString(COLUNASENHA));
			}
			return usuario;
		});
		return user.size() == 1;
	}

	public boolean consultarAdm(LogarUsuario comando) throws NoSuchAlgorithmException {
		return logarUser(comando, sqlAdm);
	}

	private boolean logarUser(LogarUsuario comando, String sql) throws NoSuchAlgorithmException {
		String senha = Criptografia.criptografa(comando.getSenha());
		String username = comando.getIdentificador();
		LogarUsuario usuario = new LogarUsuario();
		List<LogarUsuario> user = jdbcTemplate.query(sql, new Object[] { username, senha }, (rs, rowNum) -> {
			String senhaUsuario = rs.getString(COLUNASENHA);
			String nomeUsuario = rs.getString(COLUNANOMEUSUARIO);
			if (senhaUsuario.equals(senha) && nomeUsuario.equals(username)) {
				usuario.setIdentificador(rs.getString(COLUNANOMEUSUARIO));
				usuario.setSenha(rs.getString(COLUNASENHA));
			}
			return usuario;
		});
		return user.size() == 1;
	}

}
