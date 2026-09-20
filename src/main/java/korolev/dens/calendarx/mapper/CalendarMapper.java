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
                calendar.getYear().getValue(),
                calendar.isLeap(),
                calendar.getYear().length(),
                calendar.getMonths().stream().map(this::toDto).toList()
        );
    }

    private MonthDto toDto(CalendarMonth month) {
        return new MonthDto(
                month.getMonth().getValue(),
                month.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()),
                month.getDaysCount(),
                month.getDays().stream().map(this::toDto).toList()
        );
    }

    private DayDto toDto(CalendarDay day) {
        return new DayDto(
                day.getDate().getDayOfMonth(),
                day.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()),
                day.isWeekend()
        );
    }
    
}
