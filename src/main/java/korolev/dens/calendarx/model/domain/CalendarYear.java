package korolev.dens.calendarx.model.domain;

import java.util.List;

public record CalendarYear(
        int year,
        boolean isLeap,
        List<CalendarMonth> months
) {

    /// length of year in days
    public int length() {
        return months.stream().mapToInt(CalendarMonth::getDaysCount).sum();
    }

    public CalendarMonth getMonth(int monthNumber) {
        if (monthNumber < 1 || monthNumber > months.size()) {
            throw  new IllegalArgumentException("Month number must be between 1 and %d, but got %d"
                    .formatted(months.size(), monthNumber)
            );
        }
        return months.get(monthNumber - 1);
    }

}
