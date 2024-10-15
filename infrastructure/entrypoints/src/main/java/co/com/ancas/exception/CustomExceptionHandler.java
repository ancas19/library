package co.com.ancas.exception;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.response.ErrorResponse;
import co.com.ancas.response.GeneralResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GeneralResponse<ErrorResponse>> handleBadRequestException(BadRequestException ex, WebRequest request) {
        log.error("Bad Request Exception: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(
                        GeneralResponse.<ErrorResponse>builder()
                                .message(Messages.MESSAGE_GENERAL_BAD_REQUEST.getMessage())
                                .data(
                                        ErrorResponse.builder()
                                                .timeStamp(LocalDate.now())
                                                .details(request.getDescription(false))
                                                .message(ex.getMessage())
                                                .build()
                                )
                                .build()
                );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GeneralResponse<ErrorResponse>> handleNotFoundException(NotFoundException ex, WebRequest request) {
        log.error("Not Found Exception: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(
                        GeneralResponse.<ErrorResponse>builder()
                                .message(Messages.MESSAGE_GENERAL_NOT_FOUND.getMessage())
                                .data(
                                        ErrorResponse.builder()
                                                .timeStamp(LocalDate.now())
                                                .details(request.getDescription(false))
                                                .message(ex.getMessage())
                                                .build()
                                )
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponse<ErrorResponse>> handelGeneralException(Exception ex, WebRequest request) {
        log.error("Internal Server Exception: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(
                        GeneralResponse.<ErrorResponse>builder()
                                .message(Messages.MESSAGE_EXCEPTION.getMessage())
                                .data(
                                        ErrorResponse.builder()
                                                .timeStamp(LocalDate.now())
                                                .details(request.getDescription(false))
                                                .message(Messages.MESSAGE_EXCEPTION.getMessage())
                                                .build()
                                )
                                .build()
                );
    }

}
