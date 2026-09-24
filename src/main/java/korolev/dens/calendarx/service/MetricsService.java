package korolev.dens.calendarx.service;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class MetricsService {

    private final MeterRegistry meterRegistry;

    public MetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void setRequestsByLeapMetric(boolean isLeap) {
        meterRegistry.counter(
                "calendar_requests_by_leap_year_total",
                "is_leap",
                String.valueOf(isLeap)
        ).increment();
    }

    public void setRequestsByRangeMetric(int year) {
        int currentYear = Year.now().getValue();
        int diff = year - currentYear;
        String range;
        if (diff == 0) {
            range = "current";
        } else if (diff >= -5 && diff < 0) {
            range = "recent_past";
        } else if (diff > 0 && diff <= 5) {
            range = "near_future";
        } else if (diff < -5) {
            range = "distant_past";
        } else {
            range = "distant_future";
        }
        meterRegistry.counter(
                "calendar_requests_by_range_total",
                "range",
                range
        ).increment();
    }

}

