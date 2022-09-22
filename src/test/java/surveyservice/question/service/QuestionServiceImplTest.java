package surveyservice.question.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import surveyservice.question.model.Question;
import surveyservice.question.repository.QuestionRepository;
import surveyservice.survey.repository.SurveyRepository;
import surveyservice.utils.TestMock;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @InjectMocks
    QuestionServiceImpl service;

    @Mock
    QuestionRepository questionRepository;

    @Mock
    SurveyRepository surveyRepository;

    @Test
    void deleteShouldCallRepository() {
        service.deleteQuestion(1L, 2L);

        verify(questionRepository).deleteById(2L);
    }

    @Test
    void getQuestionsShouldReturnQuestions() {
        var expected = List.of(TestMock.getQuestionDTO());

        when(questionRepository.findAllBySurvey_Id(1L))
                .thenReturn(List.of(TestMock.getQuestion()));

        var result = service.getQuestions(1L);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void getQuestionShouldReturnDTO() {
        var expected = TestMock.getQuestionDTO();

        when(questionRepository.findBySurvey_IdAndId(1L, 2L))
                .thenReturn(Optional.of(TestMock.getQuestion()));

        var result = service.getQuestion(1L, 2L);

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void getQuestionShouldThrowException() {
        when(questionRepository.findBySurvey_IdAndId(1L, 2L))
                .thenReturn(Optional.empty());

        var result = assertThrows(EntityNotFoundException.class, () -> service.getQuestion(1L, 2L));

        assertThat(result.getMessage())
                .isEqualTo("Question 2 not found for survey 1");
    }

    @Test
    void createQuestionShouldReturnCreatedId() {
        var expected = TestMock.getQuestion().getId();

        when(surveyRepository.findById(1L))
                .thenReturn(Optional.of(TestMock.getSurvey()));

        when(questionRepository.save(any(Question.class)))
                .thenReturn(TestMock.getQuestion());

        var result = service.createQuestion(1L, TestMock.getQuestionDTO());

        assertThat(result)
                .isEqualTo(expected);
    }

    @Test
    void createQuestionShouldThrowException() {
        when(surveyRepository.findById(1L))
                .thenReturn(Optional.empty());

        var question = TestMock.getQuestionDTO();

        var result = assertThrows(EntityNotFoundException.class, () -> service.createQuestion(1L, question));

        assertThat(result.getMessage())
                .isEqualTo("Survey 1 not found");
    }

    @Test
    void updateQuestionShouldCallSave() {
        when(questionRepository.findBySurvey_IdAndId(1L, 1L))
                .thenReturn(Optional.of(TestMock.getQuestion()));

        service.updateQuestion(1L, TestMock.getQuestionDTO());

        verify(questionRepository).save(any(Question.class));
    }

    @Test
    void updateQuestionShouldThrowException() {
        when(questionRepository.findBySurvey_IdAndId(1L, 1L))
                .thenReturn(Optional.empty());

        var questionDTO = TestMock.getQuestionDTO();

        var result = assertThrows(EntityNotFoundException.class, () -> service.updateQuestion(1L, questionDTO));

        assertThat(result.getMessage())
                .isEqualTo("Question 1 not found for survey 1");
    }

}