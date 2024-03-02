package plannertracker.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import plannertracker.model.PlannerTracker;

/**
 * HighlightButton
 */
public class TaskButtonHandler implements EventHandler<ActionEvent> {
    private PlannerTracker plannerTracker;
    private int index;
    private int date;

    public TaskButtonHandler(PlannerTracker plannerTracker, int index, int date) {
        this.plannerTracker = plannerTracker;
        this.index = index;
        this.date = date;
    }

    @Override
    public void handle(ActionEvent event) {
        plannerTracker.toggleTaskDate(this.index, this.date);
    }
}