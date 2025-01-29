package by.agsr.monitorsensors.handler;

import by.agsr.monitorsensors.dto.ErrorDto;
import by.agsr.monitorsensors.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> notFoundException(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDto.builder()
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDto> notValid(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorDto.builder()
                                .message(exception.getMessage())
                                .build());
    }
}
