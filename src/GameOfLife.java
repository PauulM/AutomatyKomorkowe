import java.util.ArrayList;
import java.util.Set;

public class GameOfLife extends Automaton2Dim {

    private GameOfLifeRules golRules;
    private CellNeighborhood neighborhoodKind;

    public GameOfLife(int width,int height){
        super(width, height);
        golRules = new GameOfLifeRules();

    }

//    public void nextState (){
//        ArrayList<ArrayList<Cell>> updatedList = new ArrayList<>();
//        for(ArrayList<Cell> XList : this.board){
//            for(Cell tmpCell : XList){
//                ArrayList<CellCoordinates> neighbors =
//                        new ArrayList<>(neighborhoodKind.cellNeighbors(tmpCell.coords));
//                tmpCell.state = golRules.determineState(numberOfAliveNeighbors(neighbors), tmpCell.state);
//            }
//        }
//        this.board = updatedList;
//    }

//    protected int numberOfAliveNeighbors(ArrayList<CellCoordinates> neighborsCoords){
//        int aliveNeighbors = 0;
//        for(CellCoordinates tmpCoords : neighborsCoords){
//            Coordinates2D tmpCoords2D = (Coordinates2D) tmpCoords;
//            if(board.get(tmpCoords2D.x).get(tmpCoords2D.y).state.equals(BinaryState.ALIVE))
//                ++aliveNeighbors;
//        }
//        return aliveNeighbors;
//    }

}