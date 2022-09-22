package surveyservice.response.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import surveyservice.response.api.ResponseDTO;
import surveyservice.response.repository.ResponseRepository;
import surveyservice.survey.repository.SurveyRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {

    private final ResponseRepository responseRepository;
    private final SurveyRepository surveyRepository;

    @Override
    public List<ResponseDTO> getResponses(Long idSurvey) {
        return responseRepository.findAllBySurvey_Id(idSurvey)
                .stream()
                .map(ResponseDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDTO getResponse(Long idSurvey, Long id) {
        return responseRepository.findBySurvey_IdAndId(idSurvey, id)
                .map(ResponseDTO::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Response " + id + " not found for survey " + idSurvey));
    }

    @Override
    public Long createResponse(Long idSurvey, ResponseDTO responseDTO) {
        var response = surveyRepository.findById(idSurvey)
                .map(survey -> ResponseDTO.toEntity(survey, responseDTO))
                .orElseThrow(() -> new EntityNotFoundException("Survey " + idSurvey + " not found"));
        return responseRepository.save(response).getId();
    }

}
