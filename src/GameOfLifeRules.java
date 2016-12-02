import java.util.ArrayList;
import java.util.Set;

public class GameOfLifeRules {
    public Set<Integer> remainsAlive;
    public Set<Integer> borns;

    public GameOfLifeRules(){

    }

    public void initializeRules(Set<Integer> a, Set<Integer> b){
        this.remainsAlive = a;
        this.borns = b;
    }

    public CellState determineState (int numberOfAliveNeighbors, CellState state){
        if(state.equals(BinaryState.ALIVE)){
            if(isOnTheList(numberOfAliveNeighbors, remainsAlive))
                return BinaryState.ALIVE;
            else
                return BinaryState.DEAD;
        }
        else{
            if(isOnTheList(numberOfAliveNeighbors, borns))
                return BinaryState.ALIVE;
            else
                return BinaryState.DEAD;
        }

    }

    private boolean isOnTheList (Integer element, Set<Integer> list){
        for(Integer value : list) {
            if(value.equals(element))
                return true;
        }
        return false;
    }
}
