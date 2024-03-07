package e2.grid;


import e2.cell.EntityType;
import e2.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GridFactoryTest {

    private final static int GRID_SIZE = 7;
    private GridFactory gridFactory;

    @BeforeEach
    void initGridFactory(){
        this.gridFactory = new GridFactoryImpl();
    }

    @Test
    void emptyGridCorrectlyCreated(){
        Grid grid = this.gridFactory.createEmptyGrid(GRID_SIZE);
        int emptyEntityFound = 0;
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                if(grid.getCellAt(i,j).getEntityType() == EntityType.EMPTY) {
                    emptyEntityFound = emptyEntityFound + 1;
                }
            }
        }
        assertEquals(GRID_SIZE*GRID_SIZE, emptyEntityFound);
    }

    @Test
    void gridWithRandomMinesCorrectlyCreated(){
        int mineNumber = 10;
        Grid grid = this.gridFactory.createGridWithRandomMines(GRID_SIZE, mineNumber);
        int mineFound = 0;
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                if(grid.getCellAt(i,j).getEntityType() == EntityType.MINE) {
                    mineFound = mineFound + 1;
                }
            }
        }
        assertEquals(mineNumber, mineFound);
    }

    @Test
    void gridWithTooMuchMinesCorrectlyHandled(){
        assertThrows(IllegalArgumentException.class, () -> this.gridFactory.createGridWithRandomMines(GRID_SIZE, GRID_SIZE*GRID_SIZE+1));
    }

    @Test
    void gridWithPresetMinePositionCorrectlyCreated(){
        Set<Pair<Integer,Integer>> presetMinePosition = Set.of(
                new Pair<>(0,0),
                new Pair<>(1,1),
                new Pair<>(1,2),
                new Pair<>(GRID_SIZE-1,3)
        );
        Grid grid = this.gridFactory.createGridWithPresetMinePositions(GRID_SIZE, presetMinePosition);
        Set<Pair<Integer,Integer>> mineFound = new HashSet<>();
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                if(grid.getCellAt(i,j).getEntityType() == EntityType.MINE) {
                    mineFound.add(new Pair<>(i,j));
                }
            }
        }
        assertEquals(presetMinePosition, mineFound);
    }

}
