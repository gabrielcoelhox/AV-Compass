package uol.compass.avaliacao.config.dto;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uol.compass.avaliacao.config.entities.Associate;
import uol.compass.avaliacao.config.entities.PoliticalParty;
import uol.compass.avaliacao.config.repositories.AssociateRepository;
import uol.compass.avaliacao.config.repositories.PoliticalPartyRepository;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssociatePoliticalPartyFormDTO {

	@NotNull(message = "Associate id is required")
	private long idAssociate;
	
	@NotNull(message = "Political party id is required")
	private long idPoliticalParty;

	public Associate updateParty(AssociateRepository associateRepository, PoliticalPartyRepository partyRepository) {
		Optional<Associate> optionalAssociate = associateRepository.findById(idAssociate);
		Optional<PoliticalParty> optionalParty = partyRepository.findById(idPoliticalParty);
		optionalAssociate.get().setPoliticalParty(optionalParty.get());
		return optionalAssociate.get();
	}
}
