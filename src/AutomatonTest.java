import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class AutomatonTest {

    @Test
    public void GeneralTest_CheckIfMapsAreEqual(){
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

        String remainsAlive = "2 3";
        String borns ="3";
        GameOfLifeRules golRules = new GameOfLifeRules(remainsAlive, borns);

        CellNeighborhood neighborhood = new MoorNeighborhood(3,3,false,1);
        CellStateFactory factory = new GeneralStateFactory(map);
        Automaton automaton = new GameOfLife(3,3,factory,neighborhood,golRules);
        automaton.insertStructure(map);
        Automaton nxtAut = automaton.nextState();

        Map<CellCoordinates, CellState> map2 = new HashMap<>();

        map2.put(new Coordinates2D(0,0), BinaryState.DEAD);
        map2.put(new Coordinates2D(1,0), BinaryState.DEAD);
        map2.put(new Coordinates2D(2,0), BinaryState.DEAD);
        map2.put(new Coordinates2D(0,1), BinaryState.ALIVE);
        map2.put(new Coordinates2D(1,1), BinaryState.ALIVE);
        map2.put(new Coordinates2D(2,1), BinaryState.ALIVE);
        map2.put(new Coordinates2D(0,2), BinaryState.DEAD);
        map2.put(new Coordinates2D(1,2), BinaryState.DEAD);
        map2.put(new Coordinates2D(2,2), BinaryState.DEAD);

        Automaton expectedNextAutomaton = new GameOfLife(3,3,factory,neighborhood,golRules);
        expectedNextAutomaton.insertStructure(map2);

        Assert.assertEquals("Maps should be equal", true, map2.equals(nxtAut.getCells()));
        Assert.assertEquals("Automatons should be equal", true, nxtAut.equals(expectedNextAutomaton));
    }

}