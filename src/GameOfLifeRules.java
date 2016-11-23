import java.util.ArrayList;

/**
 * Created by pawma on 21.11.2016.
 */
public class GameOfLifeRules {
    public ArrayList<Integer> remainsAlive;
    public ArrayList<Integer> borns;

    public GameOfLifeRules(){

    }

    public void initializeRules(ArrayList<Integer> a, ArrayList<Integer> b){
        this.remainsAlive = a;
        this.borns = b;
    }

    public CellState determineState (int numberOfNeighbors, CellState state){
        if(state.equals(BinaryState.ALIVE)){
            if(isOnTheList(numberOfNeighbors, remainsAlive))
                return BinaryState.ALIVE;
            else
                return BinaryState.DEAD;
        }
        else{
            if(isOnTheList(numberOfNeighbors, borns))
                return BinaryState.ALIVE;
            else
                return BinaryState.DEAD;
        }

    }

    private boolean isOnTheList (Integer element, ArrayList<Integer> list){
        for(Integer value : list) {
            if(value.equals(element))
                return true;
        }
        return false;
    }
}
