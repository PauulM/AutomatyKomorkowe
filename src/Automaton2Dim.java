import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Automaton2Dim extends Automaton {
    private int width;
    private int height;


    public Automaton2Dim(int width, int height, CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood){
        super(cellStateFactory,cellNeighborhood, initializeBoard(width, height, cellStateFactory));
        this.width = width;
        this.height = height;
    }

    private static Map<CellCoordinates, CellState> initializeBoard(int width, int height,
                                                                   CellStateFactory cellStateFactory){
        Map<CellCoordinates, CellState> board = new HashMap<>();
        for(int x = 0; x<width; ++x){
            for(int y = 0; y<height; ++y){
                Coordinates2D tmpCoords2D = new Coordinates2D(x,y);
                board.put(tmpCoords2D, cellStateFactory.initialState(tmpCoords2D));
            }
        }
        return board;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    protected CellCoordinates initialCoordinates(){
        return new Coordinates2D(-1,0);
    }

    protected boolean hasNextCoordinates (CellCoordinates cellCoords){
        Coordinates2D castCoords = (Coordinates2D) cellCoords;
        if(castCoords.x == width-1 && castCoords.y == height-1)
            return false;
        else
            return true;
        //iteruję od lewej do prawej, od góry do dołu, x to szer, y to wys
        //jeśli dane współrzędne są maksymalne (leżą w prawym dolnym) to znaczy że nie ma już kolejnych
    }

    protected CellCoordinates nextCoordinates(CellCoordinates cellCoords) {
        Coordinates2D castCoords = (Coordinates2D) cellCoords;
        if(castCoords.x < width-1 && castCoords.y < height)
            return new Coordinates2D(castCoords.x+1, castCoords.y);
        else if(castCoords.x == width-1 && castCoords.y < height-1)
            return new Coordinates2D(0, castCoords.y + 1);
        else
            return castCoords;
    }






//    @Override
//    protected void initialize(){ //ustawia rozmiar planszy
//        for(int i=0; i<width; ++i){
//            board.add(new ArrayList<>());
//            for(int j=0; j<height; ++j){
//                Cell tmpCell = new Cell();
//                tmpCell.coords = new Coordinates2D(i,j);
//                board.get(i).add(tmpCell);
//            }
//        }
//    }
//
//    @Override
//    protected void initializeState(){ // inicjalizuje planaszę martwymi komórkami
//        for(ArrayList<Cell> XList : board){
//            for(Cell tempCell : XList){
//                tempCell.state = BinaryState.DEAD;
//            }
//        }
//    }
//
//    @Override
//    protected void changeCellStateToAlive(int x, int y) {
//        board.get(x).get(y).state = BinaryState.ALIVE;
//    }
}
