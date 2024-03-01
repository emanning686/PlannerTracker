package plannertracker.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * PlannerTracker
 */
public class PlannerTracker {
    private static final GregorianCalendar CALENDAR = new GregorianCalendar();
    private static final String dataDir = "plannertracker/src/main/java/plannertracker/data/";
    private Month currentMonth;
    private ArrayList<String> tasks;

    public PlannerTracker() throws IOException {
        this.currentMonth = this.reloadMonth();
        this.tasks = new ArrayList<>();
        this.tasks.add("new task");
        this.tasks.add("hello mario");
        this.tasks.add("3");
        this.tasks.add("RAHHHHH");
        this.tasks.add("hi");
    }

    public static MonthName getCurrentMonth() {
        return MonthName.getMonthName(PlannerTracker.CALENDAR.get(Calendar.MONTH));
    }

    public static int getCurrentYear() {
        return PlannerTracker.CALENDAR.get(Calendar.YEAR);
    }

    public void addTask(String name) {
        currentMonth.addTask(name);
    }

    public void setHighlight(int day, String message) {
        currentMonth.setHighlight(day, message);
    }

    public Month importMonth(String fileName) {
        File monthFileHighlights = new File(PlannerTracker.dataDir + fileName + "_HIGHLIGHTS.csv");
        File monthFileTasks = new File(PlannerTracker.dataDir + fileName + "_TASKS.csv");
        try (FileReader highlightsFileReader = new FileReader(monthFileHighlights);
        CSVReader highlightsReader = new CSVReader(highlightsFileReader);
        FileReader tasksFileReader = new FileReader(monthFileTasks);
        CSVReader tasksReader = new CSVReader(tasksFileReader)) {
            int daysInMonth = highlightsReader.readNext().length;
            String highlightsStrings[] = highlightsReader.readNext();
            Highlight highlightsArray[] = new Highlight[daysInMonth];
            for (int i = 0; i < daysInMonth; i++) {
                highlightsArray[i] = new Highlight(highlightsStrings[i]);
            }
            String taskNames[] = tasksReader.readNext();
            int numTasks = taskNames.length;
            boolean tasksCompleted[][] = new boolean[numTasks][daysInMonth];
            for (int i = 0; i < daysInMonth; i++) {
                String completedArray[] = tasksReader.readNext();
                for (int j = 0; j < numTasks; j++) {
                    if (completedArray[j].charAt(0) == 'O') {
                        tasksCompleted[j][i] = false;
                    } else {
                        tasksCompleted[j][i] = true;
                    }
                }
            }
            ArrayList<Task> tasksList = new ArrayList<>();
            for (int i = 0; i < numTasks; i++) {
                tasksList.add(new Task(taskNames[i], tasksCompleted[i]));
            }
            return new Month(PlannerTracker.getCurrentMonth(), PlannerTracker.getCurrentYear(), tasksList, highlightsArray);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            return null;
        }
    }
    
    public Month createMonth(String fileName) throws IOException {
        File monthFileHighlights = new File(PlannerTracker.dataDir + fileName + "_HIGHLIGHTS.csv");
        monthFileHighlights.createNewFile();
        File monthFileTasks = new File(PlannerTracker.dataDir + fileName + "_TASKS.csv");
        monthFileTasks.createNewFile();

        int daysInMonth = CALENDAR.getActualMaximum(Calendar.DATE);

        String highlightsHeader[] = new String[daysInMonth];
        String highlights[] = new String[daysInMonth];
        for (int i = 0; i < daysInMonth; i++) {
            highlightsHeader[i] = "day" + (i + 1);
            highlights[i] = "";
        }

        int numTasks = this.tasks.size();

        String tasksHeader[] = new String[numTasks];
        String tasksCompleted[] = new String[numTasks];
        for (int i = 0; i < numTasks; i++) {
            tasksHeader[i] = tasks.get(i);
            tasksCompleted[i] = "O";
        }

        try (FileWriter highlightsFileWriter = new FileWriter(monthFileHighlights);
        CSVWriter highlightsWriter = new CSVWriter(highlightsFileWriter);
        FileWriter tasksFileWriter = new FileWriter(monthFileTasks);
        CSVWriter tasksWriter = new CSVWriter(tasksFileWriter)) {
            highlightsWriter.writeNext(highlightsHeader);
            highlightsWriter.writeNext(highlights);
            tasksWriter.writeNext(tasksHeader);
            for (int i = 0; i < daysInMonth; i++) {
                tasksWriter.writeNext(tasksCompleted);
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return importMonth(fileName);
    }

    public Month reloadMonth() throws IOException {
        String fileName = PlannerTracker.getCurrentMonth().name() + PlannerTracker.getCurrentYear();
        File dir = new File(PlannerTracker.dataDir);
        String contents[] = dir.list();
        for (String file : contents) {
            if (file.equals(fileName + "_HIGHLIGHTS.csv")) {
                return importMonth(fileName);
            }
        }
        return createMonth(fileName);
    }

    public static void main(String[] args) throws IOException {
        // TESTS
        GregorianCalendar CALENDAR = new GregorianCalendar();
        PlannerTracker test = new PlannerTracker();
        System.out.println(PlannerTracker.getCurrentYear());
        System.out.println(PlannerTracker.getCurrentMonth());
        System.out.println(CALENDAR.getActualMaximum(Calendar.DATE));
    }
}