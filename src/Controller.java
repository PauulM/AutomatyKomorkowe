import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Automaton automaton;
    private Automaton currentAutomaton;
    private int width;
    private int height;
    private int radius;
    private CellStateFactory factory;
    private Map<CellCoordinates, CellState> map;
    private CellNeighborhood neighborsStrategy;
    private GameOfLifeRules golRules;
    private boolean wrapping = false;
    private GUIBoard guiBoard;
    private HBox boardHbox;

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
        factory = new GeneralStateFactory(map);
        automaton = new GameOfLife(this.width, this.height, this.factory, this.neighborsStrategy, golRules);
        currentAutomaton = automaton;
        automaton.insertStructure(map);
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
        if(this.wrapping == false)
            this.wrapping = true;
        else
            this.wrapping = false;
    }

    public void initializeBoardButton(){
        //tworzy planszę na której można wybrać komórki startowe

        boardHbox = (HBox) scene.lookup("#boardHbox");
        guiBoard = new GUIBoard(this.width, this.height, boardHbox, this.map, currentAutomaton);
        if(golRules!=null)
            guiBoard.initializeBoardGOL();
        else
            guiBoard.initializeBoardWW();
        this.map = guiBoard.getMap();
    }

    public void acceptStartingBoardButton(){
        //akceptuje wybrane komórki startowe
        this.map=guiBoard.getMap();
    }

    public void nextButton(){
        this.automaton = this.automaton.nextState();
        this.map = this.automaton.getCells();
        updateBoard();
    }

    public void wireworldButton(){
        factory = new GeneralStateFactory(map);
        automaton = new Wireworld(this.width, this.height, this.factory, this.neighborsStrategy);
        currentAutomaton = automaton;
        automaton.insertStructure(map);
    }

    private void updateBoard(){
        this.boardHbox.getChildren().clear();
        guiBoard = new GUIBoard(this.width, this.height, boardHbox, this.map, this.currentAutomaton);
        guiBoard.drawUpdatedBoard();
        this.map = guiBoard.getMap();
    }

    public void resetButton(){

    }

}
