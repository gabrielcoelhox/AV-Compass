package uol.compass.avaliacao.builder;

import java.time.LocalDate;

import uol.compass.avaliacao.config.dto.PoliticalPartyDTO;
import uol.compass.avaliacao.config.dto.PoliticalPartyFormDTO;
import uol.compass.avaliacao.config.dto.PoliticalPartyWithAssociatesDTO;
import uol.compass.avaliacao.config.entities.PoliticalParty;
import uol.compass.avaliacao.config.enums.Ideology;

public class PoliticalPartyBuilder {

	public static PoliticalParty getPoliticalParty() {
		
		PoliticalParty pp = new PoliticalParty();
		pp.setId(3L);
		pp.setName("Partido dos Calvos");
		pp.setAcronym("CALVO");
		pp.setIdeology(Ideology.Direita);
		pp.setFoundationDate(LocalDate.now());
		
		return pp;
	}
	
	public static PoliticalPartyDTO getPoliticalPartyDTO() {
		
		PoliticalPartyDTO pp = new PoliticalPartyDTO();
		pp.setId(3L);
		pp.setName("Partido dos Calvos");
		pp.setAcronym("CALVO");
		pp.setIdeology(Ideology.Direita);
		pp.setFoundationDate(LocalDate.now());
		
		return pp;
	}
	
	public static PoliticalPartyFormDTO getPoliticalPartyFormDTO() {
		
		PoliticalPartyFormDTO pp = new PoliticalPartyFormDTO();
		pp.setName("Partido dos Calvos");
		pp.setAcronym("CALVO");
		pp.setIdeology(Ideology.Direita);
		pp.setFoundationDate(LocalDate.now());
		
		return pp;
	}
	
	public static PoliticalPartyWithAssociatesDTO getPoliticalPartyWithAssociatesDTO() {
		
		PoliticalPartyWithAssociatesDTO pp = new PoliticalPartyWithAssociatesDTO();
		pp.setId(3L);
		pp.setName("Partido dos Calvos");
		pp.setAcronym("CALVO");
		pp.setIdeology(Ideology.Direita);
		pp.setFoundationDate(LocalDate.now());
		
		return pp;
	}
}
