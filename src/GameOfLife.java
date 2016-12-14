import java.util.ArrayList;
import java.util.Set;

public class GameOfLife extends Automaton2Dim {

    private GameOfLifeRules golRules;

    public GameOfLife(int width,int height,CellStateFactory cellStateFactory,
                      CellNeighborhood cellNeighborhood, GameOfLifeRules rules){
        super(width, height, cellStateFactory, cellNeighborhood);
        golRules = rules;

    }

    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        return new GameOfLife(this.getWidth(), this.getHeight(), cellStateFactory, cellNeighborhood, this.golRules);
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

}