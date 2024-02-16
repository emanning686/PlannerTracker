package plannertracker.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * PlannerTracker
 */
public class PlannerTracker {
    private static final GregorianCalendar calendar = new GregorianCalendar();
    private static Month currentMonth;

    public static MonthName getCurrentMonth() {
        int monthInt = calendar.get(Calendar.MONTH);
        
    }

    public static void main(String[] args) {
        System.out.println(calendar.get(Calendar.MONTH));
    }
}