package br.diabetes.permissao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.diabetes.permissao.comandos.CriarPermissao;
import br.diabetes.permissao.comandos.EditarPermissao;

@Service
@Transactional
public class PermissaoService {
	@Autowired
	private PermissaoRepository repo;
	
	public Optional<PermissaoId> executar(CriarPermissao comando){
		Permissao novo = repo.save(new Permissao(comando));
		return Optional.of(novo.getId());
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
	
	public Optional<PermissaoId> alterar(EditarPermissao comando) {
		Optional<Permissao> optional = repo.findById(comando.getId());
		Permissao Permissao = optional.get();
		Permissao.apply(comando);
		repo.save(Permissao);
		return Optional.of(comando.getId());
	}
}