package uol.compass.avaliacao.config.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uol.compass.avaliacao.config.entities.PoliticalParty;
import uol.compass.avaliacao.config.enums.Ideology;
import uol.compass.avaliacao.config.exceptions.DefaultException;
import uol.compass.avaliacao.config.repositories.PoliticalPartyRepository;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PoliticalPartyFormDTO {

	@NotEmpty(message = "The name field is required")
	private String name;
	
	@NotEmpty(message = "The acronym field is required")
	private String acronym;
	
	@NotNull(message = "The ideology field is required")
	private Ideology ideology;
	
	@NotNull(message = "Fundation date is required")
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate foundationDate;

	public PoliticalParty convert() {
		return new PoliticalParty(this.name, this.acronym, this.ideology, this.foundationDate);
	}

	public PoliticalParty update(long id, PoliticalPartyRepository partyRepository) {
		PoliticalParty pp = partyRepository.findById(id)
				.orElseThrow(() -> new DefaultException("Political Party not found", "NOT_FOUND", 404));
		
		pp.setName(this.name);
		pp.setAcronym(this.acronym);
		pp.setIdeology(this.ideology);
		pp.setFoundationDate(this.foundationDate);
		
		return pp;
	}
}
