package plannertracker.model;

/**
 * Weekday
 */
public enum Weekday {
    SUNDAY(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6);

    private int number;

    private Weekday(int number) {
        this.number = number;
    }

    public int getNumber() {return this.number;}

    public static Weekday getWeekday(int number) {
        if (number == 1) {
            return Weekday.SUNDAY;
        } else if (number == 2) {
            return Weekday.MONDAY;
        } else if (number == 3) {
            return Weekday.TUESDAY;
        } else if (number == 4) {
            return Weekday.WEDNESDAY;
        } else if (number == 5) {
            return Weekday.THURSDAY;
        } else if (number == 6) {
            return Weekday.FRIDAY;
        } else if (number == 7) {
            return Weekday.SATURDAY;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name().substring(0,1).toUpperCase() + this.name().substring(1).toLowerCase();
    }
}