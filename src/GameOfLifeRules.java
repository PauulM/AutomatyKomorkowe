import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GameOfLifeRules {
    public Set<Integer> remainsAlive;
    public Set<Integer> borns;

    public GameOfLifeRules(String remainsAlive, String borns){
        initializeRules(remainsAlive, borns);
    }

    private void initializeRules(String remainsAlive, String borns){
        this.remainsAlive = stringToSet(remainsAlive);
        this.borns = stringToSet(borns);
    }

    private Set<Integer> stringToSet (String p1){
        String delimiter = "[ ]+";
        String[] tokens = p1.split(delimiter);
        Integer[] intTokens = new Integer[tokens.length];
        for(int i=0; i<tokens.length; ++i){
            intTokens[i] = Integer.parseInt(tokens[i]);
        }
        Set<Integer> returnSet = new HashSet<>(Arrays.asList(intTokens));
        return returnSet;
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
