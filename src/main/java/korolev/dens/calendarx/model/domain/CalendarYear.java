package korolev.dens.calendarx.model.domain;

import korolev.dens.calendarx.error.InvalidCalendarStateException;
import lombok.Getter;

import java.time.Year;
import java.util.List;

@Getter
public class CalendarYear {

    private final Year year;
    private final List<CalendarMonth> months;

    public CalendarYear(int year, List<CalendarMonth> months) {
        if (year < 1582 || year > 9999) {
            throw new InvalidCalendarStateException("Year %d seems to be unreal. Try to use years from 1582 to 9999"
                    .formatted(year)
            );
        } else if (months == null || months.isEmpty()) {
            throw new InvalidCalendarStateException("Year's months list is empty or null");
        }
        this.year = Year.of(year);
        this.months = months;
    }

    public boolean isLeap() {
        return year.isLeap();
    }

    public CalendarMonth getMonth(int monthNumber) {
        if (monthNumber < 1 || monthNumber > 12) {
            throw  new IllegalArgumentException("Month number must be between 1 and 12, but got %s"
                    .formatted(monthNumber)
            );
        }
        return months.get(monthNumber - 1);
    }

}
