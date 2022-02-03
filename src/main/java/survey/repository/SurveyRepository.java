package survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import survey.model.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
