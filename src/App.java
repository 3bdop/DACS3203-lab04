import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        //this.primaryStage = primaryStage;
        GitCommand go = new GitCommand(primaryStage);
        go.initializeComponents();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
