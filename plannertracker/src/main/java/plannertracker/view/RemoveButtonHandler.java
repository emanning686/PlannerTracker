package plannertracker.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import plannertracker.model.PlannerTracker;

/**
 * RemoveButtonHandler
 */
public class RemoveButtonHandler implements EventHandler<ActionEvent> {
    private PlannerTracker plannerTracker;
    private int index;
    private int date;

    public RemoveButtonHandler(PlannerTracker plannerTracker, int index) {
        this.plannerTracker = plannerTracker;
        this.index = index;
    }

    @Override
    public void handle(ActionEvent event) {
        plannerTracker.removeTask(this.index);
    }
}