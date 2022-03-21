package uol.compass.avaliacao.controllers;

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

import uol.compass.avaliacao.dto.AssociateDTO;
import uol.compass.avaliacao.dto.AssociateFormDTO;
import uol.compass.avaliacao.dto.AssociatePoliticalPartyFormDTO;
import uol.compass.avaliacao.services.AssociateService;

@RestController
@RequestMapping(value = "/associados")
public class AssociateController {

	@Autowired
	private AssociateService associateService;
	
	@GetMapping
	public ResponseEntity<List<AssociateDTO>> findAll(@RequestParam(required = false) String cargo) {
		List<AssociateDTO> adto = associateService.findAll(cargo);
		return ResponseEntity.ok(adto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AssociateDTO> findById(@PathVariable long id) {
		AssociateDTO adto = associateService.findById(id);
		return ResponseEntity.ok(adto);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<AssociateDTO> insert(@RequestBody @Valid AssociateFormDTO form) {
		AssociateDTO save = associateService.insert(form);
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}

	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<AssociateDTO> update(
			@PathVariable Long id, 
			@RequestBody @Valid AssociateFormDTO form) {
		AssociateDTO up = associateService.update(id, form);
		return ResponseEntity.ok(up);
	}

	@PutMapping(value = "/partidos")
	@Transactional
	public ResponseEntity<AssociateDTO> updateAssociateParty(@RequestBody @Valid AssociatePoliticalPartyFormDTO form) {
		AssociateDTO up = associateService.updatePoliticalParty(form);
		return ResponseEntity.ok(up);
	}

	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable long id) {
		associateService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/{idAssociado}/partidos/{idPartido}")
	@Transactional
	public ResponseEntity<Void> deleteAssociateFromParty(
			@PathVariable long idAssociado, 
			@PathVariable long idPartido) {
		associateService.deleteAssociateFromParty(idAssociado, idPartido);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
