import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Map;

public class CellButtonWW extends CellButton {
    private final int cellSizePx = 10;
    private Map<CellCoordinates, CellState> map;
    private final Color headColor = Color.BLUE;
    private final Color tailColor = Color.RED;
    private final Color wireColor = Color.YELLOW;
    private final Color voidColor = Color.BLACK;

    public CellButtonWW(CellCoordinates coords, CellState state, Map<CellCoordinates, CellState> map ){
        super("");
        this.setPrefHeight(cellSizePx);
        this.setPrefWidth(cellSizePx);
        this.coords = coords;
        this.state = state;
        this.map = map;
        if(state.equals(WireElectronState.HEAD))
            this.setBackground(new Background(new BackgroundFill(headColor, new CornerRadii(2), new Insets(2))));
        else if (state.equals(WireElectronState.TAIL))
            this.setBackground(new Background(new BackgroundFill(tailColor, new CornerRadii(2), new Insets(2))));
        else if (state.equals(WireElectronState.WIRE))
            this.setBackground(new Background(new BackgroundFill(wireColor, new CornerRadii(2), new Insets(2))));
        else
            this.setBackground(new Background(new BackgroundFill(voidColor, new CornerRadii(2), new Insets(2))));

        this.setOnMouseClicked(e -> {
            changeState();
            System.out.println(this.state.toString());
        });
    }

    public void changeState(){
        if(state.equals(WireElectronState.HEAD)) {
            this.setBackground(new Background(new BackgroundFill(voidColor, new CornerRadii(2), new Insets(2))));
            this.state = WireElectronState.VOID;
        }
        else if (state.equals(WireElectronState.TAIL)) {
            this.setBackground(new Background(new BackgroundFill(headColor, new CornerRadii(2), new Insets(2))));
            this.state = WireElectronState.HEAD;
        }
        else if (state.equals(WireElectronState.WIRE)) {
            this.setBackground(new Background(new BackgroundFill(tailColor, new CornerRadii(2), new Insets(2))));
            this.state = WireElectronState.TAIL;
        }
        else {
            this.setBackground(new Background(new BackgroundFill(wireColor, new CornerRadii(2), new Insets(2))));
            this.state = WireElectronState.WIRE;
        }
        map.put(coords,this.state);
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
}
