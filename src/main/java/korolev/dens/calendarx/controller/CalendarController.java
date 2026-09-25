package korolev.dens.calendarx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import korolev.dens.calendarx.dto.CalendarResponseDto;
import korolev.dens.calendarx.dto.MonthDto;
import korolev.dens.calendarx.mapper.CalendarMapper;
import korolev.dens.calendarx.model.domain.CalendarMonth;
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

    @Operation(summary = "Получить григорианский календарь на запрашиваемый год")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение календаря",
            content = @Content(schema = @Schema(implementation = CalendarResponseDto.class))
    )
    @ApiStandardErrors
    @GetMapping("/gregorian/{year}")
    public ResponseEntity<CalendarResponseDto> getGregorianCalendar(
            @PathVariable
            @Min(1582) @Max(9999)
            Integer year
    ) {
        CalendarYear c = calendarService.getGregorianCalendar(year);
        return ResponseEntity.ok(calendarMapper.toDto(c));
    }

    @Operation(summary = "Получить григорианский календарь на нужный месяц указанного года")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение календаря на месяц",
            content = @Content(schema = @Schema(implementation = MonthDto.class))
    )
    @ApiStandardErrors
    @GetMapping("/gregorian/{year}/{month}")
    public ResponseEntity<MonthDto> getGregorianYearMonth(
            @PathVariable
            @Min(1582) @Max(9999)
            Integer year,
            @PathVariable
            @Min(1) @Max(12)
            Integer month
    ) {
        CalendarMonth m = calendarService.getGregorianYearMonth(year, month);
        return ResponseEntity.ok(calendarMapper.toDto(m));
    }

    // Ethiopian
    // For more calendars and more api it can be good idea to make separate controllers!

    @Operation(summary = "Получить эфиопского календарь на запрашиваемый год")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение календаря",
            content = @Content(schema = @Schema(implementation = CalendarResponseDto.class))
    )
    @ApiStandardErrors
    @GetMapping("/ethiopian/{year}")
    public ResponseEntity<CalendarResponseDto> getCalendar(
            @PathVariable
            @Min(1) @Max(9999)
            Integer year
    ) {
        CalendarYear c = calendarService.getEthiopianCalendar(year);
        return ResponseEntity.ok(calendarMapper.toDto(c));
    }

    @Operation(summary = "Получить эфиопский календарь на нужный месяц указанного года")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение календаря на месяц",
            content = @Content(schema = @Schema(implementation = MonthDto.class))
    )
    @ApiStandardErrors
    @GetMapping("/ethiopian/{year}/{month}")
    public ResponseEntity<MonthDto> getYearMonth(
            @PathVariable
            @Min(1) @Max(9999)
            Integer year,
            @PathVariable
            @Min(1) @Max(13)
            Integer month
    ) {
        CalendarMonth m = calendarService.getEthiopianYearMonth(year, month);
        return ResponseEntity.ok(calendarMapper.toDto(m));
    }

}
