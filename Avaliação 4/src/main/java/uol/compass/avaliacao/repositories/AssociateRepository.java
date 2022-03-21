package uol.compass.avaliacao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uol.compass.avaliacao.entities.Associate;
import uol.compass.avaliacao.enums.PoliticalOffice;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {

	@Query("select s from Associate s where :cargo is null or s.cargo = :cargo")
	List<Associate> filterCargo(PoliticalOffice cargo);
}
