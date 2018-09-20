package br.diabetes.usuario_adm;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.diabetes.usuario_adm.UsuarioAdm;
import br.diabetes.usuario_adm.UsuarioAdmId;
import br.diabetes.usuario_adm.UsuarioAdmRepository;
import br.diabetes.usuario_adm.comandos.BuscarUsuarioAdm;
import br.diabetes.usuario_adm.comandos.CriarUsuarioAdm;
import br.diabetes.usuario_adm.comandos.EditarUsuarioAdm;

@Service
@Transactional
public class UsuarioAdmService {
	@Autowired
	private UsuarioAdmRepository repo;

	public Optional<UsuarioAdmId> salvar(CriarUsuarioAdm comando) throws NoSuchAlgorithmException {
		if (comando.getNome() != null) {
			UsuarioAdm novo = repo.save(new UsuarioAdm(comando));
			return Optional.of(novo.getId());
		}
		return Optional.empty();
	}

	public Optional<BuscarUsuarioAdm> encontrar(UsuarioAdmId id) {
		Optional<UsuarioAdm> adm = repo.findById(id);
		if (adm.isPresent()) {
			BuscarUsuarioAdm resultado = new BuscarUsuarioAdm(adm.get());
			return Optional.of(resultado);
		}
		return Optional.empty();
	}

	public Optional<List<BuscarUsuarioAdm>> encontrar() {
		List<UsuarioAdm> adms = repo.findAll();
		List<BuscarUsuarioAdm> resultados = new ArrayList<>();
		if (!adms.isEmpty()) {
			for (UsuarioAdm adm : adms) {
				BuscarUsuarioAdm nova = new BuscarUsuarioAdm(adm);
				resultados.add(nova);
			}
			return Optional.of(resultados);
		}
		return Optional.empty();
	}

	public Optional<String> deletar(UsuarioAdmId id) {
		if (repo.findById(id).isPresent()) {
			repo.deleteById(id);
			return Optional.of("Administrador ===> " + id + ": deletado com sucesso");
		}
		return Optional.empty();
	}

	public Optional<UsuarioAdmId> alterar(EditarUsuarioAdm comando) throws NoSuchAlgorithmException {
		Optional<UsuarioAdm> optional = repo.findById(comando.getId());
		if (comando.getNome() != null && optional.isPresent()) {
			UsuarioAdm user = optional.get();
			user.apply(comando);
			repo.save(user);
			return Optional.of(comando.getId());
		}
		return Optional.empty();
	}
}