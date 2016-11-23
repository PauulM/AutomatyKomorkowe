import java.util.ArrayList;

public abstract class Automaton2Dim extends Automaton{
    private int width;
    private int height;


    public Automaton2Dim(int width, int height){
        this.width = width;
        this.height = height;
    }

    protected CellCoordinates initialCoordinates(){
        return new Coordinates2D(0,0);
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
