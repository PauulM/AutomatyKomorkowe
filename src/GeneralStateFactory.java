import java.util.Map;

public class GeneralStateFactory implements CellStateFactory{

    private Map<CellCoordinates, CellState> states;

    public GeneralStateFactory(Map<CellCoordinates, CellState> states){
        this.states = states;
    }

    @Override
    //szuka na mapie współrzędnych i znajduje dla nich stan komórki o tych współrzędnych
    public CellState initialState(CellCoordinates coords) {
        return states.get(coords);
    }
}
