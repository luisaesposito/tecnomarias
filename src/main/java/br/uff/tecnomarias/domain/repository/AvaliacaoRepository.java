package br.uff.tecnomarias.domain.repository;

import br.uff.tecnomarias.domain.entity.Avaliacao;
import br.uff.tecnomarias.domain.entity.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    Avaliacao findTopByOrderByDataDesc();

    Optional<Avaliacao> findByAvaliadora(PessoaFisica avaliadora);
}
