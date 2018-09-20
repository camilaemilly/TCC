package br.diabetes.alarme;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.diabetes.alarme.comandos.CriarAlarme;
import br.diabetes.alarme.comandos.EditarAlarme;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/alarme")
public class AlarmeController {
	@Autowired
	private AlarmeService service;

	@ApiOperation("Busca alarmes")
	@GetMapping
	public ResponseEntity<List<Alarme>> get() {
		List<Alarme> optionalAlarme = service.encontrarTodos();
		if(!optionalAlarme.isEmpty()) {
			return ResponseEntity.ok(optionalAlarme);
		}
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation("Busca alarmes por id")
	@GetMapping("/{id}")
	public ResponseEntity<Alarme> get(@PathVariable AlarmeId id) {
		Optional<Alarme> optionalAlarme = service.encontrar(id);
		if (optionalAlarme.isPresent()) {
			return ResponseEntity.ok(optionalAlarme.get());
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation("Cadastra alarmes")
	@PostMapping
	public ResponseEntity<AlarmeId> post(@RequestBody CriarAlarme comando) throws SQLException {
		Optional<AlarmeId> optionalAlarmeId = service.executar(comando);
		if (optionalAlarmeId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalAlarmeId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@ApiOperation("Altera alarmes")
	@PutMapping("/{id}")
	public ResponseEntity<AlarmeId> putCirurgia(@RequestBody EditarAlarme comando) throws SQLException {
		Optional<AlarmeId> optionalAlarmeId = service.alterar(comando);
		if (optionalAlarmeId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalAlarmeId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
}