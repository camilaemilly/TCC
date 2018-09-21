package br.diabetes.glicose;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.diabetes.glicose.Glicose;
import br.diabetes.glicose.comandos.BuscarGlicose;
import br.diabetes.glicose.GlicoseId;
import br.diabetes.glicose.comandos.CriarGlicose;
import br.diabetes.glicose.comandos.EditarGlicose;
import br.diabetes.usuario.UsuarioId;

@Service
@Transactional
public class GlicoseService {
	@Autowired
	private GlicoseRepository repo;

	public Optional<GlicoseId> salvar(CriarGlicose comando, UsuarioId id) {
		if (comando.getDataUltimaMedicao() != null) {
			Glicose nova = repo.save(new Glicose(comando, id));
			return Optional.of(nova.getId());
		}
		return Optional.empty();
	}
	
	public Optional<BuscarGlicose> encontrar(UsuarioId id) {
		Glicose glicose = repo.findAll(id.toString());
		if (glicose != null) {
			return Optional.of(new BuscarGlicose(glicose));
		}
		return Optional.empty();
	}
	
	public Optional<GlicoseId> alterar(EditarGlicose comando, UsuarioId usuarioId) {
		Glicose optional = repo.findById(comando.getId().toString(), usuarioId.toString());
		if (optional != null) {
			Glicose gli = optional;
			gli.apply(comando);
			repo.save(gli);
			return Optional.of(comando.getId());
		}
		return Optional.empty();
	}

	public Optional<String> deletar(GlicoseId id, UsuarioId usuarioId) {
		if (repo.findById(id.toString(), usuarioId.toString()) != null) {
			repo.deleteById(id);
			return Optional.of("Glicose ===> " + id + ": deletada com sucesso");
		}
		return Optional.empty();
	}
}