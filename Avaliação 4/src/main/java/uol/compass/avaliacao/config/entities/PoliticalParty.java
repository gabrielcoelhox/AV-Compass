package uol.compass.avaliacao.config.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uol.compass.avaliacao.config.enums.Ideology;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
public class PoliticalParty implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String acronym;
	
	@Enumerated(EnumType.STRING)
	private Ideology ideology;
	
	@Column(nullable = false)
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate foundationDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "politicalParty")
	private List<Associate> associates = new ArrayList<>();

	public PoliticalParty(String name, String acronym, Ideology ideology, LocalDate foundationDate) {
		this.name = name;
		this.acronym = acronym;
		this.ideology = ideology;
		this.foundationDate = foundationDate;
	}
}
