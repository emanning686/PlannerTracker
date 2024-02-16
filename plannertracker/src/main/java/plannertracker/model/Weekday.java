package plannertracker.model;

/**
 * Weekday
 */
public enum Weekday {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    public static Weekday getWeekday(int number) {
        return Weekday.values()[number - 1];
    }

    @Override
    public String toString() {
        return this.name().substring(0,1).toUpperCase() + this.name().substring(1).toLowerCase();
    }
}