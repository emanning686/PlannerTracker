package plannertracker.view;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import plannertracker.model.Highlight;
import plannertracker.model.PlannerTracker;
import plannertracker.model.PlannerTrackerChanger;
import plannertracker.model.Task;

public class PlannerTrackerGUI extends Application{
    private PlannerTracker plannerTracker;
    private ArrayList<Task> taskList;
    private Highlight[] highlightArray;
    private int daysInMonth;
    private Button highlightButtonArray[];
    private ScrollPane highlightPaneArray[];
    private ArrayList<Label> taskLabelArray;
    private ArrayList<Button[]> taskButtonArray;
    private GridPane taskGridPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        plannerTracker = new PlannerTracker(new PlannerTrackerChanger(this));
        daysInMonth = PlannerTracker.getDaysInMonth();

        GridPane root = new GridPane();
        GridPane highlightGridPane = new GridPane();
        taskGridPane = new GridPane();

        highlightButtonArray = new Button[daysInMonth];
        highlightPaneArray = new ScrollPane[daysInMonth];
        for (int i = 0; i < daysInMonth; i++) {
            Button button = makeHighlightButton(i + 1);
            highlightButtonArray[i] = button;
            ScrollPane highlightPane = makeHighlightPane("");
            highlightPaneArray[i] = highlightPane;
            highlightGridPane.add(button, 0, i);
            highlightGridPane.add(highlightPane, 1, i);
        }

        updateScene();

        HBox.setHgrow(highlightGridPane, Priority.ALWAYS);
        HBox.setHgrow(taskGridPane, Priority.ALWAYS);
        highlightGridPane.setMaxWidth(Double.MAX_VALUE);
        taskGridPane.setMaxWidth(Double.MAX_VALUE);

        root.add(highlightGridPane, 0, 0);
        root.add(taskGridPane, 1, 0);

        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setPercentWidth(50.0);
        cc1.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().add(cc1);
        ColumnConstraints cc2 = new ColumnConstraints();
        cc2.setPercentWidth(50.0);
        cc2.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().add(cc2);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Planner Tracker");
        primaryStage.show();
    }

    public void updateScene() {
        taskGridPane.getChildren().clear();
        for(int row = 0; row < daysInMonth + 1; row++) {
            taskGridPane.getRowConstraints().add( 
                    new RowConstraints(Region.USE_COMPUTED_SIZE, 
                            Region.USE_COMPUTED_SIZE, 
                            Region.USE_COMPUTED_SIZE, 
                            Priority.SOMETIMES, 
                            VPos.TOP, 
                            true));
        }
        for(int row = 0; row < plannerTracker.getTasks().size(); row++) {
            ColumnConstraints cc2 = new ColumnConstraints();
            cc2.setPercentWidth(100 / plannerTracker.getTasks().size());
            cc2.setHgrow(Priority.ALWAYS);
            taskGridPane.getColumnConstraints().add(cc2);
        }
        taskList = plannerTracker.getMonth().getTaskList();
        highlightArray = plannerTracker.getMonth().getHighlightArray();
        for (int i = 0; i < daysInMonth; i++) {
            Label label = new Label(highlightArray[i].getHighlight());
            label.setWrapText(true);
            label.setTextAlignment(TextAlignment.JUSTIFY);
            highlightPaneArray[i].setContent(label);
        }

        taskLabelArray = new ArrayList<>();
        taskButtonArray = new ArrayList<>();
        for (int i = 0; i < plannerTracker.getTasks().size(); i++) {
            String taskName = plannerTracker.getTasks().get(i);
            Label taskLabel = makeTaskLabel(taskName);
            taskLabelArray.add(makeTaskLabel(taskName));
            taskGridPane.add(taskLabel, i, 0);
            Button buttonArray[] = new Button[daysInMonth];
            for (int j = 0; j < daysInMonth; j++) {
                boolean completed = taskList.get(i).getCompleted()[j];
                Button taskButton = makeTaskButton(i, j + 1);
                if (completed) {
                    taskButton.setText("X");
                } else {
                    taskButton.setText("O");
                }
                buttonArray[j] = taskButton;
                taskGridPane.add(taskButton, i, j + 1);
            }
            taskButtonArray.add(buttonArray);
        }
    }

    private ScrollPane makeHighlightPane(String string) {
        ScrollPane HighlightPane = new ScrollPane();
        Label label = new Label(string);
        HighlightPane.setFitToWidth(true);
        HighlightPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        HighlightPane.setContent(label);

        return HighlightPane;
    }

    private Label makeTaskLabel(String text) {
        Label label = new Label(text);

        return label;
    }

    private Button makeTaskButton(int index, int date) {
        Button button = new Button();
        button.setMaxSize(200d, Double.MAX_VALUE);
        button.setOnAction(new TaskButtonHandler(plannerTracker, index, date));
        return button;
    }

    private Button makeHighlightButton(int date) {
        Button button = new Button(Integer.toString(date));
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setOnAction(new HighlightButtonHandler(plannerTracker, date));
        
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
