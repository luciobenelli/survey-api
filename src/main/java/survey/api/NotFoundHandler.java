package survey.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@Slf4j
@ControllerAdvice
@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class NotFoundHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public void handle(EntityNotFoundException e){
        log.error(e.getMessage(), e);
    }
}
