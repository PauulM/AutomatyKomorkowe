import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Automaton {
    private CellNeighborhood neighborsStrategy;
    private Map<CellCoordinates, CellState> cells;
    private CellStateFactory stateFactory;

    public Automaton(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
        this.stateFactory = cellStateFactory;
        this.neighborsStrategy = cellNeighborhood;
    }

    public Automaton nextState(){
        Automaton newAutomaton = newInstance(stateFactory, neighborsStrategy);
        CellIterator currentIterator = cellIterator();
        CellIterator newIterator = newAutomaton.cellIterator();
        while(currentIterator.hasNext()){
            Cell c = currentIterator.next();
            Set<Cell> neighborsCellSet = mapCoordinates(neighborsStrategy.cellNeighbors(c.coords));
            newIterator.next();
            newIterator.setState(nextCellState(c, neighborsCellSet));
        }
        return newAutomaton;
    }

    public void insertStructure (Map<CellCoordinates, CellState> structure){
        CellIterator iterator = cellIterator();
        while(iterator.hasNext()){
            Cell tmpCell = iterator.next();
            cells.put(tmpCell.coords,stateFactory.initialState(tmpCell.coords));
        }

    }

    public class CellIterator{
        private CellCoordinates currentCoords;

        public CellIterator(CellCoordinates coords){
            this.currentCoords = coords;
        }

        public boolean hasNext(){
            return hasNextCoordinates(currentCoords);
        }
        public Cell next(){
            Cell tmpCell = new Cell(cells.get(nextCoordinates(currentCoords)), nextCoordinates(currentCoords));
            currentCoords = nextCoordinates(currentCoords);
            return tmpCell;
        }
        public void setState(CellState state){
            cells.put(currentCoords, state);
        }
    }

    public CellIterator cellIterator(){
        return new CellIterator(initialCoordinates());
    }

    protected abstract Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood);
    protected abstract boolean hasNextCoordinates(CellCoordinates cellCoords);
    protected abstract CellCoordinates nextCoordinates(CellCoordinates cellCoords);
    protected abstract CellCoordinates initialCoordinates();
    protected abstract CellState nextCellState(Cell currentCell, Set<Cell> neighborsStates);


    //dla danego setu współrzędnych znajduje stany komórek pod tymi współrzędnymi i tworzy set celli
    private Set<Cell> mapCoordinates (Set<CellCoordinates> cellCoordinates){
        Set<Cell> cellSet = new HashSet<>();
        for(CellCoordinates tmpCoords : cellCoordinates){
            cellSet.add(new Cell(cells.get(tmpCoords), tmpCoords));
        }
        return cellSet;
    }



    //protected abstract void initialize();
    //protected abstract void initializeState();
    // abstract void changeCellStateToAlive(int x, int y);
}
