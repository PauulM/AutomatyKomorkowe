import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;


public class GameOfLifeRulesTest {
    @Test
    public void determineStateTest_CellHas3NeighborsAndIsDead(){
        Cell cell = new Cell(BinaryState.DEAD, new Coordinates2D(2,3));
        String remainsAlive = "2 3";
        String borns ="3";
        GameOfLifeRules golRules = new GameOfLifeRules(remainsAlive, borns);

        Assert.assertEquals("Should be alive", BinaryState.ALIVE,
                golRules.determineState(3, cell.state));
    }

    @Test
    public void determineStateTest_CellHas2NeighborsAndIsDead(){
        Cell cell = new Cell(BinaryState.DEAD, new Coordinates2D(2,3));
        String remainsAlive = "2 3";
        String borns ="3";
        GameOfLifeRules golRules = new GameOfLifeRules(remainsAlive, borns);

        Assert.assertEquals("Should be dead", BinaryState.DEAD,
                golRules.determineState(2, cell.state));
    }

    @Test
    public void determineStateTest_CellHas2NeighborsAndIsAlive(){
        Cell cell = new Cell(BinaryState.ALIVE, new Coordinates2D(2,3));
        String remainsAlive = "2 3";
        String borns ="3";
        GameOfLifeRules golRules = new GameOfLifeRules(remainsAlive, borns);

        Assert.assertEquals("Should be alive", BinaryState.ALIVE,
                golRules.determineState(2, cell.state));
    }

}