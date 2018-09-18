package br.diabetes.usuario;

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
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService service;

	@ApiOperation("Busca usuários")
	@GetMapping
	public ResponseEntity<List<Usuario>> get() {
		List<Usuario> optionalUsuario = service.encontrarTodos();
		if(!optionalUsuario.isEmpty()) {
			return ResponseEntity.ok(optionalUsuario);
		}
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation("Busca usuários por id")
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> get(@PathVariable UsuarioId id) {
		System.out.println(id);
		Optional<Usuario> optionalUsuario = service.encontrar(id);
		if (optionalUsuario.isPresent()) {
			return ResponseEntity.ok(optionalUsuario.get());
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation("Cadastra usuários")
	@PostMapping
	public ResponseEntity<UsuarioId> post(@RequestBody Usuario comando) throws SQLException {
		Optional<UsuarioId> optionalUsuarioId = service.executar(comando);
		if (optionalUsuarioId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalUsuarioId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@ApiOperation("Altera usuários")
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioId> putCirurgia(@RequestBody Usuario comando) throws SQLException {
		Optional<UsuarioId> optionalUsuarioId = service.alterar(comando);
		if (optionalUsuarioId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalUsuarioId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}

	@ApiOperation("Deleta usuários")
	@DeleteMapping("/{id}")
	public ResponseEntity<UsuarioId> deleteUsuario(@PathVariable UsuarioId id) throws SQLException {
		Optional<Usuario> optionalUsuario = service.encontrar(id);
		if (optionalUsuario.isPresent()) {
			service.deletar(id);
			return ResponseEntity.accepted().build();
		}
		return ResponseEntity.badRequest().build();
	}
}