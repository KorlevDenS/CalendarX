package korolev.dens.calendarx.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Информация о месяце")
public record MonthDto(
        @Schema(description = "Порядковый номер месяца (1..12)", example = "1")
        int monthNumber,
        @Schema(description = "Название месяца в ISO формате", example = "JANUARY")
        String name,
        @Schema(description = "Количество дней в месяце (28..31)", example = "31")
        int daysCount,
        @Schema(description = "Список дней месяца")
        List<DayDto> days
) {
}
