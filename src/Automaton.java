import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Automaton {
    private CellNeighborhood neighborsStrategy;
    private Map<CellCoordinates, CellState> cells;

    public Automaton nextState(){

        return null;
    }

    public void insertStructure (Map <? extends CellCoordinates, CellState> structure){
        //this.cells = structure;
    }

    public class CellIterator{
        private CellCoordinates currentCoords;

        public CellIterator(CellCoordinates coords){
            this.currentCoords = coords;
        }

        public boolean hasNext(){
            return true; // w budowie
        }
        public Cell next(){
            return null;//w budowie
        }
        public void setState(CellState state){
            cells.put(currentCoords, state);
        }
    }

//    public CellIterator cellIterator(){
//        return new CellIterator();
//    }

    protected abstract Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood);
    protected abstract boolean hasNextCoordinates(CellCoordinates cellCoords);
    protected abstract CellCoordinates nextCoordinates(CellCoordinates cellCoords) throws Exception;
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
