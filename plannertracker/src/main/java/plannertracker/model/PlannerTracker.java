package plannertracker.model;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * PlannerTracker
 */
public class PlannerTracker {
    private static final GregorianCalendar calendar = new GregorianCalendar();
    private Month currentMonth;
    private String tasks;

    public PlannerTracker() {
        // this.currentMonth = getCurrentMonth();
    }

    public static MonthName getCurrentMonth() {
        return MonthName.getMonthName(calendar.get(Calendar.MONTH));
    }

    public void addTask(String name) {
        currentMonth.addTask(name);
    }

    public void addHighlight(String highlight) {
        currentMonth.addHighlight(highlight);
    }

    public void reloadMonth() {
        File dir = new File("plannertracker/src/main/java/plannertracker/data");
    }
}