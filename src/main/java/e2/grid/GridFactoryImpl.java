package e2.grid;

import e2.utils.Pair;
import e2.utils.RandomPositionGenerator;

import java.util.HashSet;
import java.util.Set;

public class GridFactoryImpl implements GridFactory{

    @Override
    public Grid createEmptyGrid(int gridSize) {
        return new GridImpl(gridSize);
    }

    @Override
    public Grid createGridWithRandomMines(int gridSize, int mineNumber) throws IllegalArgumentException {
        if(mineNumber > gridSize*gridSize){
            throw new IllegalArgumentException();
        }
        Set<Pair<Integer,Integer>> minePositions = new HashSet<>();
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator();
        while(minePositions.size() != mineNumber){
            minePositions.add(randomPositionGenerator.generatePosition(gridSize));
        }
        return new GridImpl(gridSize, minePositions);
    }

    @Override
    public Grid createGridWithPresetMinePositions(int gridSize, Set<Pair<Integer, Integer>> presetMinePosition) {
        return new GridImpl(gridSize, presetMinePosition);
    }




}
