package plannertracker.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PlannerTrackerGUI extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane boardPane = new GridPane();
        Label label = new Label("AHHHH");
        boardPane.add(label, 1, 1);

        primaryStage.setScene(new Scene(boardPane));
        primaryStage.setTitle("Reversi");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
