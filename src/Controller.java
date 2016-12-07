import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Map;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Automaton automaton;
    private int width;
    private int height;
    private int radius;
    private CellStateFactory factory;
    private Map<CellCoordinates, CellState> map;
    private CellNeighborhood neighborsStrategy;
    private GameOfLifeRules golRules;
    private boolean wrapping = false;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setScene(Scene scene){
        this.scene = scene;
    }

    public void gameOfLifeButton(){

        //automaton = new GameOfLife(this.width, this.height, )

        //1.tworzy factory
        //2. tworzy automaton
        //3. insert structure
    }

    public void submitGameOfLifeRulesButton(){
        this.golRules = new GameOfLifeRules(getTextFromTextField("#remainsAliveField"),
                getTextFromTextField("#bornsField"));
    }

    private String getTextFromTextField(String fieldId){
        return (((TextField)scene.lookup(fieldId)).getText());
    }

    public void submitButton(){
        this.width = Integer.parseInt(getTextFromTextField("#widthField"));
        this.height = Integer.parseInt(getTextFromTextField("#heightField"));
        this.radius = Integer.parseInt(getTextFromTextField("#radiusField"));
    }

    public void moorNeighborhoodButton(){
        this.neighborsStrategy = new MoorNeighborhood(this.width, this.height, this.wrapping, this.radius);
    }

    public void wrappingButton(){
        if(this.wrapping == false)
            this.wrapping = true;
        else
            this.wrapping = false;
    }

    public void initializeBoardButton(){
        //tworzy planszę na której można wybrać komórki startowe
        
        HBox boardHbox = (HBox) scene.lookup("#boardHbox");
        Button button = new Button("abc");
        boardHbox.getChildren().addAll(button);
    }

    public void acceptStartingBoardButton(){
        //akceptuje wybrane komórki startowe
        //inicjalizuje map

    }

    public void printIfClicked(){
        System.out.println("click");
    }
}
