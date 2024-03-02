package plannertracker.view;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import plannertracker.model.PlannerTracker;

/**
 * HighlightButton
 */
public class TaskButtonLabelHandler implements EventHandler<ActionEvent> {
    private PlannerTracker plannerTracker;
    private TextInputDialog td;

    public TaskButtonLabelHandler(PlannerTracker plannerTracker) {
        this.plannerTracker = plannerTracker;
        this.td = new TextInputDialog("");
        this.td.setHeaderText("");
        this.td.setTitle("New Task Entry");
    }

    @Override
    public void handle(ActionEvent event) {
        Optional<String> result = this.td.showAndWait();
        if (result.isPresent() && this.td.getEditor().getText() != "") {
            plannerTracker.addTask(this.td.getEditor().getText());
        }
    }
}