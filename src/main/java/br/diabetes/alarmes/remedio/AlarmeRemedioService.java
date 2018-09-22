package br.diabetes.alarmes.remedio;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.diabetes.alarmes.remedio.comandos.BuscarAlarmeRemedio;
import br.diabetes.alarmes.remedio.comandos.CriarAlarmeRemedio;
import br.diabetes.alarmes.remedio.comandos.EditarAlarmeRemedio;
import br.diabetes.remedio.Remedio;
import br.diabetes.remedio.RemedioRepository;
import br.diabetes.remedio.comandos.BuscarRemedio;
import br.diabetes.usuario.UsuarioId;

@Service
@Transactional
public class AlarmeRemedioService {
	@Autowired
	private AlarmeRemedioRepository repo;

	@Autowired
	private RemedioRepository remedioRepo;
	
	public Optional<AlarmeRemedioId> salvar(CriarAlarmeRemedio comando, UsuarioId id) {
		if (comando.getHorario() != null) {
			AlarmeRemedio novo = repo.save(new AlarmeRemedio(comando, id));
			return Optional.of(novo.getId());
		}
		return Optional.empty();
	}
	
	public Optional<BuscarAlarmeRemedio> encontrar(UsuarioId usuarioId) {
		AlarmeRemedio result = repo.findAll(usuarioId.toString());
		if (result != null) {
			BuscarAlarmeRemedio resultado = new BuscarAlarmeRemedio(result);
			Optional<Remedio> remedio = remedioRepo.findById(result.getIdRemedio());
			if (remedio.isPresent())
				resultado.setRemedio(new BuscarRemedio(remedio.get()));

			return Optional.of(resultado);
		}
		return Optional.empty();
	}
	
	public Optional<AlarmeRemedioId> alterar(EditarAlarmeRemedio comando, UsuarioId usuarioId) {
		AlarmeRemedio optional = repo.findById(comando.getId().toString(), usuarioId.toString());
		if (optional != null) {
			AlarmeRemedio alar = optional;
			alar.apply(comando);
			repo.save(alar);
			return Optional.of(comando.getId());
		}
		return Optional.empty();
	}

	public Optional<String> deletar(AlarmeRemedioId id, UsuarioId usuarioId) {
		if (repo.findById(id.toString(), usuarioId.toString()) != null) {
			repo.deleteById(id);
			return Optional.of("Alarme ===> " + id + ": deletado com sucesso");
		}
		return Optional.empty();
	}
}