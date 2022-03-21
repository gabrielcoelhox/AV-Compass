package uol.compass.avaliacao.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uol.compass.avaliacao.dto.PoliticalPartyDTO;
import uol.compass.avaliacao.dto.PoliticalPartyFormDTO;
import uol.compass.avaliacao.dto.PoliticalPartyWithAssociatesDTO;
import uol.compass.avaliacao.entities.PoliticalParty;
import uol.compass.avaliacao.enums.Ideology;
import uol.compass.avaliacao.exceptions.DefaultException;
import uol.compass.avaliacao.repositories.PoliticalPartyRepository;

@Service
public class PoliticalPartyServiceImplementacao implements PoliticalPartyService{

	@Autowired
	private PoliticalPartyRepository partyRepository;

	@Autowired
	private ModelMapper mapper;

	public List<PoliticalPartyDTO> findAll() {
		List<PoliticalParty> list = partyRepository.findAll();
		return list.stream().map(politicalParty -> mapper.map(politicalParty, PoliticalPartyDTO.class))
				.collect(Collectors.toList());
	}

	public List<PoliticalPartyDTO> findByIdeology(String ideology) {
		List<PoliticalParty> list = partyRepository.filterIdeology(Ideology.valueOf(ideology));
		return list.stream().map(politicalParty -> mapper.map(politicalParty, PoliticalPartyDTO.class))
				.collect(Collectors.toList());
	}

	public PoliticalPartyDTO findById(Long id) {
		PoliticalParty politicalParty = partyRepository.findById(id)
				.orElseThrow(() -> new DefaultException("Political Party not found", "NOT_FOUND", 404));
		return mapper.map(politicalParty, PoliticalPartyDTO.class);
	}

	public PoliticalPartyWithAssociatesDTO findByIdWithAssociates(Long id) {
		PoliticalParty politicalParty = partyRepository.findById(id)
				.orElseThrow(() -> new DefaultException("Political Party not found", "NOT_FOUND", 404));
		return mapper.map(politicalParty, PoliticalPartyWithAssociatesDTO.class);
	}

	public PoliticalPartyDTO insert(PoliticalPartyFormDTO form) {
		PoliticalParty politicalParty = form.convert();
		return mapper.map(partyRepository.save(politicalParty), PoliticalPartyDTO.class);
	}

	public PoliticalPartyDTO update(long id, PoliticalPartyFormDTO form) {
		PoliticalParty politicalParty = form.update(id, partyRepository);
		partyRepository.save(politicalParty);
		return mapper.map(politicalParty, PoliticalPartyDTO.class);
	}

	public void deleteById(long id) {
		try {
		PoliticalParty politicalParty = partyRepository.findById(id)
				.orElseThrow(() -> new DefaultException("Political Party not found", "NOT_FOUND", 404));
		partyRepository.delete(politicalParty);
		} catch (Exception ex) {
			throw new DefaultException("Cannot delete political party with associates.", "CONFLICT", 409);
		}
	}
}
