package korolev.dens.calendarx.mapper;

import korolev.dens.calendarx.dto.CalendarResponseDto;
import korolev.dens.calendarx.dto.DayDto;
import korolev.dens.calendarx.dto.MonthDto;
import korolev.dens.calendarx.model.domain.CalendarDay;
import korolev.dens.calendarx.model.domain.CalendarMonth;
import korolev.dens.calendarx.model.domain.CalendarYear;
import org.springframework.stereotype.Component;

import java.time.format.TextStyle;
import java.util.Locale;

@Component
public class CalendarMapper {

    public CalendarResponseDto toDto(CalendarYear calendar) {
        return new CalendarResponseDto(
                calendar.year(),
                calendar.isLeap(),
                calendar.length(),
                calendar.months().stream().map(this::toDto).toList()
        );
    }

    public MonthDto toDto(CalendarMonth month) {
        return new MonthDto(
                month.monthNumber(),
                month.name(),
                month.getDaysCount(),
                month.days().stream().map(this::toDto).toList()
        );
    }

    private DayDto toDto(CalendarDay day) {
        return new DayDto(
                day.dayOfMonth(),
                day.dayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()),
                day.isWeekend()
        );
    }
    
}
