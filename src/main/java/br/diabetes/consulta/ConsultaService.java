package br.diabetes.consulta;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.diabetes.consulta.Consulta;
import br.diabetes.consulta.comandos.BuscarConsulta;
import br.diabetes.consulta.ConsultaId;
import br.diabetes.consulta.comandos.CriarConsulta;
import br.diabetes.consulta.comandos.EditarConsulta;
import br.diabetes.usuario.UsuarioId;

@Service
@Transactional
public class ConsultaService {
	@Autowired
	private ConsultaRepository repo;

	public Optional<ConsultaId> salvar(CriarConsulta comando, UsuarioId id) {
		if (comando.getData() != null) {
			Consulta nova = repo.save(new Consulta(comando, id));
			return Optional.of(nova.getId());
		}
		return Optional.empty();
	}
	
	public Optional<BuscarConsulta> encontrar(UsuarioId id) {
		Consulta consulta = repo.findAll(id.toString());
		if (consulta != null) {
			return Optional.of(new BuscarConsulta(consulta));
		}
		return Optional.empty();
	}
	
	public Optional<ConsultaId> alterar(EditarConsulta comando, UsuarioId usuarioId) {
		Consulta optional = repo.findById(comando.getId().toString(), usuarioId.toString());
		if (optional != null) {
			Consulta consu = optional;
			consu.apply(comando);
			repo.save(consu);
			return Optional.of(comando.getId());
		}
		return Optional.empty();
	}

	public Optional<String> deletar(ConsultaId id, UsuarioId usuarioId) {
		if (repo.findById(id.toString(), usuarioId.toString()) != null) {
			repo.deleteById(id);
			return Optional.of("consulta ===> " + id + ": deletada com sucesso");
		}
		return Optional.empty();
	}
}