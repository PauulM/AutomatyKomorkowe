import java.util.Set;

public class Wireworld extends Automaton2Dim {

    public Wireworld(int width,int height,CellStateFactory cellStateFactory,
                     CellNeighborhood cellNeighborhood){
        super(width, height, cellStateFactory, cellNeighborhood);
    }

    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        return new Wireworld(this.getWidth(), this.getHeight(), cellStateFactory, cellNeighborhood);
    }

    protected CellState nextCellState(Cell currentCell, Set<Cell> neighborsStates){
        if (currentCell.state.equals(WireElectronState.HEAD))
            return WireElectronState.TAIL;
        else if (currentCell.state.equals(WireElectronState.TAIL))
            return WireElectronState.WIRE;
        else if (currentCell.state.equals(WireElectronState.WIRE)){
            int headNeighbors = numberOfHeadNeighbors(neighborsStates);
            if(headNeighbors == 1 || headNeighbors == 2)
                return WireElectronState.HEAD;
            else
                return WireElectronState.WIRE;
        }
        return WireElectronState.VOID;
    }

    private int numberOfHeadNeighbors(Set<Cell> neighborsStates){
        int toReturn = 0;
        for (Cell tmpCell : neighborsStates){
            if (tmpCell.state.equals(WireElectronState.HEAD))
                ++toReturn;
        }
        return toReturn;
    }
}
