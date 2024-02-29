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
    private MonthName currentMonthName;
    private int currentYear;
    private String tasks;

    public PlannerTracker() {
        this.currentMonth = reloadMonth();
        this.currentMonthName = getCurrentMonth();
        this.currentYear = getCurrentYear();
    }

    public static MonthName getCurrentMonth() {
        return MonthName.getMonthName(calendar.get(Calendar.MONTH));
    }

    public static int getCurrentYear() {
        return calendar.get(Calendar.YEAR);
    }

    public void addTask(String name) {
        currentMonth.addTask(name);
    }

    public void addHighlight(String highlight) {
        currentMonth.addHighlight(highlight);
    }

    public Month importMonth(String fileName) {
        ;
    }

    public Month createMonth(String fileName) {
        ;
    }

    public Month reloadMonth() {
        String fileName = this.currentMonthName.name() + this.currentYear + ".csv";
        File dir = new File("plannertracker/src/main/java/plannertracker/data");
        String contents[] = dir.list();
        for (String file : contents) {
            if (file == fileName) {
                return importMonth(fileName);
            }
        }
        return createMonth(fileName);
    }

    public static void main(String[] args) {
        // TESTS
        System.out.println(calendar.get(Calendar.YEAR));
    }
}