package korolev.dens.calendarx.repository;

import korolev.dens.calendarx.model.template.CalendarTemplate;
import korolev.dens.calendarx.model.CalendarType;
import korolev.dens.calendarx.model.template.DayTemplate;
import korolev.dens.calendarx.model.template.MonthTemplate;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

/// Creates and stores 14 templates for every existing year type.
/// Templates does not have information about the number of specific year.
/// Lazy initTemplates for now!
@Component
public class GregorianCalendarTemplateRepository implements CalendarTemplateRepository{

    private final Map<CalendarType, CalendarTemplate> templates = new EnumMap<>(CalendarType.class);

    public CalendarTemplate getTemplate(CalendarType type) {
        return templates.computeIfAbsent(type, this::generateTemplate);
    }

    private CalendarTemplate generateTemplate(CalendarType type) {
        CalendarTemplate ct = new CalendarTemplate(type, new ArrayList<>(12));
        int weekDayIdx = type.getStartDayOfWeek().getValue();
        for (int i = 1; i <= 12; i++) {
            MonthTemplate mt = new MonthTemplate(Month.of(i), new ArrayList<>());
            for (int j = 1; j <= mt.month().length(type.isLeap()); j++) {
                DayOfWeek dow = DayOfWeek.of(weekDayIdx);
                mt.days().add(new DayTemplate(j, dow));
                weekDayIdx = weekDayIdx % 7 + 1;
            }
            ct.months().add(mt);
        }
        return ct;
    }

}
