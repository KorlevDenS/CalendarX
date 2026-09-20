package korolev.dens.calendarx.model.domain;

import korolev.dens.calendarx.error.InvalidCalendarStateException;
import lombok.Getter;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Getter
public class CalendarMonth {

    private final Month month;
    private final List<CalendarDay> days;

    public CalendarMonth(Month month, List<CalendarDay> days) {
        if (days == null || days.isEmpty())  {
            throw new InvalidCalendarStateException("Month's days list is empty or null");
        }
        this.month = month;
        this.days = List.copyOf(days);
    }

    public int getDaysCount() {
        return days.size();
    }

    public CalendarDay getDay(int dayOfMonth) {
        if (dayOfMonth < 1 || dayOfMonth > days.size()) {
            throw new IllegalArgumentException("Incorrect day of the month %s: %d"
                    .formatted(month.getDisplayName(TextStyle.FULL, Locale.getDefault()), dayOfMonth)
            );
        }
        return days.get(dayOfMonth - 1);
    }

}
