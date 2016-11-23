import java.util.ArrayList;
import java.util.Map;

public abstract class Automaton {
    private CellNeighborhood neighborsStrategy;

    public abstract void nextState();

    protected abstract void initialize();
    protected abstract void initializeState();
    protected abstract void changeCellStateToAlive(int x, int y);
}
