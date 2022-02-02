package survey.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import survey.service.SurveyService;
import survey.utils.TestMock;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SurveyController.class)
class SurveyControllerTest extends BaseControllerTest {
    private static final String URL = "/v1/surveys";
    private static final String URL_WITH_ID = "/v1/surveys/{id}";

    @MockBean
    private SurveyService surveyService;

    @Test
    void shouldReturnSurveys() throws Exception {
        when(surveyService.getSurveys())
                .thenReturn(List.of(TestMock.getSurveyDTO()));

        getMvc().perform(get(URL))
                .andExpect(status().isOk());

        verify(surveyService).getSurveys();
    }

    @Test
    void shouldReturnSurvey() throws Exception {
        var survey = TestMock.getSurveyDTO();

        when(surveyService.getSurvey(anyLong()))
                .thenReturn(survey);

        getMvc().perform(get(URL_WITH_ID, "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(survey)));

        verify(surveyService).getSurvey(1L);
    }

    @Test
    void shouldCreateSurvey() throws Exception {
        when(surveyService.createSurvey(any(SurveyDTO.class)))
                .thenReturn(1L);

        getMvc().perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(TestMock.getSurveyDTO())))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "v1/surveys/1"));

        verify(surveyService).createSurvey(any(SurveyDTO.class));
    }

    @Test
    void shouldUpdateSurvey() throws Exception {
        getMvc().perform(put(URL_WITH_ID, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(TestMock.getSurveyDTO())))
                .andExpect(status().isOk())
                .andExpect(header().string("location", "v1/surveys/1"));

        verify(surveyService).updateSurvey(any(SurveyDTO.class));
    }

    @Test
    void shouldDeleteSurvey() throws Exception{
        getMvc().perform(delete(URL_WITH_ID, "1"))
                .andExpect(status().isNoContent());

        verify(surveyService).deleteSurvey(1L);
    }

}