package plannertracker.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import plannertracker.model.PlannerTracker;

/**
 * HighlightButton
 */
public class HighlightButtonHandler implements EventHandler<ActionEvent> {
    private PlannerTracker plannerTracker;
    private int date;
    private TextInputDialog td;

    public HighlightButtonHandler(PlannerTracker plannerTracker, int date) {
        this.plannerTracker = plannerTracker;
        this.date = date;
        this.td = new TextInputDialog("Enter your daily highlight");
    }

    @Override
    public void handle(ActionEvent event) {
        td.showAndWait();
        plannerTracker.setHighlightMessage(td.getEditor().getText(), this.date);
    }
}