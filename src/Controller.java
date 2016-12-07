import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;
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
    private GUIBoard guiBoard;

    public Controller(){
        this.map = new HashMap<>();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setScene(Scene scene){
        this.scene = scene;
    }

    public void gameOfLifeButton(){
        CellStateFactory factory = new GeneralStateFactory(map);
        Automaton gameOfLife = new GameOfLife(this.width, this.height, factory, this.neighborsStrategy, golRules);

        //1.tworzy factory
        //2. tworzy automaton
        //3. insert structure
    }

    public void submitGameOfLifeRulesButton(){
        this.golRules = new GameOfLifeRules(getTextFromTextField("#remainsAliveField"),
                getTextFromTextField("#bornsField"));
    }

    private String getTextFromTextField(String fieldId){
        String toReturn = ((TextField)scene.lookup(fieldId)).getText();
        return toReturn;
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
        //odkomentować po dorobieniu zawijania
//        if(this.wrapping == false)
//            this.wrapping = true;
//        else
//            this.wrapping = false;
    }

    public void initializeBoardButton(){
        //tworzy planszę na której można wybrać komórki startowe

        HBox boardHbox = (HBox) scene.lookup("#boardHbox");
        guiBoard = new GUIBoard(this.width, this.height, boardHbox, this.map);
        guiBoard.initializeBoard();
        this.map = guiBoard.getMap();
    }

    public void acceptStartingBoardButton(){
        //akceptuje wybrane komórki startowe
        this.map=guiBoard.getMap();

    }

    

    public void printIfClicked(){
        System.out.println("click");
    }
}
