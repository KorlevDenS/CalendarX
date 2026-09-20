package korolev.dens.calendarx.service;

import korolev.dens.calendarx.model.CalendarType;
import korolev.dens.calendarx.model.domain.CalendarDay;
import korolev.dens.calendarx.model.domain.CalendarMonth;
import korolev.dens.calendarx.model.domain.CalendarYear;
import korolev.dens.calendarx.model.template.CalendarTemplate;
import korolev.dens.calendarx.repository.CalendarTemplateRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class GregorianCalendarFactory implements CalendarFactory{

    private final CalendarTemplateRepository calendarTemplateRepository;

    public GregorianCalendarFactory(CalendarTemplateRepository calendarTemplateRepository) {
        this.calendarTemplateRepository = calendarTemplateRepository;
    }

    @Override
    public CalendarYear createCalendar(Year year) {
        CalendarType type = CalendarType.fromYear(year);
        CalendarTemplate template = calendarTemplateRepository.getTemplate(type);
        return fromTemplate(template, year);
    }

    private CalendarYear fromTemplate(CalendarTemplate template, Year year) {
        List<CalendarMonth> months = template.months().stream().map(tm -> new CalendarMonth(
                tm.month(),
                tm.days().stream().map(td -> new CalendarDay(
                        year.getValue(),
                        tm.month(),
                        td.dayOfMonth()
                )).toList()
        )).toList();
        return new CalendarYear(year.getValue(), months);
    }

}
