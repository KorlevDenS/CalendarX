package korolev.dens.calendarx;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import korolev.dens.calendarx.controller.CalendarXAdvice;
import korolev.dens.calendarx.dto.ErrorResponseDto;
import korolev.dens.calendarx.error.CalendarValidationException;
import korolev.dens.calendarx.error.InvalidCalendarStateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalendarXAdviceTest {

    private CalendarXAdvice calendarXAdvice;

    @BeforeEach
    void setUp() {
        this.calendarXAdvice = new CalendarXAdvice();
    }

    @Test
    void handleConstraintViolation_shouldReturnBadRequest() {
        ConstraintViolation<?> violation1 = mock(ConstraintViolation.class);
        ConstraintViolation<?> violation2 = mock(ConstraintViolation.class);
        when(violation1.getMessage()).thenReturn("name is required");
        when(violation2.getMessage()).thenReturn("date is invalid");

        ConstraintViolationException ex = new ConstraintViolationException(Set.of(violation1, violation2));
        ResponseEntity<ErrorResponseDto> response = calendarXAdvice.handleConstraintViolation(ex);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        ErrorResponseDto body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.title()).isEqualTo("INVALID_CALENDAR");
        assertThat(body.status()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(body.detail()).contains("name is required").contains("date is invalid");
    }

    // ---------- handleCalendarValidationException ----------

    @Test
    void handleCalendarValidationException_shouldReturnBadRequest() {
        CalendarValidationException ex = new CalendarValidationException("calendar contains overlapping events");
        ResponseEntity<ErrorResponseDto> response = calendarXAdvice.handleCalendarValidationException(ex);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        ErrorResponseDto body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.title()).isEqualTo("INVALID_CALENDAR");
        assertThat(body.status()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(body.detail()).isEqualTo("calendar contains overlapping events");
    }

    // ---------- handleInvalidCalendarStateException ----------

    @Test
    void handleInvalidCalendarStateException_shouldReturnInternalServerError() {
        InvalidCalendarStateException ex = new InvalidCalendarStateException("calendar state is corrupted");
        ResponseEntity<ErrorResponseDto> response = calendarXAdvice.handleInvalidCalendarStateException(ex);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

        ErrorResponseDto body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.title()).isEqualTo("INTERNAL_SERVER_ERROR");
        assertThat(body.status()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(body.detail()).isEqualTo("calendar state is corrupted");
    }

    // ---------- handleException ----------

    @Test
    void handleException_shouldReturnInternalServerError() {
        ResponseEntity<ErrorResponseDto> response = calendarXAdvice.handleException(new Exception());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

        ErrorResponseDto body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.title()).isEqualTo("INTERNAL_SERVER_ERROR");
        assertThat(body.status()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(body.detail()).isEqualTo("something went wrong");
    }

}