package surveyservice.question.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import surveyservice.question.service.QuestionService;
import surveyservice.utils.BaseControllerTest;
import surveyservice.utils.TestMock;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuestionController.class)
class QuestionControllerTest extends BaseControllerTest {

    private static final String URL = "/v1/surveys/{surveyId}/questions";
    private static final String URL_WITH_ID = "/v1/surveys/{surveyId}/questions/{id}";

    @MockBean
    QuestionService questionService;

    @Test
    void shouldReturnQuestionsOfSurvey() throws Exception {
        when(questionService.getQuestions(1L))
                .thenReturn(List.of(TestMock.getQuestionDTO()));

        getMvc().perform(get(URL, "1"))
                .andExpect(status().isOk());

        verify(questionService).getQuestions(1L);
    }

    @Test
    void shouldReturnQuestionOfSurvey() throws Exception {
        var question = TestMock.getQuestionDTO();

        when(questionService.getQuestion(1L, 1L))
                .thenReturn(question);

        getMvc().perform(get(URL_WITH_ID, "1", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(question)));

        verify(questionService).getQuestion(1L, 1L);
    }

    @Test
    void shouldReturnQuestionOfSurveyNotFound() throws Exception {
        doThrow(EntityNotFoundException.class)
                .when(questionService).getQuestion(1L, 2L);

        getMvc().perform(get(URL_WITH_ID, "1", "2"))
                .andExpect(status().isNotFound());

        verify(questionService).getQuestion(1L, 2L);
    }

    @Test
    void shouldCreateQuestion() throws Exception {
        var question = TestMock.getQuestionDTO();

        when(questionService.createQuestion(anyLong(), any(QuestionDTO.class)))
                .thenReturn(1L);

        getMvc().perform(post(URL, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(question)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "v1/surveys/1/questions/1"));

        verify(questionService).createQuestion(anyLong(), any(QuestionDTO.class));
    }

    @Test
    void shouldUpdateQuestion() throws Exception {
        var question = TestMock.getQuestionDTO();

        when(questionService.createQuestion(anyLong(), any(QuestionDTO.class)))
                .thenReturn(1L);

        getMvc().perform(put(URL_WITH_ID, "1", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(question)))
                .andExpect(status().isOk())
                .andExpect(header().string("location", "v1/surveys/1/questions/1"));

        verify(questionService).updateQuestion(anyLong(), any(QuestionDTO.class));
    }

    @Test
    void shouldDeleteQuestion() throws Exception{
        getMvc().perform(delete(URL_WITH_ID, "1", "1"))
                .andExpect(status().isNoContent());

        verify(questionService).deleteQuestion(1L, 1L);
    }

}