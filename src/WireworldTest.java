import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;


public class WireworldTest {

    @Test
    public void nextCellStateTest_cellIsHead(){
        Automaton wireworld = new Wireworld(3,3,new GeneralStateFactory(new HashMap<>()),
                new MoorNeighborhood(3,3,false,1));

        Cell testedCell = new Cell(WireElectronState.HEAD,new Coordinates2D(1,1));
        Set<Cell> neighborsStates = new HashSet<>();

        Assert.assertEquals("Should be TAIL", WireElectronState.TAIL,
                wireworld.nextCellState(testedCell, neighborsStates));
    }

    @Test
    public void nextCellStateTest_cellIsVoid(){
        Automaton wireworld = new Wireworld(3,3,new GeneralStateFactory(new HashMap<>()),
                new MoorNeighborhood(3,3,false,1));

        Cell testedCell = new Cell(WireElectronState.VOID,new Coordinates2D(1,1));
        Set<Cell> neighborsStates = new HashSet<>();

        Assert.assertEquals("Should be TAIL", WireElectronState.VOID,
                wireworld.nextCellState(testedCell, neighborsStates));
    }

    @Test
    public void nextCellStateTest_cellIsTail(){
        Automaton wireworld = new Wireworld(3,3,new GeneralStateFactory(new HashMap<>()),
                new MoorNeighborhood(3,3,false,1));

        Cell testedCell = new Cell(WireElectronState.TAIL,new Coordinates2D(1,1));
        Set<Cell> neighborsStates = new HashSet<>();
        Assert.assertEquals("Should be WIRE", WireElectronState.WIRE,
                wireworld.nextCellState(testedCell, neighborsStates));
    }

    @Test
    public void nextCellStateTest_cellIsVOID(){
        Automaton wireworld = new Wireworld(3,3,new GeneralStateFactory(new HashMap<>()),
                new MoorNeighborhood(3,3,false,1));

        Cell testedCell = new Cell(WireElectronState.VOID,new Coordinates2D(1,1));
        Set<Cell> neighborsStates = new HashSet<>();
        Assert.assertEquals("Should be VOID", WireElectronState.VOID,
                wireworld.nextCellState(testedCell, neighborsStates));
    }

    @Test
    public void nextCellStateTest_cellIsWireAndHas2HeadNeighbors(){
        Automaton wireworld = new Wireworld(3,3,new GeneralStateFactory(new HashMap<>()),
                new MoorNeighborhood(3,3,false,1));

        Cell testedCell = new Cell(WireElectronState.WIRE,new Coordinates2D(1,1));
        Set<Cell> neighborsStates = new HashSet<>();
        neighborsStates.add(new Cell(WireElectronState.VOID,new Coordinates2D(0,0)));
        neighborsStates.add(new Cell(WireElectronState.WIRE,new Coordinates2D(1,0)));
        neighborsStates.add(new Cell(WireElectronState.VOID,new Coordinates2D(2,0)));
        neighborsStates.add(new Cell(WireElectronState.VOID,new Coordinates2D(0,1)));
        neighborsStates.add(new Cell(WireElectronState.VOID,new Coordinates2D(2,1)));
        neighborsStates.add(new Cell(WireElectronState.HEAD,new Coordinates2D(0,2)));
        neighborsStates.add(new Cell(WireElectronState.WIRE,new Coordinates2D(1,2)));
        neighborsStates.add(new Cell(WireElectronState.HEAD,new Coordinates2D(2,2)));

        Assert.assertEquals("Should be HEAD", WireElectronState.HEAD,
                wireworld.nextCellState(testedCell, neighborsStates));
    }

    @Test
    public void nextCellStateTest_cellIsWireAndHas3HeadNeighbors(){
        Automaton wireworld = new Wireworld(3,3,new GeneralStateFactory(new HashMap<>()),
                new MoorNeighborhood(3,3,false,1));

        Cell testedCell = new Cell(WireElectronState.WIRE,new Coordinates2D(1,1));
        Set<Cell> neighborsStates = new HashSet<>();
        neighborsStates.add(new Cell(WireElectronState.VOID,new Coordinates2D(0,0)));
        neighborsStates.add(new Cell(WireElectronState.WIRE,new Coordinates2D(1,0)));
        neighborsStates.add(new Cell(WireElectronState.VOID,new Coordinates2D(2,0)));
        neighborsStates.add(new Cell(WireElectronState.VOID,new Coordinates2D(0,1)));
        neighborsStates.add(new Cell(WireElectronState.VOID,new Coordinates2D(2,1)));
        neighborsStates.add(new Cell(WireElectronState.HEAD,new Coordinates2D(0,2)));
        neighborsStates.add(new Cell(WireElectronState.HEAD,new Coordinates2D(1,2)));
        neighborsStates.add(new Cell(WireElectronState.HEAD,new Coordinates2D(2,2)));

        Assert.assertEquals("Should be WIRE", WireElectronState.WIRE,
                wireworld.nextCellState(testedCell, neighborsStates));
    }


}