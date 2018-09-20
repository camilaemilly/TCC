package br.diabetes.alarme;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.diabetes.alarme.comandos.CriarAlarme;
import br.diabetes.alarme.comandos.EditarAlarme;

@Service
@Transactional
public class AlarmeService {
	@Autowired
	private AlarmeRepository repo;
	
	public Optional<AlarmeId> executar(CriarAlarme comando) {
		Alarme novo = repo.save(new Alarme(comando));
		return Optional.of(novo.getId());
	}
	
	public List<Alarme> encontrarTodos() {
		return repo.findAll();
	}
	
	public Optional<Alarme> encontrar(AlarmeId id) {
		return repo.findById(id);
	}
	
	public Optional<AlarmeId> alterar(EditarAlarme comando) {
		Optional<Alarme> optional = repo.findById(comando.getId());
		Alarme alarme = optional.get();
		alarme.apply(comando);
		repo.save(alarme);
		return Optional.of(comando.getId());
	}
}