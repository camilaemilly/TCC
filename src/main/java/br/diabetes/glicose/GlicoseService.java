package br.diabetes.glicose;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GlicoseService {
	@Autowired
	private GlicoseRepository repo;
	
	public Optional<GlicoseId> executar(Glicose nova) {
		repo.save(nova);
		return Optional.of(nova.getId());
	}
	
	public List<Glicose> encontrarTodos() {
		return repo.findAll();
	}
	
	public Optional<Glicose> encontrar(GlicoseId id) {
		return repo.findById(id);
	}
	
	public void deletar(GlicoseId id) {
		repo.deleteById(id);
	}
	
	public Optional<GlicoseId> alterar(Glicose nova) {
		repo.save(nova);
		return Optional.of(nova.getId());
	}
}