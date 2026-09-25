package korolev.dens.calendarx.model.template;

import java.util.List;

public record MonthTemplate(
        int monthNumber,
        List<DayTemplate> days
) {
}
