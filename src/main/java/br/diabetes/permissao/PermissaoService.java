package br.diabetes.permissao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissaoService {
	@Autowired
	private PermissaoRepository repo;
	
	public Optional<PermissaoId> executar(Permissao nova) {
		repo.save(nova);
		return Optional.of(nova.getId());
	}
	
	public List<Permissao> encontrarTodos() {
		return repo.findAll();
	}
	
	public Optional<Permissao> encontrar(PermissaoId id) {
		return repo.findById(id);
	}
	
	public void deletar(PermissaoId id) {
		repo.deleteById(id);
	}
	
	public Optional<PermissaoId> alterar(Permissao nova) {
		repo.save(nova);
		return Optional.of(nova.getId());
	}
}