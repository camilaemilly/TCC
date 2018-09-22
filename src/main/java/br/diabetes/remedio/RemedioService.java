package br.diabetes.remedio;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.diabetes.remedio.Remedio;
import br.diabetes.remedio.comandos.BuscarRemedio;
import br.diabetes.remedio.RemedioId;
import br.diabetes.remedio.comandos.CriarRemedio;
import br.diabetes.remedio.comandos.EditarRemedio;
import br.diabetes.usuario.UsuarioId;

@Service
@Transactional
public class RemedioService {
	@Autowired
	private RemedioRepository repo;

	public Optional<RemedioId> salvar(CriarRemedio comando, UsuarioId id) {
		if (comando.getComposicao() != null) {
			Remedio novo = repo.save(new Remedio(comando, id));
			return Optional.of(novo.getId());
		}
		return Optional.empty();
	}
	
	public Optional<BuscarRemedio> encontrar(UsuarioId id) {
		Remedio remedio = repo.findAll(id.toString());
		if (remedio != null) {
			return Optional.of(new BuscarRemedio(remedio));
		}
		return Optional.empty();
	}
	
	public Optional<RemedioId> alterar(EditarRemedio comando, UsuarioId usuarioId) {
		Remedio optional = repo.findById(comando.getId().toString(), usuarioId.toString());
		if (optional != null) {
			Remedio rer = optional;
			rer.apply(comando);
			repo.save(rer);
			return Optional.of(comando.getId());
		}
		return Optional.empty();
	}

	public Optional<String> deletar(RemedioId id, UsuarioId usuarioId) {
		if (repo.findById(id.toString(), usuarioId.toString()) != null) {
			repo.deleteById(id);
			return Optional.of("Remédio ===> " + id + ": deletado com sucesso");
		}
		return Optional.empty();
	}
}