package surveyservice.response.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import surveyservice.response.service.ResponseService;
import surveyservice.utils.BaseControllerTest;
import surveyservice.utils.TestMock;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ResponseController.class)
class ResponseControllerTest extends BaseControllerTest {

    private static final String URL = "/v1/surveys/{surveyId}/responses";
    private static final String URL_WITH_ID = "/v1/surveys/{surveyId}/responses/{id}";

    @MockBean
    private ResponseService responseService;

    @Test
    void shouldReturnResponsesOfSurvey() throws Exception {
        when(responseService.getResponses(1L))
                .thenReturn(List.of(TestMock.getResponseDTO()));

        getMvc().perform(get(URL, "1"))
                .andExpect(status().isOk());

        verify(responseService).getResponses(1L);
    }

    @Test
    void shouldReturnResponseOfSurvey() throws Exception {
        var response = TestMock.getResponseDTO();

        when(responseService.getResponse(1L, 1L))
                .thenReturn(response);

        getMvc().perform(get(URL_WITH_ID, "1", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(response)));

        verify(responseService).getResponse(1L, 1L);
    }

    @Test
    void shouldReturnQuestionOfSurveyNotFound() throws Exception {
        doThrow(EntityNotFoundException.class)
                .when(responseService).getResponse(1L, 2L);

        getMvc().perform(get(URL_WITH_ID, "1", "2"))
                .andExpect(status().isNotFound());

        verify(responseService).getResponse(1L, 2L);
    }

    @Test
    void shouldCreateResponse() throws Exception {
        var response = TestMock.getResponseDTO();

        when(responseService.createResponse(anyLong(), any(ResponseDTO.class)))
                .thenReturn(1L);

        getMvc().perform(post(URL, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(response)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "v1/surveys/1/responses/1"));

        verify(responseService).createResponse(anyLong(), any(ResponseDTO.class));
    }

}