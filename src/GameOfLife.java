import java.util.ArrayList;
import java.util.Set;

public class GameOfLife extends Automaton2Dim {

    private GameOfLifeRules golRules;
    private CellNeighborhood neighborhoodKind;

    public GameOfLife(int width,int height,CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
        super(width, height, cellStateFactory, cellNeighborhood);
        golRules = new GameOfLifeRules();

    }

    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        return new GameOfLife(this.getWidth(), this.getHeight(), cellStateFactory, cellNeighborhood);
    }

    protected CellState nextCellState(Cell currentCell, Set<Cell> neighborsStates){
        return golRules.determineState(numberOfAliveNeighbors(neighborsStates), currentCell.state);
    }

    private int numberOfAliveNeighbors(Set<Cell> neighborStates){
        int aliveNeighbors = 0;
        for(Cell tmpCell : neighborStates){
            if(tmpCell.state == BinaryState.ALIVE)
                ++aliveNeighbors;
        }
        return aliveNeighbors;
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
//
//    protected int numberOfAliveNeighbors(Set<CellCoordinates> neighborsCoords){
//        int aliveNeighbors = 0;
//        for(CellCoordinates tmpCoords : neighborsCoords){
//            Coordinates2D tmpCoords2D = (Coordinates2D) tmpCoords;
//            if(board.get(tmpCoords2D.x).get(tmpCoords2D.y).state.equals(BinaryState.ALIVE))
//                ++aliveNeighbors;
//        }
//        return aliveNeighbors;
//    }

}