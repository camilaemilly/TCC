package br.diabetes.alarme;

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
import br.diabetes.alarme.comandos.BuscarAlarme;
import br.diabetes.alarme.comandos.CriarAlarme;
import br.diabetes.alarme.comandos.EditarAlarme;
import br.diabetes.security.Autentica;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Basic Alarme Controller")
@RestController
@RequestMapping("/alarme")
@CrossOrigin
public class AlarmeController {
	private static final String ACESSONEGADO = "Acesso negado";

	@Autowired
	private AlarmeService serviceAlarme;

	@Autowired
	private Autentica autentica;

	@ApiOperation("Busqueo seus Alarmes")
	@GetMapping
	public ResponseEntity<BuscarAlarme> getAlarme(@RequestHeader String token) throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			Optional<BuscarAlarme> optionalAlarmes = serviceAlarme.encontrar(autentica.idUser(token));
			if (optionalAlarmes.isPresent()) {
				return ResponseEntity.ok(optionalAlarmes.get());
			}
			throw new NullPointerException("Não existe nenhum alarme cadastrado no banco de dados");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

	@ApiOperation("Cadastre um novo alarme")
	@PostMapping
	public ResponseEntity<String> postAlarme(@RequestBody CriarAlarme comando, @RequestHeader String token)
			throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			Optional<AlarmeId> optionalAlarmeId = serviceAlarme.salvar(comando, autentica.idUser(token));
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(optionalAlarmeId).toUri();
			return ResponseEntity.created(location).body("O alarme foi cadastrado com sucesso");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

	@ApiOperation("Altere um alarme")
	@PutMapping
	public ResponseEntity<String> putAlarme(@RequestBody EditarAlarme comando, @RequestHeader String token)
			throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			if (serviceAlarme.alterar(comando, autentica.idUser(token)).isPresent())
				return ResponseEntity.ok().body("O alarme foi alterado com sucesso");
			throw new NullPointerException("O alarme a ser alterado não existe no banco de dados");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

}