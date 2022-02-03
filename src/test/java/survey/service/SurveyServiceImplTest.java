package survey.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import survey.model.Survey;
import survey.repository.SurveyRepository;
import survey.utils.TestMock;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SurveyServiceImplTest {

    @InjectMocks
    private SurveyServiceImpl service;

    @Mock
    private SurveyRepository surveyRepository;

    @Test
    void deleteSurveyShouldCallRepository() {
        service.deleteSurvey(1L);

        verify(surveyRepository).deleteById(1L);
    }

    @Test
    void getSurveysShouldReturnSurveys() {
        var expected = List.of(TestMock.getSurvey());

        when(surveyRepository.findAll())
                .thenReturn(expected);

        var result = service.getSurveys();

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void getSurveyShouldReturnDTO() {
        var expected = TestMock.getSurvey();

        when(surveyRepository.findById(2L))
                .thenReturn(Optional.of(expected));

        var result = service.getSurvey(2L);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void getSurveyShouldThrowException() {
        when(surveyRepository.findById(1L))
                .thenReturn(Optional.empty());

        var exception = assertThrows(EntityNotFoundException.class, () -> service.getSurvey(1L));

        assertThat(exception.getMessage())
                .isEqualTo("Survey 1 not found");
    }

    @Test
    void createSurveyShouldReturnCreatedId() {
        var expected = TestMock.getSurvey();

        when(surveyRepository.saveAndFlush(any(Survey.class)))
                .thenReturn(expected);

        var result = service.createSurvey(TestMock.getSurveyDTO());

        assertThat(result)
                .isEqualTo(expected.getId());
    }

    @Test
    void updateSurveyShouldCallSaveAndFlush() {
        when(surveyRepository.findById(1L))
                .thenReturn(Optional.of(TestMock.getSurvey()));

        service.updateSurvey(TestMock.getSurveyDTO());

        verify(surveyRepository).saveAndFlush(any(Survey.class));
    }

    @Test
    void updateSurveyShouldThrowException() {
        var dto = TestMock.getSurveyDTO();

        when(surveyRepository.findById(1L))
                .thenReturn(Optional.empty());

        var exception = assertThrows(EntityNotFoundException.class, () -> service.updateSurvey(dto));

        assertThat(exception.getMessage())
                .isEqualTo("Survey 1 not found");
    }
}