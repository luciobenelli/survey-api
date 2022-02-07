package surveyservice.survey.service;

import surveyservice.survey.api.SurveyDTO;

import java.util.List;

public interface SurveyService {
    void deleteSurvey(Long id);

    List<SurveyDTO> getSurveys();

    SurveyDTO getSurvey(Long id);

    Long createSurvey(SurveyDTO surveyDTO);

    void updateSurvey(SurveyDTO surveyDTO);
}
