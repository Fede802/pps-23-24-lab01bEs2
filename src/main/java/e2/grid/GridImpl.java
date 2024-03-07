package e2.grid;

import e2.utils.Pair;
import e2.cell.GameCell;
import e2.cell.GameCellFactory;
import e2.cell.GameCellFactoryImpl;

import java.util.*;
import java.util.stream.Collectors;

public class GridImpl implements Grid {

    private final Set<GameCell> grid;

    private final int gridSize;

    public GridImpl(int gridSize) {
        this(gridSize, Set.of());
    }

    public GridImpl(int gridSize, Set<Pair<Integer, Integer>> minePositions) {
        this.grid = new HashSet<>();
        this.gridSize = gridSize;
        this.initGrid(gridSize,minePositions);
    }

    private void initGrid(int gridSize, Set<Pair<Integer, Integer>> minePositions) {
        GameCellFactory gameCellFactory = new GameCellFactoryImpl();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if(minePositions.contains(new Pair<>(i,j))){
                    this.grid.add(gameCellFactory.createMine(i, j));
                }else {
                    this.grid.add(gameCellFactory.createEmpty(i, j));
                }
            }
        }
    }

    private boolean isNeighbours(Pair<Integer, Integer> firstCell, Pair<Integer, Integer> secondCell) {
        int distanceX = Math.abs(firstCell.getX() - secondCell.getX());
        int distanceY = Math.abs(firstCell.getY() - secondCell.getY());
        return distanceX <= 1 && distanceY <= 1 && !firstCell.equals(secondCell);
    }

    @Override
    public int getSize() {
        return this.gridSize;
    }

    @Override
    public GameCell getCellAt(int cellX, int cellY) throws IndexOutOfBoundsException {
        Pair<Integer,Integer> cellPosition = new Pair<>(cellX,cellY);
        return this.grid.stream()
                .filter((cell)->(cell.getCellPosition().equals(cellPosition)))
                .findFirst()
                .orElseThrow(IndexOutOfBoundsException::new);
    }


    @Override
    public Set<GameCell> getNeighbours(int cellX, int cellY) {
        Pair<Integer, Integer> cellPosition = this.getCellAt(cellX,cellY).getCellPosition();
        return this.grid.stream()
                .filter((cell)->(isNeighbours(cell.getCellPosition(),cellPosition)))
                .collect(Collectors.toSet());
    }

}
