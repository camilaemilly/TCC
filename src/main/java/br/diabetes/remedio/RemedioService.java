package br.diabetes.remedio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RemedioService {
	@Autowired
	private RemedioRepository repo;
	
	public Optional<RemedioId> executar(Remedio nova) {
		repo.save(nova);
		return Optional.of(nova.getId());
	}
	
	public List<Remedio> encontrarTodos() {
		return repo.findAll();
	}
	
	public Optional<Remedio> encontrar(RemedioId id) {
		return repo.findById(id);
	}
	
	public void deletar(RemedioId id) {
		repo.deleteById(id);
	}
	
	public Optional<RemedioId> alterar(Remedio nova) {
		repo.save(nova);
		return Optional.of(nova.getId());
	}
}