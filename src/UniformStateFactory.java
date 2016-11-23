
public class UniformStateFactory implements CellStateFactory {
    private CellState state;

    public UniformStateFactory(CellState state){
        this.state = state;
    }

    @Override
    public CellState initialState(CellCoordinates coords) {
        return state;
    }
}
