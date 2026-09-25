package korolev.dens.calendarx.service;

import korolev.dens.calendarx.model.domain.CalendarMonth;
import korolev.dens.calendarx.model.domain.CalendarYear;
import org.springframework.stereotype.Service;

import java.util.Map;

// Maybe it is too redundant to create a separate class for just using CalendarFactory,
// but I think it will be more correctly to do it if we assume further development.
// For example if we add database, calling db in calendarFactory or in controller will be a bad practice.
@Service
public class CalendarService {

    private final Map<String, CalendarFactory> calendarFactories;
    private final MetricsService metricsService;

    public CalendarService(Map<String, CalendarFactory> calendarFactories, MetricsService metricsService) {
        this.calendarFactories = calendarFactories;
        this.metricsService = metricsService;
    }

    public CalendarYear getGregorianCalendar(int year) {
        CalendarYear c = calendarFactories.get("gregorianCalendarFactory").createCalendar(year);
        metricsService.setRequestsByLeapMetric(c.isLeap());
        metricsService.setRequestsByRangeMetric(c.year());
        return c;
    }

    public CalendarMonth getGregorianYearMonth(int year, int month) {
        // more complex business logic can be added here
        return getGregorianCalendar(year).getMonth(month);
    }

    // Ethiopian for demonstration of extensibility

    public CalendarYear getEthiopianCalendar(int year) {
        CalendarYear c = calendarFactories.get("ethiopianCalendarFactory").createCalendar(year);
        metricsService.setRequestsByLeapMetric(c.isLeap());
        metricsService.setRequestsByRangeMetric(c.year());
        return c;
    }

    public CalendarMonth getEthiopianYearMonth(int year, int month) {
        return getEthiopianCalendar(year).getMonth(month);
    }

}
