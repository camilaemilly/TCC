package br.diabetes.remedio;

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

import br.diabetes.remedio.comandos.CriarRemedio;
import br.diabetes.remedio.comandos.EditarRemedio;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/remedio")
public class RemedioController {
	@Autowired
	private RemedioService service;

	@ApiOperation("Busca remédios")
	@GetMapping
	public ResponseEntity<List<Remedio>> get() {
		List<Remedio> optionalRemedio = service.encontrarTodos();
		if(!optionalRemedio.isEmpty()) {
			return ResponseEntity.ok(optionalRemedio);
		}
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation("Busca remédios por id")
	@GetMapping("/{id}")
	public ResponseEntity<Remedio> get(@PathVariable RemedioId id) {
		System.out.println(id);
		Optional<Remedio> optionalRemedio = service.encontrar(id);
		if (optionalRemedio.isPresent()) {
			return ResponseEntity.ok(optionalRemedio.get());
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation("Cadastra remédios")
	@PostMapping
	public ResponseEntity<RemedioId> post(@RequestBody CriarRemedio comando) throws SQLException {
		Optional<RemedioId> optionalRemedioId = service.executar(comando);
		if (optionalRemedioId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalRemedioId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@ApiOperation("Altera remédios")
	@PutMapping("/{id}")
	public ResponseEntity<RemedioId> putCirurgia(@RequestBody EditarRemedio comando) throws SQLException {
		Optional<RemedioId> optionalRemedioId = service.alterar(comando);
		if (optionalRemedioId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalRemedioId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}

	@ApiOperation("Deleta remédios")
	@DeleteMapping("/{id}")
	public ResponseEntity<RemedioId> deleteRemedio(@PathVariable RemedioId id) throws SQLException {
		Optional<Remedio> optionalRemedio = service.encontrar(id);
		if (optionalRemedio.isPresent()) {
			service.deletar(id);
			return ResponseEntity.accepted().build();
		}
		return ResponseEntity.badRequest().build();
	}
}

