package korolev.dens.calendarx.model.template;

import korolev.dens.calendarx.model.CalendarType;

import java.util.List;

public record CalendarTemplate(
        CalendarType calendarType,
        List<MonthTemplate> months
) {

}
