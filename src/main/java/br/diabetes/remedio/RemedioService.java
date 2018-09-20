package br.diabetes.remedio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.diabetes.remedio.comandos.CriarRemedio;
import br.diabetes.remedio.comandos.EditarRemedio;

@Service
@Transactional
public class RemedioService {
	@Autowired
	private RemedioRepository repo;
	
	public Optional<RemedioId> executar(CriarRemedio comando){
		Remedio novo = repo.save(new Remedio(comando));
		return Optional.of(novo.getId());
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
	
	public Optional<RemedioId> alterar(EditarRemedio comando) {
		Optional<Remedio> optional = repo.findById(comando.getId());
		Remedio Remedio = optional.get();
		Remedio.apply(comando);
		repo.save(Remedio);
		return Optional.of(comando.getId());
	}
}