import java.sql.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GitCommand {
    private Scene gitScene;
    private TextField idField = new TextField();
    private Stage stage;

    public GitCommand(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void initializeComponents() {
        VBox gitLayout = new VBox(10);
        gitLayout.setPadding(new Insets(10));
        Button showButton = new Button("Go!");

        showButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getDesc();
            }
        });

        gitLayout.getChildren().addAll(new Label("Command Number 1-5:"), idField, showButton
               );

        idField.setOnAction(event -> getDesc());

        gitScene = new Scene(gitLayout, 350, 150);
        stage.setTitle("Git Command");
        stage.setScene(gitScene);
        stage.show();
    }

    private void getDesc() {
        String id = idField.getText();
        Connection con = DBUtils.establishConnection();

        //* Secure query:
        String query = "SELECT * FROM git WHERE id =?;";

        try {
            /**/
            PreparedStatement statement = con.prepareStatement(query);

            statement.setString(1, id);

            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                // Retrieve the Git command and description from the ResultSet
                String gitCommand = rs.getString("command");
                String gitDescription = rs.getString("description");

                CommandInfo getCommandInfo = new CommandInfo(stage,gitCommand,gitDescription );
                getCommandInfo.initializeComponents();
            } else {
                showAlert("Incorrect Command ID", "Please choose a number from 1-5.");
            }
            DBUtils.closeConnection(con, statement);
        } catch (Exception e) {
            //We will still print the exception error in the console to help us in the development
            e.printStackTrace();
            //But we will remove the above line, and display an alert to the user when the app is deployed
            showAlert("Database Error", "Failed to connect to the database.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showResult(){
//        CommandInfo show = new CommandInfo()
    }

}
