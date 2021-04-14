package br.uff.tecnomarias.domain.repository;

import br.uff.tecnomarias.domain.entity.PessoaJuridica;
import br.uff.tecnomarias.domain.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

    List<Vaga> findByEmpresa(PessoaJuridica empresa);

    List<Vaga> findByAreaAtuacao(String areaAtuacao);

    List<Vaga> findByCargo(String cargo);

    List<Vaga> findByLocalidade(String localidade);

    @Query(value = "SELECT DISTINCT area_atuacao FROM Vaga", nativeQuery = true)
    List<String> listAreaAtuacao();
}