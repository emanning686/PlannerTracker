package plannertracker.view;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    private ArrayList<Button[]> taskButtonArray;
    private GridPane taskGridPane;
    private HBox taskLabelHBox;
    private VBox taskVBox;
    private String weekdays[] = {"S", "M", "T", "W", "T", "F", "S"};

    @Override
    public void start(Stage primaryStage) throws Exception {
        plannerTracker = new PlannerTracker(new PlannerTrackerChanger(this));
        daysInMonth = PlannerTracker.getDaysInMonth();

        ScrollPane scrollRoot = new ScrollPane();
        GridPane root = new GridPane();
        GridPane highlightGridPane = new GridPane();
        taskGridPane = new GridPane();
        taskGridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        taskVBox = new VBox();

        highlightButtonArray = new Button[daysInMonth];
        highlightPaneArray = new ScrollPane[daysInMonth];
        for (int i = 0; i < daysInMonth; i++) {
            Button button = makeHighlightButton(i + 1);
            highlightButtonArray[i] = button;
            ScrollPane highlightPane = makeHighlightPane("");
            highlightPaneArray[i] = highlightPane;
            highlightGridPane.add(button, 0, i + 1);
            highlightGridPane.add(highlightPane, 1, i + 1);
        }
        Label label = new Label(PlannerTracker.getCurrentMonth().toString());

        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setPercentWidth(5.0);
        cc1.setHgrow(Priority.ALWAYS);
        highlightGridPane.getColumnConstraints().add(cc1);
        ColumnConstraints cc2 = new ColumnConstraints();
        cc2.setPercentWidth(95.0);
        cc2.setHgrow(Priority.ALWAYS);
        highlightGridPane.getColumnConstraints().add(cc2);

        label.setFont(new Font("Courier New", 70));
        label.setTextFill(Color.INDIGO);
        label.setAlignment(Pos.CENTER);
        highlightGridPane.add(label, 1, 0);

        updateScene();

        HBox.setHgrow(highlightGridPane, Priority.ALWAYS);
        HBox.setHgrow(taskGridPane, Priority.ALWAYS);
        highlightGridPane.setMaxWidth(Double.MAX_VALUE);
        taskGridPane.setMaxWidth(Double.MAX_VALUE);

        taskVBox.getChildren().addAll(taskGridPane);

        root.add(highlightGridPane, 0, 0);
        root.add(taskGridPane, 1, 0);

        scrollRoot.setFitToWidth(true);
        scrollRoot.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollRoot.setContent(root);

        cc1 = new ColumnConstraints();
        cc1.setPercentWidth(50.0);
        cc1.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().add(cc1);
        cc2 = new ColumnConstraints();
        cc2.setPercentWidth(50.0);
        cc2.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().add(cc2);

        Scene scene = new Scene(scrollRoot, 1000, 1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Planner Tracker");
        primaryStage.show();
    }

    public void updateScene() {
        taskGridPane.getChildren().clear();
        taskGridPane.getColumnConstraints().clear();
        taskGridPane.getRowConstraints().clear();
        for(int row = 0; row < daysInMonth + 2; row++) {
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

        taskButtonArray = new ArrayList<>();
        taskGridPane.add(makeNewTaskButton(), 0, 0);
        for (int i = 0; i < plannerTracker.getTasks().size(); i++) {
            String taskName = plannerTracker.getTasks().get(i);
            Label taskLabel = makeTaskLabel(taskName);
            Button remove = makeTaskLabelRemove(i);
            taskLabelHBox = new HBox();
            taskLabelHBox.getChildren().addAll(taskLabel, remove);
            taskGridPane.add(taskLabelHBox, i, 1);
            Button buttonArray[] = new Button[daysInMonth];
            for (int j = 0; j < daysInMonth; j++) {
                boolean completed = taskList.get(i).getCompleted()[j];
                Button taskButton = makeTaskButton(i, j + 1);
                if (completed) {
                    taskButton.setText("X");
                } else {
                    taskButton.setText("");
                }
                buttonArray[j] = taskButton;
                taskGridPane.add(taskButton, i, j + 2);
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

    private Button makeNewTaskButton() {
        Button button = new Button("New Task");

        button.setFont(new Font("Courier New", 15));
        button.setTextFill(Color.INDIGO);
        button.setAlignment(Pos.CENTER);
        button.setOnAction(new TaskButtonLabelHandler(plannerTracker));

        return button ;
    }

    private Label makeTaskLabel(String text) {
        Label label = new Label(text);

        label.setFont(new Font("Courier New", 20));
        label.setTextFill(Color.INDIGO);
        label.setAlignment(Pos.TOP_RIGHT);

        return label ;
    }

    private Button makeTaskLabelRemove(int index) {
        Button remove = new Button("REMOVE");

        remove.setFont(new Font("Courier New", 15));
        remove.setTextFill(Color.ORANGERED);
        remove.setAlignment(Pos.CENTER);
        remove.setOnAction(new RemoveButtonHandler(plannerTracker, index));

        return remove;
    }

    private Button makeTaskButton(int index, int date) {
        Button button = new Button();

        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setOnAction(new TaskButtonHandler(plannerTracker, index, date));
        return button;
    }

    private Button makeHighlightButton(int date) {
        Button button = new Button(weekdays[(plannerTracker.getStartingWeekday() + date - 2) % 7] + " " + Integer.toString(date));
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setOnAction(new HighlightButtonHandler(plannerTracker, date));
        if (date == plannerTracker.getDayOfMonth()) {
            button.setTextFill(Color.DARKTURQUOISE);
        }
        
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
