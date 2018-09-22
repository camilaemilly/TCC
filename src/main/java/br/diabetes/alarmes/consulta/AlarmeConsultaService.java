package br.diabetes.alarmes.consulta;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.diabetes.alarmes.consulta.comandos.BuscarAlarmeConsulta;
import br.diabetes.alarmes.consulta.comandos.CriarAlarmeConsulta;
import br.diabetes.alarmes.consulta.comandos.EditarAlarmeConsulta;
import br.diabetes.consulta.Consulta;
import br.diabetes.consulta.ConsultaRepository;
import br.diabetes.consulta.comandos.BuscarConsulta;
import br.diabetes.alarmes.consulta.AlarmeConsulta;
import br.diabetes.alarmes.consulta.AlarmeConsultaId;
import br.diabetes.usuario.UsuarioId;

@Service
@Transactional
public class AlarmeConsultaService {
	@Autowired
	private AlarmeConsultaRepository repo;
	
	@Autowired
	private ConsultaRepository consultaRepo;

	public Optional<AlarmeConsultaId> salvar(CriarAlarmeConsulta comando, UsuarioId id) {
		if (comando.getHorario() != null) {
			AlarmeConsulta novo = repo.save(new AlarmeConsulta(comando, id));
			return Optional.of(novo.getId());
		}
		return Optional.empty();
	}
	
	public Optional<BuscarAlarmeConsulta> encontrar(UsuarioId usuarioId) {
		AlarmeConsulta result = repo.findAll(usuarioId.toString());
		if (result != null) {
			BuscarAlarmeConsulta resultado = new BuscarAlarmeConsulta(result);
			Optional<Consulta> consulta = consultaRepo.findById(result.getIdConsulta());
			if (consulta.isPresent())
				resultado.setConsulta(new BuscarConsulta(consulta.get()));

			return Optional.of(resultado);
		}
		return Optional.empty();
	}
	
	public Optional<AlarmeConsultaId> alterar(EditarAlarmeConsulta comando, UsuarioId usuarioId) {
		AlarmeConsulta optional = repo.findById(comando.getId().toString(), usuarioId.toString());
		if (optional != null) {
			AlarmeConsulta alar = optional;
			alar.apply(comando);
			repo.save(alar);
			return Optional.of(comando.getId());
		}
		return Optional.empty();
	}

	public Optional<String> deletar(AlarmeConsultaId id, UsuarioId usuarioId) {
		if (repo.findById(id.toString(), usuarioId.toString()) != null) {
			repo.deleteById(id);
			return Optional.of("AlarmeConsulta ===> " + id + ": deletado com sucesso");
		}
		return Optional.empty();
	}
}