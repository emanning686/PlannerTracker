package plannertracker.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Month
 */
public class Month {
    private static final GregorianCalendar calendar = new GregorianCalendar();
    private final MonthName MONTH_NAME;
    private final int DAYS;
    private final Weekday STARTING_WEEKDAY;
    private Task taskArray[];

    public static  Weekday getStartingWeekday() {
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return Weekday.getWeekday(dayOfWeek);
    }

    public Month(MonthName MONTH_NAME, int DAYS) {
        this.MONTH_NAME = MONTH_NAME;
        this.DAYS = DAYS;
        this.STARTING_WEEKDAY = getStartingWeekday();
        this.taskArray = new Task[1];
    }

    public Month(MonthName MONTH_NAME, int DAYS, Task taskArray[]) {
        this.MONTH_NAME = MONTH_NAME;
        this.DAYS = DAYS;
        this.STARTING_WEEKDAY = getStartingWeekday();
        this.taskArray = taskArray;
    }

    public MonthName getMONTH_NAME() {return this.MONTH_NAME;}

    public int getDAYS() {return this.DAYS;}

    public Weekday getSTARTING_WEEKDAY() {return this.STARTING_WEEKDAY;}

    public Task[] getTaskArray() {return this.taskArray;}
}