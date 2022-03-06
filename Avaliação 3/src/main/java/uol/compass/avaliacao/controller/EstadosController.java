package uol.compass.avaliacao.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import uol.compass.avaliacao.controller.dto.EstadoDto;
import uol.compass.avaliacao.controller.form.AtualizacaoEstadoForm;
import uol.compass.avaliacao.controller.form.EstadoForm;
import uol.compass.avaliacao.model.Estado;
import uol.compass.avaliacao.model.Regioes;
import uol.compass.avaliacao.repository.EstadoRepository;

@RestController
@RequestMapping("/api/states")
public class EstadosController {

	@Autowired
	private EstadoRepository estadoRespository;

	@GetMapping
	public Page<EstadoDto> listar(@RequestParam(required = false) String regiao,
			@PageableDefault(sort = "id", direction = Direction.ASC) Pageable paginacao) {

		if (regiao == null) {
			Page<Estado> estados = estadoRespository.findAll(paginacao);
			return EstadoDto.converter(estados);
		} else {
			Page<Estado> estados = estadoRespository.findByRegiao(Regioes.valueOf(regiao), paginacao);
			return EstadoDto.converter(estados);
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<EstadoDto> cadastrar(@RequestBody @Valid EstadoForm estadoForm,
			UriComponentsBuilder uriBuilder) {
		Estado estado = estadoForm.converter();
		estadoRespository.save(estado);

		URI uri = uriBuilder.path("/api/states/{id}").buildAndExpand(estado.getId()).toUri();
		return ResponseEntity.created(uri).body(new EstadoDto(estado));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EstadoDto> detalhar(@PathVariable Long id) {
		Optional<Estado> optional = estadoRespository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok(new EstadoDto(optional.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EstadoDto> altualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoEstadoForm form) {
		Optional<Estado> optional = estadoRespository.findById(id);
		if (optional.isPresent()) {
			Estado estado = form.atualizar(id, estadoRespository);
			return ResponseEntity.ok(new EstadoDto(estado));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable long id) {
		Optional<Estado> optional = estadoRespository.findById(id);
		if (optional.isPresent()) {
			estadoRespository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
