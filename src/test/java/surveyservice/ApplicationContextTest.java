package surveyservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationContextTest {
    @Autowired
    ApplicationContext context;

    @Test
    void shouldLoadSpringBootContext(){
        assertThat(context.getId())
                .isNotNull()
                .contains("survey-service-api");
    }

}
