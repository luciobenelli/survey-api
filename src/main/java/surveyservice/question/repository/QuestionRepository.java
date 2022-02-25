package surveyservice.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import surveyservice.question.model.Question;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllBySurvey_Id(Long surveyId);
    Optional<Question> findBySurvey_IdAndId(Long surveyId, Long id);
}
