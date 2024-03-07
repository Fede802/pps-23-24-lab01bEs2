package e2.grid;


import e2.utils.Pair;
import e2.cell.GameCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    private final static int GRID_SIZE = 7;

    private Grid grid;

    @BeforeEach
    void initGrid(){
        this.grid = new GridImpl(GRID_SIZE);
    }

    @Test
    void sizeSetCorrectly(){
        assertEquals(GRID_SIZE, this.grid.getSize());
    }

    @Test
    void gridInitializedCorrectly(){
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int cellX = i;
                int cellY = j;
                assertAll(
                        () -> assertEquals(new Pair<>(cellX,cellY), this.grid.getCellAt(cellX,cellY).getCellPosition()),
                        () -> assertFalse(this.grid.getCellAt(cellX,cellY).isSelected()),
                        () -> assertFalse(this.grid.getCellAt(cellX,cellY).isFlagged())
                );

            }
        }
    }

    @Test
    void wrongCellSearchHandling(){
        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class,()->this.grid.getCellAt(-1,0)),
                () -> assertThrows(IndexOutOfBoundsException.class,()->this.grid.getCellAt(GRID_SIZE,0)),
                () -> assertThrows(IndexOutOfBoundsException.class,()->this.grid.getCellAt(0,-1)),
                () -> assertThrows(IndexOutOfBoundsException.class,()->this.grid.getCellAt(0,GRID_SIZE))
        );
    }

    @Test
    void neighboursSearchWorkCorrectly(){
        int cellX = 0;
        int cellY = 0;
        Set<Pair<Integer,Integer>> expectedNeighbours = Set.of(
                new Pair<>(1,0),
                new Pair<>(1,1),
                new Pair<>(0,1)
        );

        assertEquals(expectedNeighbours, this.grid.getNeighbours(cellX,cellY).stream().map(GameCell::getCellPosition).collect(Collectors.toSet()));
    }

    @Test
    void neighboursSearchOnWrongCellHandling(){
        assertThrows(IndexOutOfBoundsException.class, () -> this.grid.getNeighbours(-1,-1));
    }

}
