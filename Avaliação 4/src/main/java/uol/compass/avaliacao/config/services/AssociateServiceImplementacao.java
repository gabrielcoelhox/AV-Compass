package uol.compass.avaliacao.config.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uol.compass.avaliacao.config.dto.AssociateDTO;
import uol.compass.avaliacao.config.dto.AssociateFormDTO;
import uol.compass.avaliacao.config.dto.AssociatePoliticalPartyFormDTO;
import uol.compass.avaliacao.config.entities.Associate;
import uol.compass.avaliacao.config.enums.PoliticalOffice;
import uol.compass.avaliacao.config.exceptions.DefaultException;
import uol.compass.avaliacao.config.repositories.AssociateRepository;
import uol.compass.avaliacao.config.repositories.PoliticalPartyRepository;

@Service
public class AssociateServiceImplementacao implements AssociateService {

	@Autowired
	private AssociateRepository associateRepository;

	@Autowired
	private PoliticalPartyRepository partyRepository;

	@Autowired
	private ModelMapper mapper;

	public List<AssociateDTO> findAll(String politicalOffice) {
		if (politicalOffice == null) {
			List<Associate> list = associateRepository.findAll();
			return list.stream().map(associate -> mapper.map(associate, AssociateDTO.class)).collect(Collectors.toList());
		} else {
			List<Associate> list = associateRepository.filterCargo(PoliticalOffice.valueOf(politicalOffice));
			return list.stream().map(associate -> mapper.map(associate, AssociateDTO.class)).collect(Collectors.toList());
		}	
	}

	public AssociateDTO findById(Long id) {
		Associate associate = associateRepository.findById(id)
				.orElseThrow(() -> new DefaultException("Associate with id = " + id + " not found", "NOT_FOUND", 404));
		return mapper.map(associate, AssociateDTO.class);
	}

	public AssociateDTO insert(AssociateFormDTO form) {
		Associate associate = form.convert(partyRepository);
		return mapper.map(associateRepository.save(associate), AssociateDTO.class);
	}

	public AssociateDTO update(Long id, AssociateFormDTO form) {
		Associate associate = form.update(id, associateRepository, partyRepository);
		return mapper.map(associateRepository.save(associate), AssociateDTO.class);
	}

	public AssociateDTO updatePoliticalParty(AssociatePoliticalPartyFormDTO associateParty) {
		Associate associate = associateParty.updateParty(associateRepository, partyRepository);
		associateRepository.save(associate);
		return mapper.map(associate, AssociateDTO.class);
	}

	public void deleteById(long id) {
		Associate associate = associateRepository.findById(id)
				.orElseThrow(() -> new DefaultException("Associate with id = " + id + " not found", "NOT_FOUND", 404));
		associateRepository.delete(associate);
	}

	public void deleteAssociateFromParty(long idAssociate, long idParty) {
		Associate associate = associateRepository.findById(idAssociate).orElseThrow(
				() -> new DefaultException("Associate with id = " + idAssociate + " not found", "NOT_FOUND", 404));
		if (associate.getPoliticalParty().getId() == idParty) {
			associate.setPoliticalParty(null);
			associateRepository.save(associate);
		} else {
			throw new DefaultException("The associate is associated with a different political party than the one entered.", "BAD_REQUEST",
					400);
		}
	}
}
