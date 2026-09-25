package korolev.dens.calendarx.repository;

import korolev.dens.calendarx.model.CalendarType;
import korolev.dens.calendarx.model.template.CalendarTemplate;
import korolev.dens.calendarx.model.template.DayTemplate;
import korolev.dens.calendarx.model.template.MonthTemplate;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.ArrayList;

@Component
public class EthiopianCalendarTemplateRepository extends GeneratingTemplateRepository {

    @Override
    protected CalendarTemplate generateTemplate(CalendarType type) {
        CalendarTemplate ct = new CalendarTemplate(type, new ArrayList<>(13));
        int weekDayIdx = type.getStartDayOfWeek().getValue();

        for (int i = 1; i <= 12; i++) {
            MonthTemplate mt = new MonthTemplate(i, new ArrayList<>(30));
            for (int j = 1; j <= 30; j++) {
                DayOfWeek dow = DayOfWeek.of(weekDayIdx);
                mt.days().add(new DayTemplate(j, dow));
                weekDayIdx = weekDayIdx % 7 + 1;
            }
            ct.months().add(mt);
        }
        MonthTemplate mt = new MonthTemplate(13, new ArrayList<>());
        for (int i = 1; i <= (type.isLeap() ? 6 : 5); i++) {
            DayOfWeek dow = DayOfWeek.of(weekDayIdx);
            mt.days().add(new DayTemplate(i, dow));
            weekDayIdx = weekDayIdx % 7 + 1;
        }
        ct.months().add(mt);
        return ct;
    }

}
