package korolev.dens.calendarx.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import korolev.dens.calendarx.dto.ErrorResponseDto;
import korolev.dens.calendarx.error.CalendarValidationException;
import korolev.dens.calendarx.error.InvalidCalendarStateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CalendarXAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolation(
            ConstraintViolationException ex
    ) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponseDto(
                        "INVALID_CALENDAR",
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getConstraintViolations().stream()
                                .map(ConstraintViolation::getMessage)
                                .collect(Collectors.joining("; "))
                ));
    }

    @ExceptionHandler(CalendarValidationException.class)
    public ResponseEntity<ErrorResponseDto> handleCalendarValidationException(
            CalendarValidationException ex
    ) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponseDto(
                        "INVALID_CALENDAR",
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(InvalidCalendarStateException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidCalendarStateException(
            InvalidCalendarStateException ex
    ) {
        return ResponseEntity
                .internalServerError()
                .body(new ErrorResponseDto(
                        "INTERNAL_SERVER_ERROR",
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        ex.getMessage()
                ));
    }

    // Universal handler for other errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception ex) {
        log.info("", ex);
        return ResponseEntity
                .internalServerError()
                .body(
                        new ErrorResponseDto(
                                "INTERNAL_SERVER_ERROR",
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "something went wrong"
                        )
                );
    }

}
