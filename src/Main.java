import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application{

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GUIMenu.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Controller controller = (Controller)fxmlLoader.getController();
        controller.setStage(primaryStage);
        primaryStage.setTitle("Cellular automatons");
        Scene scene = new Scene(root, 1000, 600);
        controller.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
