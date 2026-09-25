package korolev.dens.calendarx.service;

import korolev.dens.calendarx.model.CalendarType;
import korolev.dens.calendarx.model.EthiopianMonth;
import korolev.dens.calendarx.model.domain.CalendarDay;
import korolev.dens.calendarx.model.domain.CalendarMonth;
import korolev.dens.calendarx.model.domain.CalendarYear;
import korolev.dens.calendarx.model.template.CalendarTemplate;
import korolev.dens.calendarx.repository.CalendarTemplateRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EthiopianCalendarFactory implements CalendarFactory{

    private final CalendarTemplateRepository calendarTemplateRepository;

    public EthiopianCalendarFactory(
            @Qualifier("ethiopianCalendarTemplateRepository")
            CalendarTemplateRepository calendarTemplateRepository
    ) {
        this.calendarTemplateRepository = calendarTemplateRepository;
    }

    private LocalDate getGregorianFirstYearDay(int ethiopianYear) {
        // Юлианский день (JDN) для 1 Мескерема 1 года Эфиопской эры = 1724221.
        long jdn = 1724221L + 365L * (ethiopianYear - 1) + (ethiopianYear - 1) / 4;
        // Перевод JDN в Unix Epoch Day (JDN 2440588 соответствует 1970-01-01)
        long epochDay = jdn - 2440588L;
        return LocalDate.ofEpochDay(epochDay);
    }

    @Override
    public CalendarYear createCalendar(int year) {
        boolean isLeap = year % 4 == 3;
        LocalDate gregorianFirstYearDay = getGregorianFirstYearDay(year);
        CalendarType type = CalendarType.fromYear(isLeap, gregorianFirstYearDay.getDayOfWeek());
        CalendarTemplate template = calendarTemplateRepository.getTemplate(type);
        return fromTemplate(template, year);
    }

    private CalendarYear fromTemplate(CalendarTemplate template, int year) {
        List<CalendarMonth> months = template.months().stream().map(tm -> new CalendarMonth(
                tm.monthNumber(),
                EthiopianMonth.of(tm.monthNumber()).getName(),
                tm.days().stream().map(td -> new CalendarDay(
                        td.dayOfMonth(),
                        td.dayOfWeek()
                )).toList()
        )).toList();
        return new CalendarYear(year, template.calendarType().isLeap(), months);
    }

}
