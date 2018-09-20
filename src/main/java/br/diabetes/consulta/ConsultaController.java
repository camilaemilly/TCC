package br.diabetes.consulta;

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

import br.diabetes.consulta.comandos.CriarConsulta;
import br.diabetes.consulta.comandos.EditarConsulta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/Consulta")
public class ConsultaController {
	@Autowired
	private ConsultaService service;

	@ApiOperation("Busca consultas")
	@GetMapping
	public ResponseEntity<List<Consulta>> get() {
		List<Consulta> optionalConsulta = service.encontrarTodos();
		if(!optionalConsulta.isEmpty()) {
			return ResponseEntity.ok(optionalConsulta);
		}
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation("Busca consultas por id")
	@GetMapping("/{id}")
	public ResponseEntity<Consulta> get(@PathVariable ConsultaId id) {
		System.out.println(id);
		Optional<Consulta> optionalConsulta = service.encontrar(id);
		if (optionalConsulta.isPresent()) {
			return ResponseEntity.ok(optionalConsulta.get());
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation("Cadastra consultas")
	@PostMapping
	public ResponseEntity<ConsultaId> post(@RequestBody CriarConsulta comando) throws SQLException {
		Optional<ConsultaId> optionalConsultaId = service.executar(comando);
		if (optionalConsultaId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalConsultaId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@ApiOperation("Altera consultas")
	@PutMapping("/{id}")
	public ResponseEntity<ConsultaId> putCirurgia(@RequestBody EditarConsulta comando) throws SQLException {
		Optional<ConsultaId> optionalConsultaId = service.alterar(comando);
		if (optionalConsultaId.isPresent()) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalConsultaId.get()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.badRequest().build();
	}

	@ApiOperation("Deleta consultas")
	@DeleteMapping("/{id}")
	public ResponseEntity<ConsultaId> deleteConsulta(@PathVariable ConsultaId id) throws SQLException {
		Optional<Consulta> optionalConsulta = service.encontrar(id);
		if (optionalConsulta.isPresent()) {
			service.deletar(id);
			return ResponseEntity.accepted().build();
		}
		return ResponseEntity.badRequest().build();
	}
}

