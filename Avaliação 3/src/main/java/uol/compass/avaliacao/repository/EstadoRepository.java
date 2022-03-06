package uol.compass.avaliacao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import uol.compass.avaliacao.model.Estado;
import uol.compass.avaliacao.model.Regioes;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	Page<Estado> findByRegiao(Regioes regiao, Pageable paginacao);

}
