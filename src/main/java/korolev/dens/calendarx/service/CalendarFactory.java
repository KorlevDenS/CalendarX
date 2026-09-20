package korolev.dens.calendarx.service;

import korolev.dens.calendarx.model.domain.CalendarYear;

import java.time.Year;

public interface CalendarFactory {
    CalendarYear createCalendar(Year year);
}
