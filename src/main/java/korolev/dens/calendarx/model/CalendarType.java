package korolev.dens.calendarx.model;

import korolev.dens.calendarx.error.InvalidCalendarStateException;
import lombok.Getter;

import java.time.DayOfWeek;

/// All 14 types of calendars.
public enum CalendarType {

    NON_LEAP_MONDAY(false, DayOfWeek.MONDAY),
    NON_LEAP_TUESDAY(false, DayOfWeek.TUESDAY),
    NON_LEAP_WEDNESDAY(false, DayOfWeek.WEDNESDAY),
    NON_LEAP_THURSDAY(false, DayOfWeek.THURSDAY),
    NON_LEAP_FRIDAY(false, DayOfWeek.FRIDAY),
    NON_LEAP_SATURDAY(false, DayOfWeek.SATURDAY),
    NON_LEAP_SUNDAY(false, DayOfWeek.SUNDAY),

    LEAP_MONDAY(true, DayOfWeek.MONDAY),
    LEAP_TUESDAY(true, DayOfWeek.TUESDAY),
    LEAP_WEDNESDAY(true, DayOfWeek.WEDNESDAY),
    LEAP_THURSDAY(true, DayOfWeek.THURSDAY),
    LEAP_FRIDAY(true, DayOfWeek.FRIDAY),
    LEAP_SATURDAY(true, DayOfWeek.SATURDAY),
    LEAP_SUNDAY(true, DayOfWeek.SUNDAY);

    @Getter
    private final boolean leap;
    @Getter
    private final DayOfWeek startDayOfWeek;

    CalendarType(boolean leap, DayOfWeek startDayOfWeek) {
        this.leap = leap;
        this.startDayOfWeek = startDayOfWeek;
    }

    public static CalendarType fromYear(boolean isLeap, DayOfWeek firstDay) {
        CalendarType[] vals = CalendarType.values();
        if (isLeap) {
            for (int i = vals.length / 2; i < vals.length; i++) {
                if (vals[i].startDayOfWeek == firstDay) {
                    return vals[i];
                }
            }
        } else {
            for (int i = 0; i < vals.length / 2; i++) {
                if (vals[i].startDayOfWeek == firstDay) {
                    return vals[i];
                }
            }
        }
        throw new InvalidCalendarStateException("Could not find existing calendar type for %s && %s"
                .formatted(isLeap, firstDay));
    }

}
