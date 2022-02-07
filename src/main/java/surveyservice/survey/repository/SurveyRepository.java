package surveyservice.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import surveyservice.survey.model.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
