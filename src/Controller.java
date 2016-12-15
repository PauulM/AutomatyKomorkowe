import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    private Label commentBox;

    public Controller(){
        this.map = new HashMap<>();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setScene(Scene scene){
        this.scene = scene;
        commentBox = (Label) this.scene.lookup("#commentBox");
    }

    public void gameOfLifeButton(){
        if(map == null || neighborsStrategy == null || golRules==null){
            commentBox.setText("cannot create Game of Life, some data might be missing");
            return;
        }
        else if(currentAutomaton != null){
            commentBox.setText("automaton already chosen");
            return;
        }
        factory = new GeneralStateFactory(map);
        automaton = new GameOfLife(this.width, this.height, this.factory, this.neighborsStrategy, golRules);
        currentAutomaton = automaton;
        automaton.insertStructure(map);
        commentBox.setText("created GoL instance");
    }

    public void submitGameOfLifeRulesButton(){
        this.golRules = new GameOfLifeRules(getTextFromTextField("#remainsAliveField"),
                getTextFromTextField("#bornsField"));
        commentBox.setText("submitted GoL Rules");
        //currentAutomaton = new GameOfLife(this.width, this.height, this.factory, this.neighborsStrategy, golRules);
    }

    private String getTextFromTextField(String fieldId){
        String toReturn = ((TextField)scene.lookup(fieldId)).getText();
        return toReturn;
    }

    public void submitButton(){
        this.width = Integer.parseInt(getTextFromTextField("#widthField"));
        if(this.width < 1){
            this.width = 1;
            commentBox.setText("invalid width, width set to 1");
        }
        else
            commentBox.setText("submitted width, height and radius");
        this.height = Integer.parseInt(getTextFromTextField("#heightField"));
        if(this.height < 1){
            this.height = 1;
            commentBox.setText("invalid height, height set to 1");
        }
        else
            commentBox.setText("submitted width, height and radius");
        this.radius = Integer.parseInt(getTextFromTextField("#radiusField"));
        if(this.radius < 1){
            this.radius = 1;
            commentBox.setText("invalid radius, radius set to 1");
        }
        else
            commentBox.setText("submitted width, height and radius");
    }

    public void moorNeighborhoodButton(){
        if(width < 1 || height < 1 || radius < 1){
            commentBox.setText("cannot create Moor Neighborhood, some data might be missing");
            return;
        }
        this.neighborsStrategy = new MoorNeighborhood(this.width, this.height, this.wrapping, this.radius);
        commentBox.setText("Moor Neighborhood chosen");
    }

    public void vonNeumanNeighborhoodButton(){
        if(width < 1 || height < 1 || radius < 1){
            commentBox.setText("cannot create Von Neuman Neighborhood, some data might be missing");
            return;
        }
        this.neighborsStrategy = new VonNeumanNeighborhood(this.width, this.height, this.wrapping, this.radius);
        commentBox.setText("Von Neuman Neighborhood chosen");
    }

    public void wrappingButton(){
        if(this.wrapping == false) {
            this.wrapping = true;
            commentBox.setText("wrapping is now ENABLED");
        }
        else {
            this.wrapping = false;
            commentBox.setText("wrapping is now DISABLED");
        }
    }

    public void initializeBoardButton(){
        //tworzy planszę na której można wybrać komórki startowe
        if (guiBoard != null){
            commentBox.setText("board already exists");
            return;
        }
        if (currentAutomaton == null)
            //currentAutomaton = new Wireworld(this.width, this.height, this.factory, this.neighborsStrategy);
        if(width < 1 || height < 1 || scene == null || map == null || currentAutomaton == null){
            commentBox.setText("cannot initialize board, some data might be missing");
            return;
        }
        boardHbox = (HBox) scene.lookup("#boardHbox");
        guiBoard = new GUIBoard(this.width, this.height, boardHbox, this.map, currentAutomaton);
        if(currentAutomaton instanceof GameOfLife)
            guiBoard.initializeBoardGOL();
        else if (currentAutomaton instanceof Wireworld)
            guiBoard.initializeBoardWW();
//        if(golRules!=null)
//            guiBoard.initializeBoardGOL();
//        else
//            guiBoard.initializeBoardWW();
//        this.map = guiBoard.getMap();
    }

    public void acceptStartingBoardButton(){
        //akceptuje wybrane komórki startowe
        this.map=guiBoard.getMap();
    }

    public void nextButton(){
        if(automaton == null){
            commentBox.setText("cannot create next state, some data might be missing");
            return;
        }
        this.automaton = this.automaton.nextState();
        this.map = this.automaton.getCells();
        updateBoard();
        commentBox.setText("next automaton state displayed");
    }

    public void wireworldButton(){
        if(map == null || neighborsStrategy == null){
            commentBox.setText("cannot create Wireworld, some data might be missing");
            return;
        }
        else if(currentAutomaton != null){
            commentBox.setText("automaton already chosen");
            return;
        }
        factory = new GeneralStateFactory(map);
        automaton = new Wireworld(this.width, this.height, this.factory, this.neighborsStrategy);
        currentAutomaton = automaton;
        automaton.insertStructure(map);
        commentBox.setText("created Wireworld instance");
    }

    private void updateBoard(){
        this.boardHbox.getChildren().clear();
        guiBoard = new GUIBoard(this.width, this.height, boardHbox, this.map, this.currentAutomaton);
        guiBoard.drawUpdatedBoard();
        this.map = guiBoard.getMap();
    }

    public void resetButton(){
        boardHbox.getChildren().clear();
        golRules = null;
        guiBoard = null;
        currentAutomaton = null;
        commentBox.setText("settings reset");
    }

}
