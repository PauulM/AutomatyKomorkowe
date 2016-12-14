import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Automaton2DimTest {

    @Test
    public void hasNextCoordinatesTest_Coords1x2_Board5x5(){
        Map<CellCoordinates, CellState> map = new HashMap<>();
        CellNeighborhood neighborhood = new MoorNeighborhood(5,5,false,1);
        CellStateFactory factory = new GeneralStateFactory(map);
        GameOfLifeRules golRules = new GameOfLifeRules("2 3", "3");
        Automaton2Dim gameOfLife = new GameOfLife(5, 5, factory, neighborhood,golRules);
        CellCoordinates coords = new Coordinates2D(1,2);
        Assert.assertEquals("Cell (1,2) should have next cell", true, gameOfLife.hasNextCoordinates(coords));
    }

    @Test
    public void hasNextCoordinatesTest_Coords4x4_Board5x5(){
        Map<CellCoordinates, CellState> map = new HashMap<>();
        CellNeighborhood neighborhood = new MoorNeighborhood(5,5,false,1);
        CellStateFactory factory = new GeneralStateFactory(map);
        GameOfLifeRules golRules = new GameOfLifeRules("2 3", "3");
        Automaton2Dim gameOfLife = new GameOfLife(5, 5, factory, neighborhood,golRules);
        CellCoordinates coords = new Coordinates2D(4,4);
        Assert.assertEquals("Cell (1,2) should have next cell", false, gameOfLife.hasNextCoordinates(coords));
    }

    @Test
    public void hasNextCoordinatesTest_Coords4x2_Board5x5(){
        Map<CellCoordinates, CellState> map = new HashMap<>();
        CellNeighborhood neighborhood = new MoorNeighborhood(5,5,false,1);
        CellStateFactory factory = new GeneralStateFactory(map);
        GameOfLifeRules golRules = new GameOfLifeRules("2 3", "3");
        Automaton2Dim gameOfLife = new GameOfLife(5, 5, factory, neighborhood,golRules);
        CellCoordinates coords = new Coordinates2D(4,2);
        Assert.assertEquals("Cell (1,2) should have next cell", true, gameOfLife.hasNextCoordinates(coords));
    }

    @Test
    public void hasNextCoordinatesTest_Coords2x4_Board5x5(){
        Map<CellCoordinates, CellState> map = new HashMap<>();
        CellNeighborhood neighborhood = new MoorNeighborhood(5,5,false,1);
        CellStateFactory factory = new GeneralStateFactory(map);
        GameOfLifeRules golRules = new GameOfLifeRules("2 3", "3");
        Automaton2Dim gameOfLife = new GameOfLife(5, 5, factory, neighborhood,golRules);
        CellCoordinates coords = new Coordinates2D(2,4);
        Assert.assertEquals("Cell (1,2) should have next cell", true, gameOfLife.hasNextCoordinates(coords));
    }

    @Test
    public void nextCoordinatesTest_Coords2x2_Board5x5(){
        Map<CellCoordinates, CellState> map = new HashMap<>();
        CellNeighborhood neighborhood = new MoorNeighborhood(5,5,false,1);
        CellStateFactory factory = new GeneralStateFactory(map);
        GameOfLifeRules golRules = new GameOfLifeRules("2 3", "3");
        Automaton2Dim gameOfLife = new GameOfLife(5, 5, factory, neighborhood,golRules);
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
        Map<CellCoordinates, CellState> map = new HashMap<>();
        CellNeighborhood neighborhood = new MoorNeighborhood(5,5,false,1);
        CellStateFactory factory = new GeneralStateFactory(map);
        GameOfLifeRules golRules = new GameOfLifeRules("2 3", "3");
        Automaton2Dim gameOfLife = new GameOfLife(5, 5, factory, neighborhood,golRules);
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

}