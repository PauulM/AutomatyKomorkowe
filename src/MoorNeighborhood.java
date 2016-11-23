import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MoorNeighborhood implements CellNeighborhood {
    private int boardWidth;
    private int boardHeight;
    private boolean boardWrap;
    private int radius;

    public MoorNeighborhood(int width, int height, boolean wrap, int rad){
        this.boardWidth = width;
        this.boardHeight = height;
        this.boardWrap = wrap;
        this.radius = rad;
    }


    public Set<CellCoordinates> cellNeighbors(CellCoordinates cell){
        if(!boardWrap) return searchNeighborsNoWrap(cell);

        else return new HashSet<>();//wersja robocza;
    }

    private Set<CellCoordinates> searchNeighborsNoWrap(CellCoordinates cell){
        HashSet<CellCoordinates> toReturnSet = new HashSet<>();
        Coordinates2D castCell = (Coordinates2D) cell;

        for(int x = -radius; x <= radius; ++x){
            for(int y = -radius; y <= radius; ++y){
                if(x == 0 && y == 0)
                    continue;
                if(checkNewCoords(castCell, x, y))
                    toReturnSet.add(new Coordinates2D(castCell.x+x, castCell.y+y));
            }
        }
        return toReturnSet;
    }

    private boolean checkNewCoords(Coordinates2D cell, int moveX, int moveY){
        return coordsAreOnBoard(cell.x + moveX, cell.y + moveY);
    }

    private boolean coordsAreOnBoard(int XCoord, int YCoord){
        if(XCoord >= 0 && YCoord >= 0 && XCoord < boardWidth && YCoord < boardHeight)
            return true;
        else
            return false;
    }
}