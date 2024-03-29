package plannertracker.view;

import java.util.Optional;

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
        this.td = new TextInputDialog("");
        this.td.setHeaderText("");
        this.td.setTitle("New Highlight Entry");
    }

    @Override
    public void handle(ActionEvent event) {
        Optional<String> result = this.td.showAndWait();
        if (result.isPresent() && this.td.getEditor().getText() != "") {
            plannerTracker.setHighlightMessage(this.td.getEditor().getText(), this.date);
        }
    }
}