package uol.compass.avaliacao.config.services;

import java.util.List;

import uol.compass.avaliacao.config.dto.AssociateDTO;
import uol.compass.avaliacao.config.dto.AssociateFormDTO;
import uol.compass.avaliacao.config.dto.AssociatePoliticalPartyFormDTO;

public interface AssociateService {

	public List<AssociateDTO> findAll(String politicalOffice);
	
	public AssociateDTO findById(Long id);
	
	public AssociateDTO insert(AssociateFormDTO form);

	public AssociateDTO update(Long id, AssociateFormDTO form);

	public AssociateDTO updatePoliticalParty(AssociatePoliticalPartyFormDTO associateParty);

	public void deleteById(long id);

	public void deleteAssociateFromParty(long idAssociate, long idParty);
}
