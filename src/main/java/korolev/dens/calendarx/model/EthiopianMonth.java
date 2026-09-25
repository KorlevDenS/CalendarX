package korolev.dens.calendarx.model;

import korolev.dens.calendarx.error.InvalidCalendarStateException;
import lombok.Getter;

public enum EthiopianMonth {
    MESKEREM(1, "Мескерем"),
    TIKIMT(2, "Тикимт"),
    HIDAR(3, "Хидар"),
    TAHSAS(4, "Тахсас"),
    TIR(5, "Тир"),
    YAKATIT(6, "Якатит"),
    MAGABIT(7, "Магабит"),
    MIYAZYA(8, "Миязия"),
    GINBOT(9, "Гинбот"),
    SENE(10, "Сене"),
    HAMLE(11, "Хамле"),
    NAHASE(12, "Нехасе"),
    PAGUME(13, "Пагумен");

    @Getter
    private final int number;
    @Getter
    private final String name;

    EthiopianMonth(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public static EthiopianMonth of(int monthNumber) {
        if (monthNumber < 1 || monthNumber > 13) {
            throw new InvalidCalendarStateException("Number of Ethiopian month must be between 1 and 13, got: " + monthNumber);
        }
        return values()[monthNumber - 1];
    }
}
