package surveyservice.question.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import surveyservice.utils.BaseRepositoryTest;
import surveyservice.utils.TestMock;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionRepositoryTest extends BaseRepositoryTest {
    @Autowired
    QuestionRepository repository;

    @Test
    @Sql(value = {"classpath:scripts/Insert_Survey.sql", "classpath:scripts/Insert_Question.sql", "classpath:scripts/Insert_Choice.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"classpath:scripts/Clear_Tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findAllBySurvey_IdShouldReturnResults() {
        var expected = TestMock.getQuestionList();

        var result = repository.findAllBySurvey_Id(1L);

        assertThat(result)
                .isNotEmpty()
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .ignoringFields("survey.questionList")
                .isEqualTo(expected);

        assertThat(result)
                .extracting("survey")
                .doesNotContainNull();

        var choices = result.stream()
                .flatMap(question ->  question.getChoiceList().stream())
                .collect(Collectors.toList());

        assertThat(choices)
                .extracting( "question" )
                .doesNotContainNull();
    }

    @Test
    @Sql(value = {"classpath:scripts/Insert_Survey.sql", "classpath:scripts/Insert_Question.sql", "classpath:scripts/Insert_Choice.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"classpath:scripts/Clear_Tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findAllBySurvey_IdShouldReturnEmptyList() {
        var result = repository.findAllBySurvey_Id(2L);

        assertThat(result)
                .isEmpty();
    }

    @Test
    @Sql(value = {"classpath:scripts/Insert_Survey.sql", "classpath:scripts/Insert_Question.sql", "classpath:scripts/Insert_Choice.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"classpath:scripts/Clear_Tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findBySurvey_IdAndIdShouldReturnResult() {
        var expected = Optional.of(TestMock.getQuestion());

        var result = repository.findBySurvey_IdAndId(1L, 1L);

        assertThat(result)
                .isPresent()
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .ignoringFields("value.survey.questionList")
                .isEqualTo(expected);

        assertThat(result)
                .get()
                .extracting("survey")
                .isNotNull();

        var choices = result.stream()
                .flatMap(question ->  question.getChoiceList().stream())
                .collect(Collectors.toList());

        assertThat(choices)
                .extracting( "question" )
                .doesNotContainNull();
    }

    @Test
    @Sql(value = {"classpath:scripts/Insert_Survey.sql", "classpath:scripts/Insert_Question.sql", "classpath:scripts/Insert_Choice.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"classpath:scripts/Clear_Tables.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findBySurvey_IdAndIdShouldReturnEmptyOptional() {
        var result = repository.findBySurvey_IdAndId(2L, 1L);

        assertThat(result)
                .isEmpty();
    }
}