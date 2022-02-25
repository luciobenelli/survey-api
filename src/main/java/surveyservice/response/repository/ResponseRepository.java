package surveyservice.response.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import surveyservice.response.model.Response;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findAllBySurvey_Id(Long surveyId);

    Optional<Response> findBySurvey_IdAndId(Long idSurvey, Long id);
}
