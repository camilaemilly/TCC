package br.diabetes.consulta;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConsultaService {
	@Autowired
	private ConsultaRepository repo;
	
	public Optional<ConsultaId> executar(Consulta nova) {
		repo.save(nova);
		return Optional.of(nova.getId());
	}
	
	public List<Consulta> encontrarTodos() {
		return repo.findAll();
	}
	
	public Optional<Consulta> encontrar(ConsultaId id) {
		return repo.findById(id);
	}
	
	public void deletar(ConsultaId id) {
		repo.deleteById(id);
	}
	
	public Optional<ConsultaId> alterar(Consulta nova) {
		repo.save(nova);
		return Optional.of(nova.getId());
	}
}