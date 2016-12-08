import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Map;

public class CellButton extends Button {

    private CellCoordinates coords;
    private CellState state;
    private final int cellSizePx = 15;
    private Map<CellCoordinates, CellState> map;

    public CellButton(CellCoordinates coords, CellState state, Map<CellCoordinates, CellState> map ){
        super("");
        this.setPrefHeight(cellSizePx);
        this.setPrefWidth(cellSizePx);
        this.coords = coords;
        this.state = state;
        this.map = map;
        if(state.equals(BinaryState.DEAD))
            this.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(2), new Insets(2))));
        else
            this.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(2), new Insets(2))));
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
            this.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(2), new Insets(2))));
        }
        else
        {
            this.state = BinaryState.ALIVE;
            this.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(2), new Insets(2))));
        }
        map.put(coords,this.state);
    }

}
