import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class Main extends Application{

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        primaryStage.setTitle("Cellular automatons");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();


//        primaryStage.setTitle("Cellular automatons");
//        Button selectGameOfLifeButton = new Button("Game of Life");
//        Button selectLangtonAntButton = new Button("Langton Ant");
//        selectGameOfLifeButton.setOnAction(e -> {
//           VBox dataInput =new VBox();
//        });
//        selectLangtonAntButton.setOnAction(e -> System.out.println("LA"));
//        VBox menu = new VBox();
//        menu.getChildren().addAll(selectGameOfLifeButton, selectLangtonAntButton);
//        Scene scene = new Scene(menu, 1000, 600);
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }
}
