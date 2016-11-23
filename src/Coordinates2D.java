import java.util.ArrayList;

/**
 * Created by pawma on 21.11.2016.
 */
public class Coordinates2D implements CellCoordinates {
    public final int x;
    public final int y;

    public Coordinates2D(int xx, int yy){
        this.x = xx;
        this.y = yy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates2D that = (Coordinates2D) o;

        if (x != that.x) return false;
        return y == that.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
