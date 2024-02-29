package plannertracker.model;

import java.util.ArrayList;
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
    private ArrayList<Task> taskList;
    private ArrayList<Highlight> highlightList;

    public static  Weekday getStartingWeekday() {
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return Weekday.getWeekday(dayOfWeek);
    }

    public Month(MonthName MONTH_NAME, int DAYS) {
        this.MONTH_NAME = MONTH_NAME;
        this.DAYS = DAYS;
        this.STARTING_WEEKDAY = getStartingWeekday();
        this.taskList = new ArrayList<>();
        this.highlightList = new ArrayList<>();
    }

    public Month(MonthName MONTH_NAME, int DAYS, ArrayList<Task> taskList, ArrayList<Highlight> highlightList) {
        this.MONTH_NAME = MONTH_NAME;
        this.DAYS = DAYS;
        this.STARTING_WEEKDAY = getStartingWeekday();
        this.taskList = taskList;
        this.highlightList = highlightList;
    }

    public void addTask(String name) {
        taskList.add(new Task(this.DAYS, name));
    }

    public void addHighlight(String highlight) {
        highlightList.add(new Highlight(this.DAYS, highlight));
    }

    public MonthName getMONTH_NAME() {return this.MONTH_NAME;}

    public int getDAYS() {return this.DAYS;}

    public Weekday getSTARTING_WEEKDAY() {return this.STARTING_WEEKDAY;}

    public ArrayList<Task> getTaskList() {return this.taskList;}
}