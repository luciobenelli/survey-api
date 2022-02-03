package surveyservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import surveyservice.Application;

import java.util.List;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    private static final String TITLE = "Survey API Project";
    private static final String DESCRIPTION = "Backend of a survey application";
    private static final String NAME = "Lúcio Benelli Pereira";
    private static final String EMAIL = "lucio.benelli@gmail.com";
    private static final String SITE = "https://github.com/luciobenelli/survey-api";
    private static final String BASE_PACKAGE = Application.class.getPackageName();
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String DEFAULT_INCLUDE_PATTERN = "/.*";

    @Value("{app.swagger.protocol:https}")
    private String protocol;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .protocols(Set.of(protocol))
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(getSecurityContexts())
                .securitySchemes(getSecuritySchemes());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .contact(new Contact(NAME, SITE, EMAIL))
                .build();
    }

    private List<SecurityScheme> getSecuritySchemes() {
        return List.of(new ApiKey("JWT", AUTHORIZATION_HEADER, "header"));
    }

    private List<SecurityContext> getSecurityContexts() {
        return List.of(SecurityContext.builder()
                .securityReferences(getSecurityReferences())
                .operationSelector(operationContext -> operationContext.requestMappingPattern().matches(DEFAULT_INCLUDE_PATTERN))
                .build());
    }

    private List<SecurityReference> getSecurityReferences() {
        var authorizationScopes = new AuthorizationScope[]{
                new AuthorizationScope("global", "accessEverything")
        };
        return List.of(new SecurityReference("JWT", authorizationScopes));
    }
}
