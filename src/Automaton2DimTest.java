import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Automaton2DimTest {

    @Test
    public void hasNextCoordinatesTest_Coords1x2_Board5x5(){
        Automaton2Dim gameOfLife = new GameOfLife(5, 5);
        CellCoordinates coords = new Coordinates2D(1,2);
        Assert.assertEquals("Cell (1,2) should have next cell", true, gameOfLife.hasNextCoordinates(coords));
    }

    @Test
    public void hasNextCoordinatesTest_Coords4x4_Board5x5(){
        Automaton2Dim gameOfLife = new GameOfLife(5, 5);
        CellCoordinates coords = new Coordinates2D(4,4);
        Assert.assertEquals("Cell (1,2) should have next cell", false, gameOfLife.hasNextCoordinates(coords));
    }

    @Test
    public void hasNextCoordinatesTest_Coords4x2_Board5x5(){
        Automaton2Dim gameOfLife = new GameOfLife(5, 5);
        CellCoordinates coords = new Coordinates2D(4,2);
        Assert.assertEquals("Cell (1,2) should have next cell", true, gameOfLife.hasNextCoordinates(coords));
    }

    @Test
    public void hasNextCoordinatesTest_Coords2x4_Board5x5(){
        Automaton2Dim gameOfLife = new GameOfLife(5, 5);
        CellCoordinates coords = new Coordinates2D(2,4);
        Assert.assertEquals("Cell (1,2) should have next cell", true, gameOfLife.hasNextCoordinates(coords));
    }

    @Test
    public void nextCoordinatesTest_Coords2x2_Board5x5(){
        Automaton2Dim gameOfLife = new GameOfLife(5, 5);
        CellCoordinates coords = new Coordinates2D(2,2);
        CellCoordinates expectedCoords = new Coordinates2D(3,2);
        try {
            Assert.assertEquals("Cell (2,2) should have next cell (3x2)",
                    true, gameOfLife.nextCoordinates(coords).equals(expectedCoords));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void nextCoordinatesTest_Coords4x2_Board5x5(){
        Automaton2Dim gameOfLife = new GameOfLife(5, 5);
        CellCoordinates coords = new Coordinates2D(4,2);
        CellCoordinates expectedCoords = new Coordinates2D(0,3);
        try {
            Assert.assertEquals("Cell (4,2) should have next cell (0x3)",
                    true, gameOfLife.nextCoordinates(coords).equals(expectedCoords));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

//    @Test(expected = Exception.class)
//    public void nextCoordinatesTest_Coords4x4_Board5x5_ThrowsException() throws Exception{
//        Automaton2Dim gameOfLife = new GameOfLife(5,5);
//        CellCoordinates coords = new Coordinates2D(4,4);
//        gameOfLife.nextCoordinates(coords);
//    }
//
//    @Test(expected = Exception.class)
//    public void nextCoordinatesTest_Coords5x5_Board5x5_ThrowsException() throws Exception{
//        Automaton2Dim gameOfLife = new GameOfLife(5,5);
//        CellCoordinates coords = new Coordinates2D(5,5);
//        gameOfLife.nextCoordinates(coords);
//    }
















//    @Test
//    public void initializationTest_ListsSizeIs2x3(){
//        Automaton2Dim automaton2Dim = new GameOfLife(2,3);
//        Assert.assertEquals("X list should be of size 2", 2, automaton2Dim.board.size());
//        Assert.assertEquals("Y list should be of size 3", 3, automaton2Dim.board.get(0).size());
//    }
//
//    @Test
//    public void stateInitializationTest_AllCellsDead(){
//        Automaton2Dim automaton2Dim = new GameOfLife(2,3);
//        for(ArrayList<Cell> XList : automaton2Dim.board){
//            for(Cell tempCell : XList)
//                Assert.assertEquals("All cells should be dead", BinaryState.DEAD, tempCell.state);
//        }
//    }

//    @Test
//    public void changeCellStateTest_X2Y1Alive(){
//        Automaton2Dim automaton2Dim = new GameOfLife(3,3);
//        automaton2Dim.changeCellStateToAlive(2,1);
//        Assert.assertEquals("X:2,Y:1 cell should be alive", BinaryState.ALIVE,
//                automaton2Dim.board.get(2).get(1).state);
//    }
}