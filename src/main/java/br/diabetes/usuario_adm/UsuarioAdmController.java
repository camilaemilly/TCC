package br.diabetes.usuario_adm;

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
@RequestMapping("/usuarioAdm")
public class UsuarioAdmController {
	@Autowired
	private UsuarioAdmService service;

	@ApiOperation("Busca usuários")
	@GetMapping
	public ResponseEntity<List<UsuarioAdm>> get() {
		List<UsuarioAdm> optionalUsuarioAdm = service.encontrarTodos();
		if(!optionalUsuarioAdm.isEmpty()) {
			return ResponseEntity.ok(optionalUsuarioAdm);
		}
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation("Busca usuários por id")
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioAdm> get(@PathVariable UsuarioAdmId id) {
		System.out.println(id);
		Optional<UsuarioAdm> optionalUsuarioAdm = service.encontrar(id);
		if (optionalUsuarioAdm.isPresent()) {
			return ResponseEntity.ok(optionalUsuarioAdm.get());
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation("Cadastra usuários")
	@PostMapping
	public ResponseEntity<UsuarioAdmId> post(@RequestBody UsuarioAdm comando) throws SQLException {
		Optional<UsuarioAdmId> optionalUsuarioAdmId = service.executar(comando);
		if (optionalUsuarioAdmId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalUsuarioAdmId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@ApiOperation("Altera usuários")
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioAdmId> putCirurgia(@RequestBody UsuarioAdm comando) throws SQLException {
		Optional<UsuarioAdmId> optionalUsuarioAdmId = service.alterar(comando);
		if (optionalUsuarioAdmId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalUsuarioAdmId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}

	@ApiOperation("Deleta usuários")
	@DeleteMapping("/{id}")
	public ResponseEntity<UsuarioAdmId> deleteUsuarioAdm(@PathVariable UsuarioAdmId id) throws SQLException {
		Optional<UsuarioAdm> optionalUsuarioAdm = service.encontrar(id);
		if (optionalUsuarioAdm.isPresent()) {
			service.deletar(id);
			return ResponseEntity.accepted().build();
		}
		return ResponseEntity.badRequest().build();
	}
}