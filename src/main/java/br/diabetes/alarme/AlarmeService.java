package br.diabetes.alarme;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.diabetes.alarme.Alarme;
import br.diabetes.alarme.comandos.BuscarAlarme;
import br.diabetes.alarme.AlarmeId;
import br.diabetes.alarme.comandos.CriarAlarme;
import br.diabetes.alarme.comandos.EditarAlarme;
import br.diabetes.usuario.UsuarioId;

@Service
@Transactional
public class AlarmeService {
	@Autowired
	private AlarmeRepository repo;

	public Optional<AlarmeId> salvar(CriarAlarme comando, UsuarioId id) {
		if (comando.getHorario() != null) {
			Alarme novo = repo.save(new Alarme(comando, id));
			return Optional.of(novo.getId());
		}
		return Optional.empty();
	}
	
	public Optional<BuscarAlarme> encontrar(UsuarioId id) {
		Alarme alarme = repo.findAll(id.toString());
		if (alarme != null) {
			return Optional.of(new BuscarAlarme(alarme));
		}
		return Optional.empty();
	}
	
	public Optional<AlarmeId> alterar(EditarAlarme comando, UsuarioId usuarioId) {
		Alarme optional = repo.findById(comando.getId().toString(), usuarioId.toString());
		if (optional != null) {
			Alarme alar = optional;
			alar.apply(comando);
			repo.save(alar);
			return Optional.of(comando.getId());
		}
		return Optional.empty();
	}

	public Optional<String> deletar(AlarmeId id, UsuarioId usuarioId) {
		if (repo.findById(id.toString(), usuarioId.toString()) != null) {
			repo.deleteById(id);
			return Optional.of("Alarme ===> " + id + ": deletado com sucesso");
		}
		return Optional.empty();
	}
}