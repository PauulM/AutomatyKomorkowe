import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class AutomatonTest {

    @Test
    public void GeneralTestGameOfLife_CheckIfMapsAreEqual(){
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
    }

    @Test
    public void GeneralTestWireworld_CheckIfMapsAreEqual(){
        Map<CellCoordinates, CellState> map = new HashMap<>();

        map.put(new Coordinates2D(0,0), WireElectronState.VOID);
        map.put(new Coordinates2D(1,0), WireElectronState.WIRE);
        map.put(new Coordinates2D(2,0), WireElectronState.VOID);
        map.put(new Coordinates2D(0,1), WireElectronState.VOID);
        map.put(new Coordinates2D(1,1), WireElectronState.HEAD);
        map.put(new Coordinates2D(2,1), WireElectronState.VOID);
        map.put(new Coordinates2D(0,2), WireElectronState.VOID);
        map.put(new Coordinates2D(1,2), WireElectronState.TAIL);
        map.put(new Coordinates2D(2,2), WireElectronState.VOID);

        CellNeighborhood neighborhood = new MoorNeighborhood(3,3,false,1);
        CellStateFactory factory = new GeneralStateFactory(map);
        Automaton automaton = new Wireworld(3,3,factory,neighborhood);
        automaton.insertStructure(map);
        Automaton nxtAut = automaton.nextState();

        Map<CellCoordinates, CellState> map2 = new HashMap<>();

        map2.put(new Coordinates2D(0,0), WireElectronState.VOID);
        map2.put(new Coordinates2D(1,0), WireElectronState.HEAD);
        map2.put(new Coordinates2D(2,0), WireElectronState.VOID);
        map2.put(new Coordinates2D(0,1), WireElectronState.VOID);
        map2.put(new Coordinates2D(1,1), WireElectronState.TAIL);
        map2.put(new Coordinates2D(2,1), WireElectronState.VOID);
        map2.put(new Coordinates2D(0,2), WireElectronState.VOID);
        map2.put(new Coordinates2D(1,2), WireElectronState.WIRE);
        map2.put(new Coordinates2D(2,2), WireElectronState.VOID);;

        Automaton expectedNextAutomaton = new Wireworld(3,3,factory,neighborhood);
        expectedNextAutomaton.insertStructure(map2);

        Assert.assertEquals("Maps should be equal", true, map2.equals(nxtAut.getCells()));
    }

}