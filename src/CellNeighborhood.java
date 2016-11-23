import java.util.ArrayList;
import java.util.Set;

public interface CellNeighborhood {
    public Set<CellCoordinates> cellNeighbors(CellCoordinates cell);

    //ma zwracać kolekcję współrzędnych komórek, które sąsiadują z podaną w parametrze
}
