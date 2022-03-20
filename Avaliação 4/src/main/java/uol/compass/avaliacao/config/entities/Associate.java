package uol.compass.avaliacao.config.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uol.compass.avaliacao.config.enums.PoliticalOffice;
import uol.compass.avaliacao.config.enums.Sex;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
public class Associate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private PoliticalOffice cargo;
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate birthDate;
	
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	@JsonIgnore
	@ManyToOne
	private PoliticalParty politicalParty;

	public Associate(String name, PoliticalOffice cargo, LocalDate birthDate, Sex sex,
			PoliticalParty politicalParty) {
		this.name = name;
		this.cargo = cargo;
		this.birthDate = birthDate;
		this.sex = sex;
		this.politicalParty = politicalParty;
	}
}
