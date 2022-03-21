package uol.compass.avaliacao.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uol.compass.avaliacao.entities.PoliticalParty;
import uol.compass.avaliacao.enums.PoliticalOffice;
import uol.compass.avaliacao.enums.Sex;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class AssociateDTO {

	private long id;
	
	private String name;
	
	private PoliticalOffice politicalOffice;
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate birthDate;
	
	private Sex sex;
	
	private PoliticalParty politicalParty;
}
