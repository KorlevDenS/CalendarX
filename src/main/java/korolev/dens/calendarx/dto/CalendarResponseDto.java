package korolev.dens.calendarx.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Полный календарь на указанный год")
public record CalendarResponseDto(
        @Schema(description = "Номер года (1582..9999)", example = "2026")
        int year,
        @Schema(description = "Признак високосного года", example = "false")
        boolean isLeap,
        @Schema(description = "Количество дней в месяце (28..31)", example = "31")
        int daysCount,
        @Schema(description = "Список из 12 месяцев года")
        List<MonthDto> months
) {
}