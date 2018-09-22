package br.diabetes.usuario;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.diabetes.usuario.comandos.BuscarUsuario;
import br.diabetes.usuario.comandos.CriarUsuario;
import br.diabetes.usuario.comandos.EditarUsuario;

@Service
@Transactional
public class UsuarioService {
	@Autowired
	private UsuarioRepository repo;

	public Optional<UsuarioId> salvar(CriarUsuario comando) throws NoSuchAlgorithmException {
		if (comando.getNomeUsuario() != null) {
			Usuario novo = new Usuario(comando);
			repo.save(novo);
			return Optional.of(novo.getId());
		}
		return Optional.empty();
	}

	public Optional<List<BuscarUsuario>> encontrar() {
		List<BuscarUsuario> resultados = new ArrayList<>();
		List<Usuario> usuarios = repo.findAll();
		if (!usuarios.isEmpty()) {
			for (Usuario usuario : usuarios) {
				if (usuario.getAtivo() == 1) {
					BuscarUsuario user = new BuscarUsuario(usuario);
					resultados.add(user);
				}
			}
			return Optional.of(resultados);
		}
		return Optional.empty();
	}

	public Optional<BuscarUsuario> encontrar(UsuarioId id) {
		Optional<Usuario> usuario = repo.findById(id);
		if (usuario.isPresent() && usuario.get().getAtivo() == 1) {
			BuscarUsuario user = new BuscarUsuario(usuario.get());
			return Optional.of(user);
		}
		return Optional.empty();
	}

	public Optional<String> deletar(UsuarioId id) {
		Optional<Usuario> usuario = repo.findById(id);
		if (usuario.isPresent() && usuario.get().getAtivo() == 1) {
			usuario.get().setAtivo(0);
			repo.save(usuario.get());
			return Optional.of("Usuário ===> " + id + ": deletado com sucesso");
		}
		return Optional.empty();
	}

	public Optional<UsuarioId> alterar(EditarUsuario comando) throws NoSuchAlgorithmException {
		Optional<Usuario> optional = repo.findById(comando.getId());
		if (comando.getNomeUsuario() != null && optional.isPresent()) {
			Usuario user = optional.get();
			user.apply(comando);
			repo.save(user);
			return Optional.of(comando.getId());
		}
		return Optional.empty();
	}
}