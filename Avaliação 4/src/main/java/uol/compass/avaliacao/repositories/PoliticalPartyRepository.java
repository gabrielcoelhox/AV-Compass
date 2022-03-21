package uol.compass.avaliacao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uol.compass.avaliacao.entities.PoliticalParty;
import uol.compass.avaliacao.enums.Ideology;

@Repository
public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Long>{

	@Query("select s from PoliticalParty s where :ideology is null or s.ideology = :ideology")
	List<PoliticalParty> filterIdeology(Ideology ideology);
	
//	PoliticalParty filterName(String politicalParty);
}
