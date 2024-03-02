package plannertracker.model;

import java.util.ArrayList;

/**
 * PlannerTrackerObserver
 */
public interface PlannerTrackerObserver {
    void updateScene(ArrayList<Task> taskList, Highlight[] highlightArray);
}