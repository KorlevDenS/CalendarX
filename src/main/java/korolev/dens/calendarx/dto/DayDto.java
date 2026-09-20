package korolev.dens.calendarx.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Информация о конкретном дне")
public record DayDto(
        @Schema(description = "День месяца (1..31)", example = "12")
        int dayOfMonth,
        @Schema(description = "День недели в ISO формате", example = "THURSDAY")
        String dayOfWeek,
        @Schema(description = "Признак выходного дня (суббота или воскресенье)", example = "false")
        boolean isWeekend
) {
}
