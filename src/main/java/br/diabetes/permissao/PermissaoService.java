package br.diabetes.permissao;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.diabetes.permissao.Permissao;
import br.diabetes.permissao.comandos.BuscarPermissao;
import br.diabetes.permissao.PermissaoId;
import br.diabetes.permissao.comandos.CriarPermissao;
import br.diabetes.permissao.comandos.EditarPermissao;
import br.diabetes.usuario.UsuarioId;

@Service
@Transactional
public class PermissaoService {
	@Autowired
	private PermissaoRepository repo;

	public Optional<PermissaoId> salvar(CriarPermissao comando, UsuarioId id) {
		if (comando.getEmail() != null) {
			Permissao nova = repo.save(new Permissao(comando, id));
			return Optional.of(nova.getId());
		}
		return Optional.empty();
	}
	
	public Optional<BuscarPermissao> encontrar(UsuarioId id) {
		Permissao permissao = repo.findAll(id.toString());
		if (permissao != null) {
			return Optional.of(new BuscarPermissao(permissao));
		}
		return Optional.empty();
	}
	
	public Optional<PermissaoId> alterar(EditarPermissao comando, UsuarioId usuarioId) {
		Permissao optional = repo.findById(comando.getId().toString(), usuarioId.toString());
		if (optional != null) {
			Permissao per = optional;
			per.apply(comando);
			repo.save(per);
			return Optional.of(comando.getId());
		}
		return Optional.empty();
	}

	public Optional<String> deletar(PermissaoId id, UsuarioId usuarioId) {
		if (repo.findById(id.toString(), usuarioId.toString()) != null) {
			repo.deleteById(id);
			return Optional.of("Permissão ===> " + id + ": deletada com sucesso");
		}
		return Optional.empty();
	}
}