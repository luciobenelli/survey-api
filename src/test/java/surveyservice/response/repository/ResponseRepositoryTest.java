package surveyservice.response.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import surveyservice.utils.BaseRepositoryTest;
import surveyservice.utils.TestMock;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ResponseRepositoryTest extends BaseRepositoryTest {

    @Autowired
    ResponseRepository repository;
    @Test
    @Sql(value = {"classpath:scripts/Insert_Survey.sql", "classpath:scripts/Insert_Question.sql", "classpath:scripts/Insert_Choice.sql",
            "classpath:scripts/Insert_Respondent.sql", "classpath:scripts/Insert_Response.sql", "classpath:scripts/Insert_Answer.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"classpath:scripts/Clear_Tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findAllBySurvey_IdShouldReturnResults() {
        var expected = List.of(TestMock.getResponse());

        var result = repository.findAllBySurvey_Id(1L);

        assertThat(result)
                .isNotEmpty()
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .ignoringFields("answerList", "survey")
                .isEqualTo(expected);

        assertThat(result)
                .extracting("survey")
                .doesNotContainNull();

        assertThat(result)
                .extracting("respondent")
                .doesNotContainNull();

        var answers = result.stream()
                .flatMap(response ->  response.getAnswerList().stream())
                .collect(Collectors.toList());

        assertThat(answers)
                .extracting( "question" )
                .doesNotContainNull();

        assertThat(answers)
                .extracting( "choice" )
                .doesNotContainNull();

        assertThat(answers)
                .extracting( "response" )
                .doesNotContainNull();
    }

    @Test
    @Sql(value = {"classpath:scripts/Insert_Survey.sql", "classpath:scripts/Insert_Question.sql", "classpath:scripts/Insert_Choice.sql",
            "classpath:scripts/Insert_Respondent.sql", "classpath:scripts/Insert_Response.sql", "classpath:scripts/Insert_Answer.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"classpath:scripts/Clear_Tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findAllBySurvey_IdShouldReturnEmptyList() {
        var result = repository.findAllBySurvey_Id(2L);

        assertThat(result)
                .isEmpty();
    }

    @Test
    @Sql(value = {"classpath:scripts/Insert_Survey.sql", "classpath:scripts/Insert_Question.sql", "classpath:scripts/Insert_Choice.sql",
            "classpath:scripts/Insert_Respondent.sql", "classpath:scripts/Insert_Response.sql", "classpath:scripts/Insert_Answer.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"classpath:scripts/Clear_Tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findBySurvey_IdAndIdShouldReturnResult() {
        var expected = Optional.of(TestMock.getResponse());

        var result = repository.findBySurvey_IdAndId(1L, 1L);

        assertThat(result)
                .isPresent()
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .ignoringFields("value.answerList", "value.survey")
                .isEqualTo(expected);

        assertThat(result)
                .get()
                .extracting("survey")
                .isNotNull();

        assertThat(result)
                .get()
                .extracting("respondent")
                .isNotNull();

        var answers = result.stream()
                .flatMap(response ->  response.getAnswerList().stream())
                .collect(Collectors.toList());

        assertThat(answers)
                .extracting( "question" )
                .doesNotContainNull();

        assertThat(answers)
                .extracting( "choice" )
                .doesNotContainNull();

        assertThat(answers)
                .extracting( "response" )
                .doesNotContainNull();
    }

    @Test
    @Sql(value = {"classpath:scripts/Insert_Survey.sql", "classpath:scripts/Insert_Question.sql", "classpath:scripts/Insert_Choice.sql",
            "classpath:scripts/Insert_Respondent.sql", "classpath:scripts/Insert_Response.sql", "classpath:scripts/Insert_Answer.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"classpath:scripts/Clear_Tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findBySurvey_IdAndIdShouldReturnEmptyOptional() {
        var result = repository.findBySurvey_IdAndId(2L, 1L);

        assertThat(result)
                .isEmpty();
    }
}