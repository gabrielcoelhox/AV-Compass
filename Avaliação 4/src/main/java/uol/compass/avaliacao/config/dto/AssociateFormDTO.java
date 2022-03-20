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
import uol.compass.avaliacao.config.entities.Associate;
import uol.compass.avaliacao.config.entities.PoliticalParty;
import uol.compass.avaliacao.config.enums.PoliticalOffice;
import uol.compass.avaliacao.config.enums.Sex;
import uol.compass.avaliacao.config.exceptions.DefaultException;
import uol.compass.avaliacao.config.repositories.AssociateRepository;
import uol.compass.avaliacao.config.repositories.PoliticalPartyRepository;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssociateFormDTO {

	@NotEmpty(message = "The name field is required")
	private String name;
	
	@NotNull(message = "The political office field is required")
	private PoliticalOffice politicalOffice;
	
	@NotNull(message = "The birth date field is required")
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate birthDate;
	
	@NotNull(message = "The sex field is required")
	private Sex sex;
	
	private long politicalParty;

	public Associate convert(PoliticalPartyRepository partyRepository) {
		PoliticalParty politicalParty = partyRepository.findById(this.politicalParty)
				.orElseThrow(() -> new DefaultException("Political Party not found", "NOT_FOUND", 404));
		return new Associate(this.name, this.politicalOffice, this.birthDate, this.sex, politicalParty);
	}

	public Associate update(Long id, AssociateRepository associateRepository,
			PoliticalPartyRepository partyRepository) {
		Associate a = associateRepository.findById(id)
				.orElseThrow(() -> new DefaultException("The associate with id = " + id + " not found", "NOT_FOUND", 404));
		
		a.setName(this.name);
		a.setCargo(this.politicalOffice);
		a.setBirthDate(this.birthDate);
		a.setSex(this.sex);
		
		PoliticalParty politicalParty = partyRepository.findById(this.politicalParty)
				.orElseThrow(() -> new DefaultException("Political Party is not found", "NOT_FOUND", 404));
		a.setPoliticalParty(politicalParty);
		return a;
	}
}
