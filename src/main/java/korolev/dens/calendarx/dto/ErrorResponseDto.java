package korolev.dens.calendarx.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Единый формат передачи ошибок API сервиса CalendarX")
// I think it can be extended for real app. For now - only base info.
public record ErrorResponseDto(
        @Schema(description = "Краткое название ошибки", example = "Bad Request")
        String title,
        @Schema(description = "HTTP статус-код", example = "400")
        int status,
        @Schema(description = "Подробное описание причины ошибки", example = "Год должен быть меньше 9999")
        String detail
) {
}
