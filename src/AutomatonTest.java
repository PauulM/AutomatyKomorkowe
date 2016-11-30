import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AutomatonTest {

    @Test
    public void CzyDziala(){
        Map<CellCoordinates, CellState> map = new HashMap<>();

        map.put(new Coordinates2D(0,0), BinaryState.DEAD);
        map.put(new Coordinates2D(1,0), BinaryState.ALIVE);
        map.put(new Coordinates2D(2,0), BinaryState.DEAD);
        map.put(new Coordinates2D(0,1), BinaryState.DEAD);
        map.put(new Coordinates2D(1,1), BinaryState.ALIVE);
        map.put(new Coordinates2D(2,1), BinaryState.DEAD);
        map.put(new Coordinates2D(0,2), BinaryState.DEAD);
        map.put(new Coordinates2D(1,2), BinaryState.ALIVE);
        map.put(new Coordinates2D(2,2), BinaryState.DEAD);

        CellNeighborhood neighborhood = new MoorNeighborhood(3,3,false,1);
        CellStateFactory factory = new GeneralStateFactory(map);
        Automaton automaton = new GameOfLife(3,3,factory,neighborhood);
        automaton.insertStructure(map);
        Automaton nxtAut = automaton.nextState();

        Map<CellCoordinates, CellState> map2 = new HashMap<>();

        map.put(new Coordinates2D(0,0), BinaryState.DEAD);
        map.put(new Coordinates2D(1,0), BinaryState.DEAD);
        map.put(new Coordinates2D(2,0), BinaryState.DEAD);
        map.put(new Coordinates2D(0,1), BinaryState.ALIVE);
        map.put(new Coordinates2D(1,1), BinaryState.ALIVE);
        map.put(new Coordinates2D(2,1), BinaryState.ALIVE);
        map.put(new Coordinates2D(0,2), BinaryState.DEAD);
        map.put(new Coordinates2D(1,2), BinaryState.DEAD);
        map.put(new Coordinates2D(2,2), BinaryState.DEAD);

        Assert.assertEquals("abc", true, map.equals(map2));

    }

}