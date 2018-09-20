package br.diabetes.usuario_adm;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioAdmService {
	@Autowired
	private UsuarioAdmRepository repo;
	
	public Optional<UsuarioAdmId> executar(UsuarioAdm novo) {
		repo.save(novo);
		return Optional.of(novo.getId());
	}
	
	public List<UsuarioAdm> encontrarTodos() {
		return repo.findAll();
	}
	
	public Optional<UsuarioAdm> encontrar(UsuarioAdmId id) {
		return repo.findById(id);
	}
	
	public void deletar(UsuarioAdmId id) {
		repo.deleteById(id);
	}
	
	public Optional<UsuarioAdmId> alterar(UsuarioAdm novo) {
		repo.save(novo);
		return Optional.of(novo.getId());
	}
}