import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;

public class GUIBoard {

    private int width;
    private int height;
    private HBox boardHbox;
    private Automaton currentAutomaton;
    private Map<CellCoordinates, CellState> map;
    private CellButton[][] board;

    public GUIBoard(int width, int height, HBox boardHbox, Map<CellCoordinates,
            CellState> map, Automaton currentAutomaton){
        this.width = width;
        this.height = height;
        this.boardHbox = boardHbox;
        this.map = map;
        this.board = new CellButton[width][height];
        this.currentAutomaton = currentAutomaton;
    }

    public void initializeBoardGOL(){
        for (int i=0; i<width; ++i){
            VBox tmpVbox = new VBox();
            tmpVbox.setMaxHeight(500);
            boardHbox.getChildren().addAll(tmpVbox);
            for(int j=0; j<height; ++j){
                CellButton tmpCellButtonGOL = new CellButtonGOL(new Coordinates2D(i,j), BinaryState.DEAD, map);
                this.board[i][j] = tmpCellButtonGOL;
                tmpVbox.getChildren().addAll(this.board[i][j]);
            }
        }
        boardToMap();
    }

    public void initializeBoardWW(){
        for (int i=0; i<width; ++i){
            VBox tmpVbox = new VBox();
            tmpVbox.setMaxHeight(500);
            boardHbox.getChildren().addAll(tmpVbox);
            for(int j=0; j<height; ++j){
                CellButton tmpCellButtonWW = new CellButtonWW(new Coordinates2D(i,j),
                        WireElectronState.VOID, map);
                this.board[i][j] = tmpCellButtonWW;
                tmpVbox.getChildren().addAll(this.board[i][j]);
            }
        }
        boardToMap();
    }

    private void boardToMap(){
        for (int i=0; i<width; ++i){
            for(int j=0; j<height; ++j){
                map.put(board[i][j].getCoords(),board[i][j].getState());
            }
        }
    }

    public void drawUpdatedBoard(){
        for (int i=0; i<width; ++i){
            VBox tmpVbox = new VBox();
            boardHbox.getChildren().addAll(tmpVbox);
            CellButton tmpCellButton;
            for (int j=0; j<height; ++j){
                Coordinates2D tmpCoords = new Coordinates2D(i,j);
                if(currentAutomaton instanceof GameOfLife)
                    tmpCellButton = new CellButtonGOL(tmpCoords, map.get(tmpCoords), map);
                else
                    tmpCellButton = new CellButtonWW(tmpCoords, map.get(tmpCoords), map);
                this.board[i][j] = tmpCellButton;
                tmpVbox.getChildren().addAll(this.board[i][j]);
            }
        }
        boardToMap();
    }
    public Map<CellCoordinates, CellState> getMap(){
        return map;
    }

    public void insertGlider(){
        int leftTopCornerCoordX = width/2 - 1;
        int leftTopCornerCoordY = height/2 - 1;
        board[leftTopCornerCoordX][leftTopCornerCoordY].state = BinaryState.ALIVE;
        board[leftTopCornerCoordX+1][leftTopCornerCoordY].state = BinaryState.ALIVE;
        board[leftTopCornerCoordX+2][leftTopCornerCoordY].state = BinaryState.ALIVE;
        board[leftTopCornerCoordX][leftTopCornerCoordY+1].state = BinaryState.ALIVE;
        board[leftTopCornerCoordX+1][leftTopCornerCoordY+2].state = BinaryState.ALIVE;
        boardToMap();
    }

    public void insertLWSS(){
        int leftTopCornerCoordX = width/2 - 2;
        int leftTopCornerCoordY = height/2 - 2;
        board[leftTopCornerCoordX+1][leftTopCornerCoordY].state = BinaryState.ALIVE;
        board[leftTopCornerCoordX+4][leftTopCornerCoordY].state = BinaryState.ALIVE;
        board[leftTopCornerCoordX][leftTopCornerCoordY+1].state = BinaryState.ALIVE;
        board[leftTopCornerCoordX][leftTopCornerCoordY+2].state = BinaryState.ALIVE;
        board[leftTopCornerCoordX+4][leftTopCornerCoordY+2].state = BinaryState.ALIVE;
        board[leftTopCornerCoordX][leftTopCornerCoordY+3].state = BinaryState.ALIVE;
        board[leftTopCornerCoordX+1][leftTopCornerCoordY+3].state = BinaryState.ALIVE;
        board[leftTopCornerCoordX+2][leftTopCornerCoordY+3].state = BinaryState.ALIVE;
        board[leftTopCornerCoordX+3][leftTopCornerCoordY+3].state = BinaryState.ALIVE;
        boardToMap();
    }
}
