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
    private final MetricsService metricsService;

    public CalendarService(CalendarFactory calendarFactory, MetricsService metricsService) {
        this.calendarFactory = calendarFactory;
        this.metricsService = metricsService;
    }

    public CalendarYear getCalendar(int year) {
        CalendarYear c = calendarFactory.createCalendar(Year.of(year));
        metricsService.setRequestsByLeapMetric(c.isLeap());
        metricsService.setRequestsByRangeMetric(c.getYear().getValue());
        return c;
    }

    public CalendarMonth getYearMonth(int year, int month) {
        // more complex business logic can be added here
        return getCalendar(year).getMonth(month);
    }

}
