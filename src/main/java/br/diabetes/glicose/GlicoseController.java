package br.diabetes.glicose;

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
import br.diabetes.glicose.comandos.BuscarGlicose;
import br.diabetes.glicose.comandos.CriarGlicose;
import br.diabetes.glicose.comandos.EditarGlicose;
import br.diabetes.security.Autentica;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Basic Glicose Controller")
@RestController
@RequestMapping("/glicose")
@CrossOrigin
public class GlicoseController {
	private static final String ACESSONEGADO = "Acesso negado";

	@Autowired
	private GlicoseService serviceGlicose;

	@Autowired
	private Autentica autentica;

	@ApiOperation("Busque a sua glicose")
	@GetMapping
	public ResponseEntity<BuscarGlicose> getGlicose(@RequestHeader String token) throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			Optional<BuscarGlicose> optionalGlicoses = serviceGlicose.encontrar(autentica.idUser(token));
			if (optionalGlicoses.isPresent()) {
				return ResponseEntity.ok(optionalGlicoses.get());
			}
			throw new NullPointerException("Não existe nenhuma glicose cadastrada no banco de dados");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

	@ApiOperation("Cadastre uma nova glicose")
	@PostMapping
	public ResponseEntity<String> postGlicose(@RequestBody CriarGlicose comando, @RequestHeader String token)
			throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			Optional<GlicoseId> optionalGlicoseId = serviceGlicose.salvar(comando, autentica.idUser(token));
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(optionalGlicoseId).toUri();
			return ResponseEntity.created(location).body("A glicose foi cadastrada com sucesso");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

	@ApiOperation("Altere uma glicose")
	@PutMapping
	public ResponseEntity<String> putGlicose(@RequestBody EditarGlicose comando, @RequestHeader String token)
			throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			if (serviceGlicose.alterar(comando, autentica.idUser(token)).isPresent())
				return ResponseEntity.ok().body("A glicose foi alterada com sucesso");
			throw new NullPointerException("A glicose a ser alterada não existe no banco de dados");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

}