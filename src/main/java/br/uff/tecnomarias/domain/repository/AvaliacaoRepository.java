package br.uff.tecnomarias.domain.repository;

import br.uff.tecnomarias.domain.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    Avaliacao findTopByOrderByTimestampDesc();
}
