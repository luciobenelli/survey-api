package surveyservice.response.service;

import surveyservice.response.api.ResponseDTO;

import java.util.List;

public interface ResponseService {
    List<ResponseDTO> getResponses(Long idSurvey);

    Long createResponse(Long idSurvey, ResponseDTO responseDTO);

    ResponseDTO getResponse(Long idSurvey, Long id);
}
