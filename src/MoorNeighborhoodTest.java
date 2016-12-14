import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MoorNeighborhoodTest {
    @Test
    public void cellNeighborsTest_Check2x3NeighboursNoWrapRadius1() {
        MoorNeighborhood testMN = new MoorNeighborhood(5, 5, false, 1);
        Cell cell = new Cell(BinaryState.ALIVE, new Coordinates2D(2,3));
        //cell.coords = new Coordinates2D(2,3);
        HashSet<CellCoordinates> neighbors = (HashSet<CellCoordinates>) testMN.cellNeighbors(cell.coords);
        Set<CellCoordinates> expectedNeighbors = new HashSet<>();
        expectedNeighbors.add(new Coordinates2D(1,2));
        expectedNeighbors.add(new Coordinates2D(2,2));
        expectedNeighbors.add(new Coordinates2D(3,2));
        expectedNeighbors.add(new Coordinates2D(1,3));
        expectedNeighbors.add(new Coordinates2D(3,3));
        expectedNeighbors.add(new Coordinates2D(1,4));
        expectedNeighbors.add(new Coordinates2D(2,4));
        expectedNeighbors.add(new Coordinates2D(3,4));

        Assert.assertEquals("Should have 8 neighbors", 8, neighbors.size());
        Assert.assertEquals("Wrong neighborhood", true, neighbors.equals(expectedNeighbors));
    }

    @Test
    public void cellNeighborsTest_Check4x1NeighboursNoWrapRadius1() {
        MoorNeighborhood testMN = new MoorNeighborhood(5, 5, false, 1);
        Cell cell = new Cell(BinaryState.ALIVE, new Coordinates2D(4,1));
        //cell.coords = new Coordinates2D(4,1);
        HashSet<CellCoordinates> neighbors = (HashSet<CellCoordinates>) testMN.cellNeighbors(cell.coords);
        Set<CellCoordinates> expectedNeighbors = new HashSet<>();
        expectedNeighbors.add(new Coordinates2D(3,0));
        expectedNeighbors.add(new Coordinates2D(4,0));
        expectedNeighbors.add(new Coordinates2D(3,1));
        expectedNeighbors.add(new Coordinates2D(3,2));
        expectedNeighbors.add(new Coordinates2D(4,2));

        Assert.assertEquals("Should have 5 neighbors", 5, neighbors.size());
        Assert.assertEquals("Wrong neighborhood", true, neighbors.equals(expectedNeighbors));
    }

    @Test
    public void cellNeighborsTest_Check0x4NeighboursNoWrapRadius1() {
        MoorNeighborhood testMN = new MoorNeighborhood(5, 5, false, 1);
        Cell cell = new Cell(BinaryState.ALIVE, new Coordinates2D(0,4));
        //cell.coords = new Coordinates2D(0,4);
        HashSet<CellCoordinates> neighbors = (HashSet<CellCoordinates>) testMN.cellNeighbors(cell.coords);
        Set<CellCoordinates> expectedNeighbors = new HashSet<>();
        expectedNeighbors.add(new Coordinates2D(0,3));
        expectedNeighbors.add(new Coordinates2D(1,3));
        expectedNeighbors.add(new Coordinates2D(1,4));

        Assert.assertEquals("Should have 5 neighbors", 3, neighbors.size());
        Assert.assertEquals("Wrong neighborhood", true, neighbors.equals(expectedNeighbors));
    }

    @Test
    public void cellNeighborsTest_Check4x1NeighboursNoWrapRadius2() {
        MoorNeighborhood testMN = new MoorNeighborhood(5, 5, false, 2);
        Cell cell = new Cell(BinaryState.ALIVE, new Coordinates2D(4,1));
        //cell.coords = new Coordinates2D(4,1);
        HashSet<CellCoordinates> neighbors = (HashSet<CellCoordinates>) testMN.cellNeighbors(cell.coords);
        Set<CellCoordinates> expectedNeighbors = new HashSet<>();
        expectedNeighbors.add(new Coordinates2D(2,0));
        expectedNeighbors.add(new Coordinates2D(3,0));
        expectedNeighbors.add(new Coordinates2D(4,0));
        expectedNeighbors.add(new Coordinates2D(2,1));
        expectedNeighbors.add(new Coordinates2D(3,1));
        expectedNeighbors.add(new Coordinates2D(2,2));
        expectedNeighbors.add(new Coordinates2D(3,2));
        expectedNeighbors.add(new Coordinates2D(4,2));
        expectedNeighbors.add(new Coordinates2D(2,3));
        expectedNeighbors.add(new Coordinates2D(3,3));
        expectedNeighbors.add(new Coordinates2D(4,3));

        Assert.assertEquals("Should have 11 neighbors", 11, neighbors.size());
        Assert.assertEquals("Wrong neighborhood", true, neighbors.equals(expectedNeighbors));
    }

    @Test
    public void cellNeighborsTest_Check0x3NeighboursWithWrapRadius1() {
        MoorNeighborhood testMN = new MoorNeighborhood(4, 4, true, 1);
        Cell cell = new Cell(BinaryState.ALIVE, new Coordinates2D(0,3));
        //cell.coords = new Coordinates2D(0,4);
        HashSet<CellCoordinates> neighbors = (HashSet<CellCoordinates>) testMN.cellNeighbors(cell.coords);
        Set<CellCoordinates> expectedNeighbors = new HashSet<>();
        expectedNeighbors.add(new Coordinates2D(3,2));
        expectedNeighbors.add(new Coordinates2D(0,2));
        expectedNeighbors.add(new Coordinates2D(1,2));
        expectedNeighbors.add(new Coordinates2D(3,3));
        expectedNeighbors.add(new Coordinates2D(1,3));
        expectedNeighbors.add(new Coordinates2D(3,0));
        expectedNeighbors.add(new Coordinates2D(0,0));
        expectedNeighbors.add(new Coordinates2D(1,0));

        Assert.assertEquals("Should have 8 neighbors", 8, neighbors.size());
        Assert.assertEquals("Wrong neighborhood", true, neighbors.equals(expectedNeighbors));
    }

    @Test
    public void cellNeighborsTest_Check3x1NeighboursWithWrapRadius2() {
        MoorNeighborhood testMN = new MoorNeighborhood(4, 4, true, 2);
        Cell cell = new Cell(BinaryState.ALIVE, new Coordinates2D(3, 1));
        HashSet<CellCoordinates> neighbors = (HashSet<CellCoordinates>) testMN.cellNeighbors(cell.coords);
        Set<CellCoordinates> expectedNeighbors = new HashSet<>();
        expectedNeighbors.add(new Coordinates2D(1, 3));
        expectedNeighbors.add(new Coordinates2D(2, 3));
        expectedNeighbors.add(new Coordinates2D(3, 3));
        expectedNeighbors.add(new Coordinates2D(0, 3));
        expectedNeighbors.add(new Coordinates2D(1, 3));
        expectedNeighbors.add(new Coordinates2D(1, 0));
        expectedNeighbors.add(new Coordinates2D(2, 0));
        expectedNeighbors.add(new Coordinates2D(3, 0));
        expectedNeighbors.add(new Coordinates2D(0, 0));
        expectedNeighbors.add(new Coordinates2D(1, 0));
        expectedNeighbors.add(new Coordinates2D(1, 1));
        expectedNeighbors.add(new Coordinates2D(2, 1));
        expectedNeighbors.add(new Coordinates2D(0, 1));
        expectedNeighbors.add(new Coordinates2D(1, 1));
        expectedNeighbors.add(new Coordinates2D(1, 2));
        expectedNeighbors.add(new Coordinates2D(2, 2));
        expectedNeighbors.add(new Coordinates2D(3, 2));
        expectedNeighbors.add(new Coordinates2D(0, 2));
        expectedNeighbors.add(new Coordinates2D(1, 2));
        expectedNeighbors.add(new Coordinates2D(1, 3));
        expectedNeighbors.add(new Coordinates2D(2, 3));
        expectedNeighbors.add(new Coordinates2D(3, 3));
        expectedNeighbors.add(new Coordinates2D(0, 3));
        expectedNeighbors.add(new Coordinates2D(1, 3));
        //16 sąsiadów a nie 24 bo się powtarzają a jest set
        Assert.assertEquals(16, neighbors.size());
        //Assert.assertEquals("Wrong neighborhood", true, neighbors.equals(expectedNeighbors));
    }
}