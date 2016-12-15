import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Map;

public class CellButtonWW extends CellButton {
    private final int CELLSIZEPX = 25;
    private Map<CellCoordinates, CellState> map;
    private final Color HEADCOLOR = Color.BLUE;
    private final Color TAILCOLOR = Color.RED;
    private final Color WIRECOLOR = Color.YELLOW;
    private final Color VOIDCOLOR = Color.BLACK;

    public CellButtonWW(CellCoordinates coords, CellState state, Map<CellCoordinates, CellState> map ){
        super("");
        this.setPrefHeight(CELLSIZEPX);
        this.setPrefWidth(CELLSIZEPX);
        this.coords = coords;
        this.state = state;
        this.map = map;
        if(state.equals(WireElectronState.HEAD))
            this.setBackground(new Background(new BackgroundFill(HEADCOLOR, new CornerRadii(0), new Insets(0.5))));
        else if (state.equals(WireElectronState.TAIL))
            this.setBackground(new Background(new BackgroundFill(TAILCOLOR, new CornerRadii(0), new Insets(0.5))));
        else if (state.equals(WireElectronState.WIRE))
            this.setBackground(new Background(new BackgroundFill(WIRECOLOR, new CornerRadii(0), new Insets(0.5))));
        else
            this.setBackground(new Background(new BackgroundFill(VOIDCOLOR, new CornerRadii(0), new Insets(0.5))));

        this.setOnMouseClicked(e -> {
            changeState();
            System.out.println(this.state.toString());
        });
    }

    public void changeState(){
        if(state.equals(WireElectronState.HEAD)) {
            this.setBackground(new Background(new BackgroundFill(VOIDCOLOR, new CornerRadii(0), new Insets(0.7))));
            this.state = WireElectronState.VOID;
        }
        else if (state.equals(WireElectronState.TAIL)) {
            this.setBackground(new Background(new BackgroundFill(HEADCOLOR, new CornerRadii(0), new Insets(0.7))));
            this.state = WireElectronState.HEAD;
        }
        else if (state.equals(WireElectronState.WIRE)) {
            this.setBackground(new Background(new BackgroundFill(TAILCOLOR, new CornerRadii(0), new Insets(0.7))));
            this.state = WireElectronState.TAIL;
        }
        else {
            this.setBackground(new Background(new BackgroundFill(WIRECOLOR, new CornerRadii(0), new Insets(0.7))));
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
