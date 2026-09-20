package korolev.dens.calendarx.model.template;

import java.time.Month;
import java.util.List;

public record MonthTemplate(
        Month month,
        List<DayTemplate> days
) {
}
