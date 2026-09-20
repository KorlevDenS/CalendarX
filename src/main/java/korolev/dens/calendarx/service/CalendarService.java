package korolev.dens.calendarx.service;

import korolev.dens.calendarx.model.domain.CalendarMonth;
import korolev.dens.calendarx.model.domain.CalendarYear;
import org.springframework.stereotype.Service;

import java.time.Year;

// Maybe it is too redundant to create a separate class for just using CalendarFactory,
// but I think it will be more correctly to do it if we assume further development.
// For example if we add database, calling db in calendarFactory or in controller will be a bad practice.
@Service
public class CalendarService {

    private final CalendarFactory calendarFactory;

    public CalendarService(CalendarFactory calendarFactory) {
        this.calendarFactory = calendarFactory;
    }

    public CalendarYear getCalendar(int year) {
        // more complex business logic can be added here
        return calendarFactory.createCalendar(Year.of(year));
    }

    public CalendarMonth getYearMonth(int year, int month) {
        // more complex business logic can be added here
        return getCalendar(year).getMonth(month);
    }

}
