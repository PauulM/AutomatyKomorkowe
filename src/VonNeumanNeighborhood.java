import java.util.HashSet;
import java.util.Set;
import static java.lang.Math.abs;

public class VonNeumanNeighborhood implements CellNeighborhood{

    private int boardWidth;
    private int boardHeight;
    private boolean boardWrap;
    private int radius;

    public VonNeumanNeighborhood(int width, int height, boolean wrap, int rad){
        this.boardWidth = width;
        this.boardHeight = height;
        this.boardWrap = wrap;
        this.radius = rad;
    }

    @Override
    public Set<CellCoordinates> cellNeighbors(CellCoordinates cell){
        if(!boardWrap)
            return searchNeighborsNoWrap(cell);
        else
            return searchNeighborsWithWrap(cell);
    }

    private Set<CellCoordinates> searchNeighborsNoWrap(CellCoordinates coords){
        HashSet<CellCoordinates> toReturnSet = new HashSet<>();
        Coordinates2D castCoords = (Coordinates2D) coords;

        for(int x = -radius; x <= radius; ++x){
            for(int y = -radius; y <= radius; ++y){
                if(x == 0 && y == 0)
                    continue;
                if(abs(abs(x) + abs(y)) > radius)
                    continue;
                if(checkNewCoords(castCoords, x, y))
                    toReturnSet.add(new Coordinates2D(castCoords.x+x, castCoords.y+y));
            }
        }
        return toReturnSet;
    }

    private Set<CellCoordinates> searchNeighborsWithWrap(CellCoordinates coords){
        HashSet<CellCoordinates> toReturnSet = new HashSet<>();
        Coordinates2D castCoords = (Coordinates2D) coords;
        for(int x = -radius; x <= radius; ++x){
            for(int y = -radius; y <= radius; ++y){
                if(x == 0 && y == 0)
                    continue;
                if(abs(abs(x) + abs(y)) > radius)
                    continue;
                if(checkNewCoords(castCoords, x, y))
                    toReturnSet.add(new Coordinates2D(castCoords.x+x, castCoords.y+y));
                else{
                    if(isXOnBoard(castCoords.x+x)==false && isYOnBoard(castCoords.y+y)==true){
                        toReturnSet.add(new Coordinates2D(abs(boardWidth-abs(x)-castCoords.x),
                                castCoords.y+y));
                    }
                    else if(isXOnBoard(castCoords.x+x)==true && isYOnBoard(castCoords.y+y)==false){
                        toReturnSet.add(new Coordinates2D((castCoords.x+x),
                                abs(boardHeight-abs(y)-castCoords.y)));
                    }
                    else{
                        toReturnSet.add(new Coordinates2D(abs(boardWidth-abs(x)-castCoords.x),
                                abs(boardHeight-abs(y)-castCoords.y)));
                    }
                }
            }
        }
        return toReturnSet;
    }

    private boolean checkNewCoords(Coordinates2D coords, int moveX, int moveY){
        return coordsAreOnBoard(coords.x + moveX, coords.y + moveY);
    }

    private boolean isXOnBoard(int XCoord){
        if(XCoord >= 0 && XCoord < boardWidth)
            return true;
        else
            return false;
    }

    private boolean isYOnBoard(int YCoord){
        if(YCoord >= 0 && YCoord < boardHeight)
            return true;
        else
            return false;
    }

    private boolean coordsAreOnBoard(int XCoord, int YCoord){
        if(XCoord >= 0 && YCoord >= 0 && XCoord < boardWidth && YCoord < boardHeight)
            return true;
        else
            return false;
    }
}
