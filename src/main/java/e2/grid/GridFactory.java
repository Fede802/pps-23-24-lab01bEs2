package e2.grid;



import e2.utils.Pair;

import java.util.Set;

public interface GridFactory {

    Grid createEmptyGrid(int gridSize);

    Grid createGridWithRandomMines(int gridSize, int mineNumber) throws IllegalArgumentException;

    Grid createGridWithPresetMinePositions(int gridSize, Set<Pair<Integer, Integer>> presetMinePosition);

}
