package br.diabetes.consulta;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.diabetes.consulta.comandos.CriarConsulta;
import br.diabetes.consulta.comandos.EditarConsulta;

@Service
@Transactional
public class ConsultaService {
	@Autowired
	private ConsultaRepository repo;
	
	public Optional<ConsultaId> executar(CriarConsulta comando){
		Consulta novo = repo.save(new Consulta(comando));
		return Optional.of(novo.getId());
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
	
	public Optional<ConsultaId> alterar(EditarConsulta comando) {
		Optional<Consulta> optional = repo.findById(comando.getId());
		Consulta consulta = optional.get();
		consulta.apply(comando);
		repo.save(consulta);
		return Optional.of(comando.getId());
	}
}