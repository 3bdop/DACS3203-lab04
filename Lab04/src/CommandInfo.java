import java.sql.*;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CommandInfo {
    private Scene commandInfoScene;
    private Stage stage;
    private String gitCommand;
    private String gitDescription;

    public CommandInfo(Stage primaryStage, String gitCommand, String gitDescription){
        this.stage = primaryStage;
        this.gitCommand = gitCommand;
        this.gitDescription = gitDescription;
    }

    public void initializeComponents() {
        VBox commandInfoLayout = new VBox(10);
        commandInfoLayout.setPadding(new Insets(10));
        Button backButton = new Button("Go Back");

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
              goBack();
            }
        });
        Text commandText = new Text("Git Command: " + gitCommand);
        commandText.setStyle("-fx-font-weight: bold;");

        Text descriptionText = new Text("Description: " + gitDescription);
        descriptionText.setWrappingWidth(300);

        descriptionText.setFill(Color.DARKBLUE);

        commandInfoLayout.getChildren().addAll(commandText, descriptionText, backButton);

        commandInfoScene = new Scene(commandInfoLayout, 350, 150);
        stage.setTitle("Command Description");
        stage.setScene(commandInfoScene);
        stage.show();
    }

    private void goBack(){
        GitCommand back = new GitCommand(stage);
        back.initializeComponents();
    }
}
