package uol.compass.avaliacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uol.compass.avaliacao.model.States;

@Repository
public interface StatesRepository extends JpaRepository<States, Long> {

	@Query("select s from States s where (:regiao is null or s.regiao = :regiao) "
			+ "and (:populacao is null or s.populacao >= :populacao) "
			+ "and (:area is null or s.area >= :area)")
	
	List<States> findByRegiao(String regiao, Double populacao, Double area);

}
