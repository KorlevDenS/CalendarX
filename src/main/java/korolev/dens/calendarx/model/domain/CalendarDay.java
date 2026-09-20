package korolev.dens.calendarx.model.domain;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/// Maybe now this class has too few business logic for a domain model, but it can be extended
/// for using more complex logic as adding custom holidays or using database.
@Getter
public class CalendarDay {

    private final LocalDate date;

    public CalendarDay(int year, Month month, int dayOfMonth) {
        this.date = LocalDate.of(year, month, dayOfMonth);
    }

    public DayOfWeek getDayOfWeek() {
        return date.getDayOfWeek();
    }

    public boolean isWeekend() {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

}
