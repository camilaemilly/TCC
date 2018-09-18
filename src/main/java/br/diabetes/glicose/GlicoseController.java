package br.diabetes.glicose;

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
@RequestMapping("/glicose")
public class GlicoseController {
	@Autowired
	private GlicoseService service;

	@ApiOperation("Busca hemoglobinas glicadas")
	@GetMapping
	public ResponseEntity<List<Glicose>> get() {
		List<Glicose> optionalGlicose = service.encontrarTodos();
		if(!optionalGlicose.isEmpty()) {
			return ResponseEntity.ok(optionalGlicose);
		}
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation("Busca hemoglobinas glicadas por id")
	@GetMapping("/{id}")
	public ResponseEntity<Glicose> get(@PathVariable GlicoseId id) {
		System.out.println(id);
		Optional<Glicose> optionalGlicose = service.encontrar(id);
		if (optionalGlicose.isPresent()) {
			return ResponseEntity.ok(optionalGlicose.get());
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation("Cadastra hemoglobinas glicadas")
	@PostMapping
	public ResponseEntity<GlicoseId> post(@RequestBody Glicose comando) throws SQLException {
		Optional<GlicoseId> optionalGlicoseId = service.executar(comando);
		if (optionalGlicoseId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalGlicoseId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@ApiOperation("Altera hemoglobinas glicadas")
	@PutMapping("/{id}")
	public ResponseEntity<GlicoseId> putCirurgia(@RequestBody Glicose comando) throws SQLException {
		Optional<GlicoseId> optionalGlicoseId = service.alterar(comando);
		if (optionalGlicoseId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalGlicoseId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}

	@ApiOperation("Deleta hemoglobinas glicadas")
	@DeleteMapping("/{id}")
	public ResponseEntity<GlicoseId> deleteGlicose(@PathVariable GlicoseId id) throws SQLException {
		Optional<Glicose> optionalGlicose = service.encontrar(id);
		if (optionalGlicose.isPresent()) {
			service.deletar(id);
			return ResponseEntity.accepted().build();
		}
		return ResponseEntity.badRequest().build();
	}
}

