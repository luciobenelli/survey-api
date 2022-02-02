package survey.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import survey.service.SurveyService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SurveyController.class)
class SurveyControllerTest extends BaseControllerTest {
    private static final String URL = "/v1/surveys";
    private static final String URL_WITH_ID = "/v1/surveys/{id}";

    @MockBean
    private SurveyService surveyService;

    private List<SurveyDTO> surveys;

    @BeforeEach
    private void setup() {
        surveys = List.of(SurveyDTO.build(1L));
    }

    @Test
    void shouldReturnSurveys() throws Exception {
        when(surveyService.getSurveys())
                .thenReturn(surveys);

        getMvc().perform(get(URL))
                .andExpect(status().isOk());

        verify(surveyService).getSurveys();
    }

    @Test
    void shouldReturnSurvey() throws Exception {
        when(surveyService.getSurvey(anyLong()))
                .thenReturn(surveys.get(0));

        getMvc().perform(get(URL_WITH_ID, "1"))
                .andExpect(status().isOk());

        verify(surveyService).getSurvey(1L);
    }

    @Test
    void shouldCreateSurvey() throws Exception {
        when(surveyService.createSurvey(any(SurveyDTO.class)))
                .thenReturn(1L);

        getMvc().perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(surveys.get(0))))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "v1/surveys/1"));

        verify(surveyService).createSurvey(any(SurveyDTO.class));
    }

    @Test
    void shouldUpdateSurvey() throws Exception {
        getMvc().perform(put(URL_WITH_ID, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(surveys.get(0))))
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