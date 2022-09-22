package surveyservice.response.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import surveyservice.response.model.Response;
import surveyservice.response.repository.ResponseRepository;
import surveyservice.survey.repository.SurveyRepository;
import surveyservice.utils.TestMock;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResponseServiceImplTest {

    @InjectMocks
    ResponseServiceImpl service;

    @Mock
    ResponseRepository responseRepository;

    @Mock
    SurveyRepository surveyRepository;

    @Test
    void getResponsesShouldReturnResponses() {
        var expected = List.of(TestMock.getResponseDTO());

        when(responseRepository.findAllBySurvey_Id(1L))
                .thenReturn(List.of(TestMock.getResponse()));

        var result = service.getResponses(1L);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void getResponseshouldReturnDTO() {
        var expected = TestMock.getResponseDTO();

        when(responseRepository.findBySurvey_IdAndId(1L, 2L))
                .thenReturn(Optional.of(TestMock.getResponse()));

        var result = service.getResponse(1L, 2L);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void getResponseshouldThrowException() {
        when(responseRepository.findBySurvey_IdAndId(1L, 2L))
                .thenReturn(Optional.empty());

        var result = assertThrows(EntityNotFoundException.class, () -> service.getResponse(1L, 2L));

        assertThat(result.getMessage())
                .isEqualTo("Response 2 not found for survey 1");
    }

    @Test
    void createResponseShouldReturnCreatedId() {
        when(surveyRepository.findById(1L))
                .thenReturn(Optional.of(TestMock.getSurvey()));

        when(responseRepository.save(any(Response.class)))
                .thenReturn(TestMock.getResponse());

        var result = service.createResponse(1L, TestMock.getResponseDTO());

        assertThat(result)
                .isEqualTo(1L);
    }

    @Test
    void createResponseShouldThrowException() {
        when(surveyRepository.findById(1L))
                .thenReturn(Optional.empty());

        var Response = TestMock.getResponseDTO();

        var result = assertThrows(EntityNotFoundException.class, () -> service.createResponse(1L, Response));

        assertThat(result.getMessage())
                .isEqualTo("Survey 1 not found");
    }

}