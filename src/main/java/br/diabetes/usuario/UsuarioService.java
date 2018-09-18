package br.diabetes.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {
	@Autowired
	private UsuarioRepository repo;
	
	public Optional<UsuarioId> executar(Usuario nova) {
		repo.save(nova);
		return Optional.of(nova.getId());
	}
	
	public List<Usuario> encontrarTodos() {
		return repo.findAll();
	}
	
	public Optional<Usuario> encontrar(UsuarioId id) {
		return repo.findById(id);
	}
	
	public void deletar(UsuarioId id) {
		repo.deleteById(id);
	}
	
	public Optional<UsuarioId> alterar(Usuario nova) {
		repo.save(nova);
		return Optional.of(nova.getId());
	}
}