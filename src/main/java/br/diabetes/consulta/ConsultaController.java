package br.diabetes.consulta;

import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.diabetes.consulta.comandos.BuscarConsulta;
import br.diabetes.consulta.comandos.CriarConsulta;
import br.diabetes.consulta.comandos.EditarConsulta;
import br.diabetes.security.Autentica;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Basic Consulta Controller")
@RestController
@RequestMapping("/consulta")
@CrossOrigin
public class ConsultaController {
	private static final String ACESSONEGADO = "Acesso negado";

	@Autowired
	private ConsultaService serviceConsulta;

	@Autowired
	private Autentica autentica;

	@ApiOperation("Busque a sua consulta")
	@GetMapping
	public ResponseEntity<BuscarConsulta> getConsulta(@RequestHeader String token) throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			Optional<BuscarConsulta> optionalPermissoes = serviceConsulta.encontrar(autentica.idUser(token));
			if (optionalPermissoes.isPresent()) {
				return ResponseEntity.ok(optionalPermissoes.get());
			}
			throw new NullPointerException("Não existe nenhuma consulta cadastrada no banco de dados");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

	@ApiOperation("Cadastre uma nova Consulta")
	@PostMapping
	public ResponseEntity<String> postConsulta(@RequestBody CriarConsulta comando, @RequestHeader String token)
			throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			Optional<ConsultaId> optionalConsultaId = serviceConsulta.salvar(comando, autentica.idUser(token));
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(optionalConsultaId).toUri();
			return ResponseEntity.created(location).body("A consulta foi cadastrada com sucesso");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

	@ApiOperation("Altere uma Consulta")
	@PutMapping
	public ResponseEntity<String> putConsulta(@RequestBody EditarConsulta comando, @RequestHeader String token)
			throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			if (serviceConsulta.alterar(comando, autentica.idUser(token)).isPresent())
				return ResponseEntity.ok().body("A consulta foi alterada com sucesso");
			throw new NullPointerException("A consulta a ser alterada não existe no banco de dados");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

}