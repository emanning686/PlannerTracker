package plannertracker.model;

import java.util.ArrayList;

import plannertracker.view.PlannerTrackerGUI;

public class PlannerTrackerChanger implements PlannerTrackerObserver {
    private PlannerTrackerGUI plannerTrackerGUI;

    public PlannerTrackerChanger(PlannerTrackerGUI plannerTrackerGUI) {
        this.plannerTrackerGUI = plannerTrackerGUI;
    }

    @Override
    public void updateScene(ArrayList<Task> taskList, Highlight[] highlightArray) {
        plannerTrackerGUI.updateScene();
    }
}
