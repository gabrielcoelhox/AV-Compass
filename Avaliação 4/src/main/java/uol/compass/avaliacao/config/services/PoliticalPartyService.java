package uol.compass.avaliacao.config.services;

import java.util.List;

import uol.compass.avaliacao.config.dto.PoliticalPartyDTO;
import uol.compass.avaliacao.config.dto.PoliticalPartyFormDTO;
import uol.compass.avaliacao.config.dto.PoliticalPartyWithAssociatesDTO;

public interface PoliticalPartyService {

public List<PoliticalPartyDTO> findAll();
	
	public List<PoliticalPartyDTO> findByIdeology(String ideology);
	
	public PoliticalPartyDTO findById(Long id);
	
	public PoliticalPartyWithAssociatesDTO findByIdWithAssociates(Long id);
	
	public PoliticalPartyDTO insert(PoliticalPartyFormDTO form);
	
	public PoliticalPartyDTO update(long id, PoliticalPartyFormDTO form);
	
	public void deleteById(long id);
}
