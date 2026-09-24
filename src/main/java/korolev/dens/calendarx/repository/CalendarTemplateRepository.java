package korolev.dens.calendarx.repository;

import korolev.dens.calendarx.model.CalendarType;
import korolev.dens.calendarx.model.template.CalendarTemplate;

public interface CalendarTemplateRepository {
    CalendarTemplate getTemplate(CalendarType type);
}
