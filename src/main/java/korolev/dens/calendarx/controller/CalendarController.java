package korolev.dens.calendarx.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import korolev.dens.calendarx.dto.CalendarResponseDto;
import korolev.dens.calendarx.mapper.CalendarMapper;
import korolev.dens.calendarx.model.domain.CalendarYear;
import korolev.dens.calendarx.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;
    private final CalendarMapper calendarMapper;

    public CalendarController(CalendarService calendarService, CalendarMapper calendarMapper) {
        this.calendarService = calendarService;
        this.calendarMapper = calendarMapper;
    }

    @GetMapping("/{year}")
    public ResponseEntity<CalendarResponseDto> getCalendar(
            @PathVariable
            @Min(1582)
            @Max(9999)
            Integer year
    ) {
        CalendarYear c = calendarService.getCalendar(year);
        return ResponseEntity.ok(calendarMapper.toDto(c));
    }

}
