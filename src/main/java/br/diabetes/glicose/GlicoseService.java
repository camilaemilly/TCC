package br.diabetes.glicose;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.diabetes.glicose.comandos.CriarGlicose;
import br.diabetes.glicose.comandos.EditarGlicose;

@Service
@Transactional
public class GlicoseService {
	@Autowired
	private GlicoseRepository repo;
	
	public Optional<GlicoseId> executar(CriarGlicose comando){
		Glicose novo = repo.save(new Glicose(comando));
		return Optional.of(novo.getId());
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
	
	public Optional<GlicoseId> alterar(EditarGlicose comando) {
		Optional<Glicose> optional = repo.findById(comando.getId());
		Glicose Glicose = optional.get();
		Glicose.apply(comando);
		repo.save(Glicose);
		return Optional.of(comando.getId());
	}
}