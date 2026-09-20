package korolev.dens.calendarx;

import korolev.dens.calendarx.dto.CalendarResponseDto;
import korolev.dens.calendarx.dto.DayDto;
import korolev.dens.calendarx.dto.MonthDto;
import korolev.dens.calendarx.mapper.CalendarMapper;
import korolev.dens.calendarx.model.domain.CalendarDay;
import korolev.dens.calendarx.model.domain.CalendarMonth;
import korolev.dens.calendarx.model.domain.CalendarYear;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class CalendarMapperTest {

    private CalendarMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new CalendarMapper();
    }

    @Test
    void toDto_shouldMapCalendarMetadata() {
        CalendarYear year = new CalendarYear(
                2024,
                List.of(new CalendarMonth(
                                Month.JANUARY,
                                List.of(new CalendarDay(2024, Month.JANUARY, 1))
                ))
        );
        CalendarResponseDto dto = mapper.toDto(year);
        assertThat(dto.year()).isEqualTo(2024);
        assertThat(dto.isLeap()).isTrue();
        assertThat(dto.daysCount()).isEqualTo(366);
        assertThat(dto.months()).hasSize(1);
    }

    @Test
    void toDto_shouldMapMonthsAndDays() {
        CalendarDay monday = new CalendarDay(2024, Month.JANUARY, 1);
        CalendarDay saturday = new CalendarDay(2024, Month.JANUARY, 6);
        CalendarMonth january = new CalendarMonth(Month.JANUARY, List.of(monday, saturday));
        CalendarYear year = new CalendarYear(2024, List.of(january));

        CalendarResponseDto dto = mapper.toDto(year);
        MonthDto monthDto = dto.months().getFirst();
        assertThat(monthDto.monthNumber()).isEqualTo(1);
        assertThat(monthDto.name()).isEqualTo(Month.JANUARY.getDisplayName(
                TextStyle.FULL,
                Locale.getDefault()
        ));
        assertThat(monthDto.daysCount()).isEqualTo(2);

        DayDto firstDay = monthDto.days().getFirst();
        assertThat(firstDay.dayOfMonth()).isEqualTo(1);
        assertThat(firstDay.isWeekend()).isFalse();
        assertThat(firstDay.dayOfWeek()).isEqualTo(DayOfWeek.MONDAY.getDisplayName(
                TextStyle.FULL,
                Locale.getDefault()
        ));
    }

}