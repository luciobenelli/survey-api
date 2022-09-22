package surveyservice.survey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import surveyservice.survey.api.SurveyDTO;
import surveyservice.survey.model.Survey;
import surveyservice.survey.repository.SurveyRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    @Override
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }

    @Override
    public List<SurveyDTO> getSurveys() {
        return surveyRepository.findAll().stream()
                .map(SurveyDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SurveyDTO getSurvey(Long id) {
        return surveyRepository.findById(id)
                .map(SurveyDTO::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Survey " + id + " not found"));
    }

    @Override
    public Long createSurvey(SurveyDTO surveyDTO) {
        return surveyRepository.save(SurveyDTO.toEntity(Survey.builder().build(), surveyDTO)).getId();
    }

    @Override
    public void updateSurvey(SurveyDTO surveyDTO) {
        var survey = surveyRepository.findById(surveyDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Survey " + surveyDTO.getId() + " not found"));
        surveyRepository.save(SurveyDTO.toEntity(survey, surveyDTO));
    }
}
