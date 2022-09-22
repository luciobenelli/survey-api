package surveyservice.response.api;

import org.junit.jupiter.api.Test;
import surveyservice.utils.TestMock;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnswerDTOTest {

    @Test
    void shouldThrowExceptionChoiceNotFound() {
        var survey  = TestMock.getSurvey().toBuilder()
                .questionList(TestMock.getQuestionList())
                .build();
        var answerDTO = TestMock.getAnswerDTO(1L, 999L);

        var exception = assertThrows(EntityNotFoundException.class, () -> AnswerDTO.toEntity(survey, answerDTO));

        assertThat( exception )
                .extracting("message")
                .isEqualTo("Choice 999 not found for question 1");
    }

    @Test
    void shouldThrowExceptionQuestionNotFound() {
        var survey  = TestMock.getSurvey();
        var answerDTO = TestMock.getAnswerDTO(999L, 1L);

        var exception = assertThrows(EntityNotFoundException.class, () -> AnswerDTO.toEntity(survey, answerDTO));

        assertThat( exception )
                .extracting("message")
                .isEqualTo("Question 999 not found for survey 1");
    }
}