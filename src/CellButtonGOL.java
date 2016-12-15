import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Map;

public class CellButtonGOL extends CellButton {

    private final int CELLSIZEPX = 25;
    private Map<CellCoordinates, CellState> map;
    private final Color DEADCOLOR = Color.BLACK;
    private final Color ALIVECOLOR = Color.DARKGRAY;

    public CellButtonGOL(CellCoordinates coords, CellState state, Map<CellCoordinates, CellState> map ){
        super("");
        this.setPrefHeight(CELLSIZEPX);
        this.setPrefWidth(CELLSIZEPX);
        this.coords = coords;
        this.state = state;
        this.map = map;
        if(state.equals(BinaryState.DEAD))
            this.setBackground(new Background(new BackgroundFill(DEADCOLOR, new CornerRadii(0), new Insets(0.7))));
        else
            this.setBackground(new Background(new BackgroundFill(ALIVECOLOR, new CornerRadii(0), new Insets(0.7))));
        this.setOnMouseClicked(e -> {
            changeState();
            System.out.println(this.state.toString());
        });
    }

    public CellCoordinates getCoords(){
        return this.coords;
    }

    public CellState getState(){
        return this.state;
    }

    public Map<CellCoordinates, CellState> getMap(){
        return this.map;
    }

    private void changeState(){
        if (this.state.equals(BinaryState.ALIVE)){
            this.state = BinaryState.DEAD;
            this.setBackground(new Background(new BackgroundFill(DEADCOLOR, new CornerRadii(0), new Insets(0.7))));
        }
        else
        {
            this.state = BinaryState.ALIVE;
            this.setBackground(new Background(new BackgroundFill(ALIVECOLOR, new CornerRadii(0), new Insets(0.7))));
        }
        map.put(coords,this.state);
    }

}
