package korolev.dens.calendarx.repository;

import korolev.dens.calendarx.model.CalendarType;
import korolev.dens.calendarx.model.template.CalendarTemplate;

import java.util.EnumMap;
import java.util.Map;

// This abstract class only for generating repositories, interface - for all repos.
/// Creates and stores N templates for every existing year type for some calendar.
/// Templates does not have information about the number of specific year.
/// Lazy initTemplates for now!
public abstract class GeneratingTemplateRepository implements CalendarTemplateRepository {

    protected final Map<CalendarType, CalendarTemplate> templates = new EnumMap<>(CalendarType.class);

    @Override
    public CalendarTemplate getTemplate(CalendarType type) {
        return templates.computeIfAbsent(type, this::generateTemplate);
    }

    protected abstract CalendarTemplate generateTemplate(CalendarType type);

}
