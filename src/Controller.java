import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

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
    private Timer timer;
    private int timeInMs;
    private TimerTask clickNextButton;
    private Timeline timeline;
    private boolean timerStarted = false;

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
        try {
            this.golRules = new GameOfLifeRules(getTextFromTextField("#remainsAliveField"),
                    getTextFromTextField("#bornsField"));
            commentBox.setText("submitted GoL Rules");
        }
        catch (Exception e){
            commentBox.setText("error occured");
        }
    }

    private String getTextFromTextField(String fieldId){
        String toReturn = ((TextField)scene.lookup(fieldId)).getText();
        return toReturn;
    }

    public void submitButton(){
        try {
            this.width = Integer.parseInt(getTextFromTextField("#widthField"));
            boolean modification = false;
            if (this.width < 1) {
                this.width = 1;
                modification = true;
            }
            this.height = Integer.parseInt(getTextFromTextField("#heightField"));
            if (this.height < 1) {
                this.height = 1;
                modification = true;
            }
            this.radius = Integer.parseInt(getTextFromTextField("#radiusField"));
            if (this.radius < 1) {
                this.radius = 1;
                modification = true;
            }
            if (modification == true) {
                commentBox.setText("invalid data input, some values changed");
            } else
                commentBox.setText("width, height and radius set");
        }
        catch (Exception e){
            commentBox.setText("error occured");
        }
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
        if(automaton == null || guiBoard == null || boardHbox == null){
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
        if(boardHbox != null)
            boardHbox.getChildren().clear();
        golRules = null;
        guiBoard = null;
        currentAutomaton = null;
        timer = null;
        clickNextButton = null;
        neighborsStrategy = null;
        commentBox.setText("settings reset");
    }

    public void submitTimerButton(){
        try{
            timeInMs = Integer.parseInt(getTextFromTextField("#time"));
        }
        catch(Exception e){
            commentBox.setText("error occured");
            return;
        }
        if(timeInMs < 1)
            timeInMs = 1000;
        commentBox.setText("invalid time, time set to 1000ms");
        this.timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(timeInMs),
                event -> nextButton()));
        commentBox.setText("created new Timer, period is " + Integer.toString(timeInMs) + "ms");
    }

    public void startTimerButton(){
        if(timeline == null){
            commentBox.setText("cannot start timer, some data might be missing");
            return;
        }
        timerStarted = true;
        timeline.play();
        commentBox.setText("auto Next started");
    }

    public void stopTimerButton(){
        if(timeline == null || timerStarted == false){
            commentBox.setText("cannot stop timer, some data might be missing");
            return;
        }
        timeline.stop();
        commentBox.setText("auto Next stopped");
    }

    public void insertGliderButton(){
        if(guiBoard == null || !(currentAutomaton instanceof GameOfLife) || this.width < 3 || this.height < 3){
            commentBox.setText("cannot insert Glider");
            return;
        }
        boardHbox.getChildren().clear();
        guiBoard = null;
        initializeBoardButton();
        this.guiBoard.insertGlider();
        updateBoard();
        commentBox.setText("Glider inserted");
    }

    public void insertLWSSButton(){
        if(guiBoard == null || !(currentAutomaton instanceof GameOfLife) || this.width < 5 || this.height < 4){
            commentBox.setText("cannot insert LWSS");
            return;
        }
        boardHbox.getChildren().clear();
        guiBoard = null;
        initializeBoardButton();
        this.guiBoard.insertLWSS();
        updateBoard();
        commentBox.setText("LWSS inserted");
    }
}