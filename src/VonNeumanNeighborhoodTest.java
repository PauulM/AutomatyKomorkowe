import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class VonNeumanNeighborhoodTest {

    @Test
    public void cellNeighborsTest_Check2x3NeighboursNoWrapRadius1() {
        VonNeumanNeighborhood testVN = new VonNeumanNeighborhood(5, 5, false, 1);
        Cell cell = new Cell(BinaryState.ALIVE, new Coordinates2D(2,3));
        HashSet<CellCoordinates> neighbors = (HashSet<CellCoordinates>) testVN.cellNeighbors(cell.coords);
        Set<CellCoordinates> expectedNeighbors = new HashSet<>();
        expectedNeighbors.add(new Coordinates2D(2,2));
        expectedNeighbors.add(new Coordinates2D(1,3));
        expectedNeighbors.add(new Coordinates2D(3,3));
        expectedNeighbors.add(new Coordinates2D(2,4));

        Assert.assertEquals("Should have 4 neighbors", 4, neighbors.size());
        Assert.assertEquals("Wrong neighborhood", true, neighbors.equals(expectedNeighbors));
    }

    @Test
    public void cellNeighborsTest_Check0x0NeighboursNoWrapRadius1() {
        VonNeumanNeighborhood testVN = new VonNeumanNeighborhood(5, 5, false, 1);
        Cell cell = new Cell(BinaryState.ALIVE, new Coordinates2D(0,0));
        HashSet<CellCoordinates> neighbors = (HashSet<CellCoordinates>) testVN.cellNeighbors(cell.coords);
        Set<CellCoordinates> expectedNeighbors = new HashSet<>();
        expectedNeighbors.add(new Coordinates2D(1,0));
        expectedNeighbors.add(new Coordinates2D(0,1));

        Assert.assertEquals("Should have 2 neighbors", 2, neighbors.size());
        Assert.assertEquals("Wrong neighborhood", true, neighbors.equals(expectedNeighbors));
    }

    @Test
    public void cellNeighborsTest_Check0x0NeighboursWithWrapRadius1() {
        VonNeumanNeighborhood testVN = new VonNeumanNeighborhood(5, 5, true, 1);
        Cell cell = new Cell(BinaryState.ALIVE, new Coordinates2D(0,0));
        HashSet<CellCoordinates> neighbors = (HashSet<CellCoordinates>) testVN.cellNeighbors(cell.coords);
        Set<CellCoordinates> expectedNeighbors = new HashSet<>();
        expectedNeighbors.add(new Coordinates2D(1,0));
        expectedNeighbors.add(new Coordinates2D(0,1));
        expectedNeighbors.add(new Coordinates2D(4,0));
        expectedNeighbors.add(new Coordinates2D(0,4));

        Assert.assertEquals("Should have 4 neighbors", 4, neighbors.size());
        Assert.assertEquals("Wrong neighborhood", true, neighbors.equals(expectedNeighbors));
    }

    @Test
    public void cellNeighborsTest_Check2x2NeighboursNoWrapRadius2() {
        VonNeumanNeighborhood testVN = new VonNeumanNeighborhood(5, 5, false, 2);
        Cell cell = new Cell(BinaryState.ALIVE, new Coordinates2D(2,2));
        HashSet<CellCoordinates> neighbors = (HashSet<CellCoordinates>) testVN.cellNeighbors(cell.coords);
        Set<CellCoordinates> expectedNeighbors = new HashSet<>();
        expectedNeighbors.add(new Coordinates2D(2,0));
        expectedNeighbors.add(new Coordinates2D(2,1));
        expectedNeighbors.add(new Coordinates2D(0,2));
        expectedNeighbors.add(new Coordinates2D(1,2));
        expectedNeighbors.add(new Coordinates2D(3,2));
        expectedNeighbors.add(new Coordinates2D(4,2));
        expectedNeighbors.add(new Coordinates2D(2,3));
        expectedNeighbors.add(new Coordinates2D(2,4));
        expectedNeighbors.add(new Coordinates2D(1,1));
        expectedNeighbors.add(new Coordinates2D(3,1));
        expectedNeighbors.add(new Coordinates2D(1,3));
        expectedNeighbors.add(new Coordinates2D(3,3));

        Assert.assertEquals("Should have 12 neighbors", 12, neighbors.size());
        Assert.assertEquals("Wrong neighborhood", true, neighbors.equals(expectedNeighbors));
    }
}