package korolev.dens.calendarx.service;

import korolev.dens.calendarx.model.domain.CalendarYear;

public interface CalendarFactory {
    CalendarYear createCalendar(int year);
}
