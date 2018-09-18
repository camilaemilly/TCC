package br.diabetes.permissao;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/permissao")
public class PermissaoController {
	@Autowired
	private PermissaoService service;

	@ApiOperation("Busca Permissões")
	@GetMapping
	public ResponseEntity<List<Permissao>> get() {
		List<Permissao> optionalPermissao = service.encontrarTodos();
		if(!optionalPermissao.isEmpty()) {
			return ResponseEntity.ok(optionalPermissao);
		}
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation("Busca Permissões por id")
	@GetMapping("/{id}")
	public ResponseEntity<Permissao> get(@PathVariable PermissaoId id) {
		System.out.println(id);
		Optional<Permissao> optionalPermissao = service.encontrar(id);
		if (optionalPermissao.isPresent()) {
			return ResponseEntity.ok(optionalPermissao.get());
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation("Cadastra Permissões")
	@PostMapping
	public ResponseEntity<PermissaoId> post(@RequestBody Permissao comando) throws SQLException {
		Optional<PermissaoId> optionalPermissaoId = service.executar(comando);
		if (optionalPermissaoId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalPermissaoId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@ApiOperation("Altera Permissões")
	@PutMapping("/{id}")
	public ResponseEntity<PermissaoId> putCirurgia(@RequestBody Permissao comando) throws SQLException {
		Optional<PermissaoId> optionalPermissaoId = service.alterar(comando);
		if (optionalPermissaoId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalPermissaoId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}

	@ApiOperation("Deleta Permissões")
	@DeleteMapping("/{id}")
	public ResponseEntity<PermissaoId> deletePermissao(@PathVariable PermissaoId id) throws SQLException {
		Optional<Permissao> optionalPermissao = service.encontrar(id);
		if (optionalPermissao.isPresent()) {
			service.deletar(id);
			return ResponseEntity.accepted().build();
		}
		return ResponseEntity.badRequest().build();
	}
}

