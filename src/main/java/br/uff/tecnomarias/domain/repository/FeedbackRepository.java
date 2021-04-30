package br.uff.tecnomarias.domain.repository;

import br.uff.tecnomarias.domain.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findTop3ByOrderByIdDesc();

    Feedback findTop1ByOrderByIdDesc();

}
