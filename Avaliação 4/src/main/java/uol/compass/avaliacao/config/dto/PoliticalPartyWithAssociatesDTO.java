package uol.compass.avaliacao.config.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uol.compass.avaliacao.config.entities.Associate;
import uol.compass.avaliacao.config.enums.Ideology;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PoliticalPartyWithAssociatesDTO {

	private long id;
	private String name;
	private String acronym;
	private Ideology ideology;
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate foundationDate;
	
	private List<Associate> associates;
}
