package e2.grid;

import e2.cell.GameCell;

import java.util.Set;

public interface Grid {

    int getSize();

    GameCell getCellAt(int cellX, int cellY) throws IndexOutOfBoundsException;

    Set<GameCell> getNeighbours(int cellX, int cellY);

}
