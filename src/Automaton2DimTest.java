import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Automaton2DimTest {

    @Test
    public void initializationTest_ListsSizeIs2x3(){
        Automaton2Dim automaton2Dim = new GameOfLife(2,3);
        Assert.assertEquals("X list should be of size 2", 2, automaton2Dim.board.size());
        Assert.assertEquals("Y list should be of size 3", 3, automaton2Dim.board.get(0).size());
    }

    @Test
    public void stateInitializationTest_AllCellsDead(){
        Automaton2Dim automaton2Dim = new GameOfLife(2,3);
        for(ArrayList<Cell> XList : automaton2Dim.board){
            for(Cell tempCell : XList)
                Assert.assertEquals("All cells should be dead", BinaryState.DEAD, tempCell.state);
        }
    }

    @Test
    public void changeCellStateTest_X2Y1Alive(){
        Automaton2Dim automaton2Dim = new GameOfLife(3,3);
        automaton2Dim.changeCellStateToAlive(2,1);
        Assert.assertEquals("X:2,Y:1 cell should be alive", BinaryState.ALIVE,
                automaton2Dim.board.get(2).get(1).state);
    }
}