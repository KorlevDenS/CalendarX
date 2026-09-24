package korolev.dens.calendarx;

import korolev.dens.calendarx.model.domain.CalendarDay;
import korolev.dens.calendarx.model.domain.CalendarMonth;
import korolev.dens.calendarx.model.domain.CalendarYear;
import korolev.dens.calendarx.repository.GregorianCalendarTemplateRepository;
import korolev.dens.calendarx.service.GregorianCalendarFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class GregorianCalendarFactoryTest {

    private GregorianCalendarFactory factory;

    @BeforeEach
    void setUp() {
        GregorianCalendarTemplateRepository repository = new GregorianCalendarTemplateRepository();
        factory = new GregorianCalendarFactory(repository);
    }

    @ParameterizedTest(name = "Validate calendar for year {0}")
    @ValueSource(ints = {
            2023, // обычный
            2024, // високосный
            2025, // обычный
            2026, // обычный
            2000, // високосный (делится на 400)
            1900  // невисокосный (делится на 100, но не на 400)
    })
    void createCalendar_shouldMatchJavaLocalDateForEveryDayOfYear(int yearValue) {
        CalendarYear calendar = factory.createCalendar(Year.of(yearValue));

        assertThat(calendar.isLeap()).isEqualTo(Year.of(yearValue).isLeap());
        assertThat(calendar.getYear().length()).isEqualTo(Year.of(yearValue).length());

        LocalDate date = LocalDate.of(yearValue, 1, 1);
        LocalDate lastDate = LocalDate.of(yearValue, 12, 31);

        while (!date.isAfter(lastDate)) {
            CalendarMonth month = calendar.getMonth(date.getMonthValue());
            CalendarDay day = month.getDay(date.getDayOfMonth());
            assertThat(day.getDate()).isEqualTo(date);
            assertThat(day.getDayOfWeek()).isEqualTo(date.getDayOfWeek());

            boolean expectedWeekend = date.getDayOfWeek() == DayOfWeek.SATURDAY
                    || date.getDayOfWeek() == DayOfWeek.SUNDAY;
            assertThat(day.isWeekend()).isEqualTo(expectedWeekend);
            date = date.plusDays(1);
        }
    }
}