package br.diabetes.alarmes.remedio;

import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.diabetes.alarmes.remedio.comandos.BuscarAlarmeRemedio;
import br.diabetes.alarmes.remedio.comandos.CriarAlarmeRemedio;
import br.diabetes.alarmes.remedio.comandos.EditarAlarmeRemedio;
import br.diabetes.security.Autentica;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Basic Alarme Controller")
@RestController
@RequestMapping("/alarme")
@CrossOrigin
public class AlarmeRemedioController {
	private static final String ACESSONEGADO = "Acesso negado";

	@Autowired
	private AlarmeRemedioService serviceAlarme;

	@Autowired
	private Autentica autentica;

	@ApiOperation("Busque seus Alarmes")
	@GetMapping
	public ResponseEntity<BuscarAlarmeRemedio> getAlarme(@RequestHeader String token) throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			Optional<BuscarAlarmeRemedio> optionalAlarmes = serviceAlarme.encontrar(autentica.idUser(token));
			if (optionalAlarmes.isPresent()) {
				return ResponseEntity.ok(optionalAlarmes.get());
			}
			throw new NullPointerException("Não existe nenhum alarme cadastrado no banco de dados");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

	@ApiOperation("Cadastre um novo alarme")
	@PostMapping
	public ResponseEntity<String> postAlarme(@RequestBody CriarAlarmeRemedio comando, @RequestHeader String token)
			throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			Optional<AlarmeRemedioId> optionalAlarmeId = serviceAlarme.salvar(comando, autentica.idUser(token));
			if (optionalAlarmeId.isPresent()) {
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(optionalAlarmeId.get()).toUri();
				return ResponseEntity.created(location).body("O alarme foi cadastrado com sucesso");
			}
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

	@ApiOperation("Altere um alarme")
	@PutMapping
	public ResponseEntity<String> putAlarme(@RequestBody EditarAlarmeRemedio comando, @RequestHeader String token)
			throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			if (serviceAlarme.alterar(comando, autentica.idUser(token)).isPresent())
				return ResponseEntity.ok().body("O alarme foi alterado com sucesso");
			throw new NullPointerException("O alarme a ser alterado não existe no banco de dados");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}
	
	@ApiOperation("Delete um alarme pelo ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAlarme(@PathVariable AlarmeRemedioId id, @RequestHeader String token)
			throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			Optional<String> resultado = serviceAlarme.deletar(id, autentica.idUser(token));
			if (resultado.isPresent())
				return ResponseEntity.ok(resultado.get());
			throw new NullPointerException("O alarme a ser deletado não existe no banco de dados");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

}