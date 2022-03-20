package uol.compass.avaliacao.builder;

import java.time.LocalDate;

import uol.compass.avaliacao.config.dto.AssociateDTO;
import uol.compass.avaliacao.config.dto.AssociateFormDTO;
import uol.compass.avaliacao.config.dto.AssociatePoliticalPartyFormDTO;
import uol.compass.avaliacao.config.entities.Associate;
import uol.compass.avaliacao.config.enums.PoliticalOffice;
import uol.compass.avaliacao.config.enums.Sex;

public class AssociateBuilder {

	public static Associate getAssociate() {
		
		Associate a = new Associate();
		a.setId(1L);
		a.setName("Neymar");
		a.setCargo(PoliticalOffice.Senador);
		a.setBirthDate(LocalDate.now());
		a.setSex(Sex.Masculino);
		a.setPoliticalParty(PoliticalPartyBuilder.getPoliticalParty());
		
		return a;
	}

	public static AssociateDTO getAssociateDTO() {
		
		AssociateDTO aD = new AssociateDTO();
		aD.setId(1L);
		aD.setName("Neymar");
		aD.setPoliticalOffice(PoliticalOffice.Presidente);
		aD.setBirthDate(LocalDate.now());
		aD.setSex(Sex.Masculino);

		return aD;
	}

	public static AssociateFormDTO getAssociateFormDTO() {
		
		AssociateFormDTO aFD = new AssociateFormDTO();
		aFD.setName("Neymar");
		aFD.setPoliticalOffice(PoliticalOffice.Presidente);
		aFD.setBirthDate(LocalDate.now());
		aFD.setSex(Sex.Masculino);
		aFD.setPoliticalParty(3L);

		return aFD;
	}

	public static AssociatePoliticalPartyFormDTO getAssociatePoliticalPartyFormDTO() {
		
		AssociatePoliticalPartyFormDTO ney = new AssociatePoliticalPartyFormDTO();
		ney.setIdAssociate(1L);
		ney.setIdPoliticalParty(2L);

		return ney;
	}
}
