package br.diabetes.alarme;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AlarmeService {
	@Autowired
	private AlarmeRepository repo;
	
	public Optional<AlarmeId> executar(Alarme nova) {
		repo.save(nova);
		return Optional.of(nova.getId());
	}
	
	public List<Alarme> encontrarTodos() {
		return repo.findAll();
	}
	
	public Optional<Alarme> encontrar(AlarmeId id) {
		return repo.findById(id);
	}
	
	public Optional<AlarmeId> alterar(Alarme nova) {
		repo.save(nova);
		return Optional.of(nova.getId());
	}
}