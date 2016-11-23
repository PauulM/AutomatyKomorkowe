import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class GameOfLifeRulesTest {
    @Test
    public void determineStateTest_CellHas3NeighborsAndIsDead(){
        Cell cell = new Cell();
        cell.state = BinaryState.DEAD;
        GameOfLifeRules golRules = new GameOfLifeRules();
        ArrayList<Integer> remainsAlive = new ArrayList<>();
        remainsAlive.add(2);
        remainsAlive.add(3);
        ArrayList<Integer> borns = new ArrayList<>();
        borns.add(3);
        golRules.initializeRules(remainsAlive, borns);

        Assert.assertEquals("Should be alive", BinaryState.ALIVE,
                golRules.determineState(3, cell.state));
    }

    @Test
    public void determineStateTest_CellHas2NeighborsAndIsDead(){
        Cell cell = new Cell();
        cell.state = BinaryState.DEAD;
        GameOfLifeRules golRules = new GameOfLifeRules();
        ArrayList<Integer> remainsAlive = new ArrayList<>();
        remainsAlive.add(2);
        remainsAlive.add(3);
        ArrayList<Integer> borns = new ArrayList<>();
        borns.add(3);
        golRules.initializeRules(remainsAlive, borns);

        Assert.assertEquals("Should be dead", BinaryState.DEAD,
                golRules.determineState(2, cell.state));
    }

    @Test
    public void determineStateTest_CellHas2NeighborsAndIsAlive(){
        Cell cell = new Cell();
        cell.state = BinaryState.ALIVE;
        GameOfLifeRules golRules = new GameOfLifeRules();
        ArrayList<Integer> remainsAlive = new ArrayList<>();
        remainsAlive.add(2);
        remainsAlive.add(3);
        ArrayList<Integer> borns = new ArrayList<>();
        borns.add(3);
        golRules.initializeRules(remainsAlive, borns);

        Assert.assertEquals("Should be alive", BinaryState.ALIVE,
                golRules.determineState(2, cell.state));
    }
}