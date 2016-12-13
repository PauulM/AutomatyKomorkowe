import javafx.scene.control.Button;

public abstract class CellButton extends Button {

    protected CellCoordinates coords;
    protected CellState state;

    public CellButton(String a){
        super(a);
    }

    public CellCoordinates getCoords(){
        return this.coords;
    }

    public CellState getState(){
        return this.state;
    }
}
