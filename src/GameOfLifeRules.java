import java.util.ArrayList;

public class GameOfLifeRules {
    public ArrayList<Integer> remainsAlive;
    public ArrayList<Integer> borns;

    public GameOfLifeRules(){

    }

    public void initializeRules(ArrayList<Integer> a, ArrayList<Integer> b){
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

    private boolean isOnTheList (Integer element, ArrayList<Integer> list){
        for(Integer value : list) {
            if(value.equals(element))
                return true;
        }
        return false;
    }
}
