package korolev.dens.calendarx.model.domain;

import java.time.DayOfWeek;

/// Maybe now this class has too few business logic for a domain model, but it can be extended
/// for using more complex logic as adding custom holidays or using database.
public record CalendarDay(
        int dayOfMonth,
        DayOfWeek dayOfWeek
) {

    public boolean isWeekend() {
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

}
