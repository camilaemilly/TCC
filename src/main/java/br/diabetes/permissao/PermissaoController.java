package br.diabetes.permissao;

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
import br.diabetes.permissao.comandos.BuscarPermissao;
import br.diabetes.permissao.comandos.CriarPermissao;
import br.diabetes.permissao.comandos.EditarPermissao;
import br.diabetes.security.Autentica;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Basic Permissao Controller")
@RestController
@RequestMapping("/permissao")
@CrossOrigin
public class PermissaoController {
	private static final String ACESSONEGADO = "Acesso negado";

	@Autowired
	private PermissaoService servicePermissao;

	@Autowired
	private Autentica autentica;

	@ApiOperation("Busque a sua permissao")
	@GetMapping
	public ResponseEntity<BuscarPermissao> getPermissao(@RequestHeader String token) throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			Optional<BuscarPermissao> optionalPermissoes = servicePermissao.encontrar(autentica.idUser(token));
			if (optionalPermissoes.isPresent()) {
				return ResponseEntity.ok(optionalPermissoes.get());
			}
			throw new NullPointerException("Não existe nenhuma permissão cadastrada no banco de dados");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

	@ApiOperation("Cadastre uma nova Permissao")
	@PostMapping
	public ResponseEntity<String> postPermissao(@RequestBody CriarPermissao comando, @RequestHeader String token)
			throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			Optional<PermissaoId> optionalPermissaoId = servicePermissao.salvar(comando, autentica.idUser(token));
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(optionalPermissaoId).toUri();
			return ResponseEntity.created(location).body("A permissão foi cadastrada com sucesso");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

	@ApiOperation("Altere uma permissao")
	@PutMapping
	public ResponseEntity<String> putPermissao(@RequestBody EditarPermissao comando, @RequestHeader String token)
			throws AccessDeniedException {
		if (autentica.autenticaRequisicao(token)) {
			if (servicePermissao.alterar(comando, autentica.idUser(token)).isPresent())
				return ResponseEntity.ok().body("A permissão foi alterada com sucesso");
			throw new NullPointerException("A permissão a ser alterada não existe no banco de dados");
		}
		throw new AccessDeniedException(ACESSONEGADO);
	}

}