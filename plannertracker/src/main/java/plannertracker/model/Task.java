package plannertracker.model;

/**
 * Task
 */
public class Task {
    private String name;
    private boolean completed[];

    public Task(int DAYS, String name) {
        this.name = name;
        this.completed = new boolean[DAYS];
    }

    public Task(String name, boolean completed[]) {
        this.name = name;
        this.completed = completed;
    }

    public String getName() {return this.name;}

    public boolean[] getCompleted() {return this.completed;}

    public void changeName(String name) {
        this.name = name;
    }

    public void toggleCompleted(int date) {
        if (completed[date - 1]) {
            completed[date - 1] = false;
        } else {
            completed[date - 1] = true;
        }
    }
}