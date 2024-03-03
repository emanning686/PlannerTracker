package plannertracker.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Month
 */
public class Month {
    private static final GregorianCalendar CALENDAR = new GregorianCalendar();
    private final MonthName MONTH_NAME;
    private final int DAYS;
    private final int YEAR;
    private ArrayList<Task> taskList;
    private Highlight[] highlightArray;

    public int getStartingWeekday() {
        CALENDAR.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfWeek = CALENDAR.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }

    public Month(MonthName MONTH_NAME, int YEAR, ArrayList<Task> taskList, Highlight[] highlightArray) {
        this.MONTH_NAME = MONTH_NAME;
        this.DAYS = CALENDAR.getActualMaximum(Calendar.DATE);
        this.YEAR = YEAR;
        this.taskList = taskList;
        this.highlightArray = highlightArray;
    }

    public void addTask(String name) {
        taskList.add(new Task(this.DAYS, name));
    }

    public void removeTask(int index) {
        this.taskList.remove(index);
    }

    public void toggleTaskDate(int index, int date) {
        taskList.get(index).toggleCompleted(date);
    }

    public void setHighlightMessage(String message, int date) {
        this.highlightArray[date - 1].setMessage(message);
    }

    public MonthName getMONTH_NAME() {return this.MONTH_NAME;}

    public int getDAYS() {return this.DAYS;}

    public int getYEAR() {return this.YEAR;}

    public ArrayList<Task> getTaskList() {return this.taskList;}

    public Highlight[] getHighlightArray() {return this.highlightArray;}
}