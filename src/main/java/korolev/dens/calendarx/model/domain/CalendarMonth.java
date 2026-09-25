package korolev.dens.calendarx.model.domain;

import java.util.List;

public record CalendarMonth(
        int monthNumber,
        String name,
        List<CalendarDay> days
) {

    public int getDaysCount() {
        return days.size();
    }

    public CalendarDay getDay(int dayOfMonth) {
        if (dayOfMonth < 1 || dayOfMonth > days.size()) {
            throw new IllegalArgumentException("Incorrect day of the month %s: %d"
                    .formatted(name, dayOfMonth)
            );
        }
        return days.get(dayOfMonth - 1);
    }

}
