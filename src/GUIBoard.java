import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;

public class GUIBoard {

    private int width;
    private int height;
    private HBox boardHbox;
    private Map<CellCoordinates, CellState> map;
    private CellButton[][] board;

    public GUIBoard(int width, int height, HBox boardHbox, Map<CellCoordinates, CellState> map){
        this.width = width;
        this.height = height;
        this.boardHbox = boardHbox;
        this.map = map;
        this.board = new CellButton[width][height];
    }

    public void initializeBoard(){
        for (int i=0; i<width; ++i){
            VBox tmpVbox = new VBox();
            boardHbox.getChildren().addAll(tmpVbox);
            for(int j=0; j<height; ++j){
                CellButton tmpCellButton = new CellButton(new Coordinates2D(i,j), BinaryState.DEAD);
                this.board[i][j] = tmpCellButton;
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

    public Map<CellCoordinates, CellState> getMap(){
        return map;
    }

}
