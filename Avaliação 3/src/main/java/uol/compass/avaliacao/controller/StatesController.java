package uol.compass.avaliacao.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import uol.compass.avaliacao.controller.dto.StatesDto;
import uol.compass.avaliacao.controller.form.AtualizacaoStates;
import uol.compass.avaliacao.model.States;
import uol.compass.avaliacao.repository.StatesRepository;

@RestController
@RequestMapping("/states")
public class StatesController {

	@Autowired
	private StatesRepository statesRepository;

	// GET ALL
	@GetMapping
	public ResponseEntity<List<States>> getAll(
			@RequestParam(required = false) String regiao,
			@RequestParam(required = false) Double populacao,
			@RequestParam(required = false) Double area) {
		List<States> states = new ArrayList<>();
		states = statesRepository.findByRegiao(regiao, populacao, area);
		return new ResponseEntity<>(states, HttpStatus.OK);
	}

	// INSERIR
	@PostMapping
	@Transactional
	public ResponseEntity<States> insert(@RequestBody States states) {
		
		try {
			Long diffDatesInYears = ChronoUnit.YEARS.between(states.getDataFundacao(), LocalDate.now());
			
			if (states.getTempoDesdeFundacao() != diffDatesInYears.intValue()) {
				throw new Exception("qualquer coisa");
			}
			
			statesRepository.save(states);
			return new ResponseEntity<>(new States(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new States(), HttpStatus.BAD_REQUEST);
		}
		
	}

	// PROCURAR POR ID
	@GetMapping(path = "/states/{id}")
	public ResponseEntity<StatesDto> getById(@PathVariable Long id) {
		Optional<States> optional = statesRepository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok(new StatesDto(optional.get()));
		}

		return ResponseEntity.notFound().build();
	}

	// ATUALIZAR
	@PutMapping(path = "/states/{id}")
	@Transactional
	public ResponseEntity<StatesDto> update(@PathVariable Long id, @RequestBody AtualizacaoStates form) {
		Optional<States> optional = statesRepository.findById(id);
		if (optional.isPresent()) {
			States state = form.atualizar(id, statesRepository);
			return ResponseEntity.ok(new StatesDto(state));
		}

		return ResponseEntity.notFound().build();
	}

	// DELETAR
	@DeleteMapping(path = "/states/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable long id) {
		Optional<States> optional = statesRepository.findById(id);
		if (optional.isPresent()) {
			statesRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
