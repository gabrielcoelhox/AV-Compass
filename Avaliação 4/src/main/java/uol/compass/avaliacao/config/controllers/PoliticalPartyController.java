package uol.compass.avaliacao.config.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

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

import uol.compass.avaliacao.config.dto.PoliticalPartyDTO;
import uol.compass.avaliacao.config.dto.PoliticalPartyFormDTO;
import uol.compass.avaliacao.config.dto.PoliticalPartyWithAssociatesDTO;
import uol.compass.avaliacao.config.services.PoliticalPartyService;

@RestController
@RequestMapping(value = "/partidos")
public class PoliticalPartyController {

	@Autowired
	private PoliticalPartyService politicalPartyService;

	@GetMapping
	public ResponseEntity<List<PoliticalPartyDTO>> findAll(@RequestParam(required = false) String ideology) {
		if (ideology == null) {
			List<PoliticalPartyDTO> pp = politicalPartyService.findAll();
			return ResponseEntity.ok().body(pp);
		}
		List<PoliticalPartyDTO> pp = politicalPartyService.findByIdeology(ideology);
		return ResponseEntity.ok(pp);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PoliticalPartyDTO> findById(@PathVariable long id) {
		PoliticalPartyDTO pp = politicalPartyService.findById(id);
		return ResponseEntity.ok().body(pp);
	}

	@GetMapping(value = "/{id}/associados")
	public ResponseEntity<PoliticalPartyWithAssociatesDTO> findByIdAssociation(@PathVariable long id) {
		PoliticalPartyWithAssociatesDTO obj = politicalPartyService.findByIdWithAssociates(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<PoliticalPartyDTO> insert(@RequestBody @Valid PoliticalPartyFormDTO form) {
		PoliticalPartyDTO save = politicalPartyService.insert(form);
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}

	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<PoliticalPartyDTO> update(
			@PathVariable long id, 
			@RequestBody @Valid PoliticalPartyFormDTO form) {
		PoliticalPartyDTO up = politicalPartyService.update(id, form);
		return ResponseEntity.ok(up);
	}

	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable long id) {
		politicalPartyService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
