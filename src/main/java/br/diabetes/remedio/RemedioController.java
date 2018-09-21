package br.diabetes.remedio;

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
import br.diabetes.remedio.comandos.BuscarRemedio;
import br.diabetes.remedio.comandos.CriarRemedio;
import br.diabetes.remedio.comandos.EditarRemedio;
import br.diabetes.security.Autentica;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Basic Remedio Controller")
@RestController
@RequestMapping("/remedio")
@CrossOrigin
public class RemedioController {
	private static final String ACESSONEGADO = "Acesso negado";

	@Autowired
	private RemedioService serviceRemedio;

	@Autowired
	private Autentica autentica;

	@ApiOperation("Busqueo seus remedios")
	@GetMapping
	public ResponseEntity<BuscarRemedio> getRemedio(@RequestHeader String token) throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			Optional<BuscarRemedio> optionalRemedios = serviceRemedio.encontrar(autentica.idUser(token));
			if (optionalRemedios.isPresent()) {
				return ResponseEntity.ok(optionalRemedios.get());
			}
			throw new NullPointerException("Não existe nenhum remédio cadastrado no banco de dados");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

	@ApiOperation("Cadastre um novo remedio")
	@PostMapping
	public ResponseEntity<String> postRemedio(@RequestBody CriarRemedio comando, @RequestHeader String token)
			throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			Optional<RemedioId> optionalRemedioId = serviceRemedio.salvar(comando, autentica.idUser(token));
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(optionalRemedioId).toUri();
			return ResponseEntity.created(location).body("O remédio foi cadastrado com sucesso");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

	@ApiOperation("Altere um remedio")
	@PutMapping
	public ResponseEntity<String> putRemedio(@RequestBody EditarRemedio comando, @RequestHeader String token)
			throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			if (serviceRemedio.alterar(comando, autentica.idUser(token)).isPresent())
				return ResponseEntity.ok().body("O remédio foi alterado com sucesso");
			throw new NullPointerException("O remédio a ser alterado não existe no banco de dados");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}
}
