package e2;



import e2.cell.EntityType;
import e2.cell.GameCell;
import e2.cell.GameCellData;
import e2.grid.Grid;
import e2.grid.GridFactoryImpl;
import e2.utils.Pair;

import java.util.Optional;
import java.util.Set;

public class LogicsImpl implements Logics {

    private final Grid grid;
    private boolean gameLost;
    public LogicsImpl(int gridSize, int minesToPlace) {
        this.grid = new GridFactoryImpl().createGridWithRandomMines(gridSize,minesToPlace);
    }

    public LogicsImpl(int gridSize, Set<Pair<Integer, Integer>> presetMinePosition) {
        this.grid = new GridFactoryImpl().createGridWithPresetMinePositions(gridSize,presetMinePosition);
    }

    private int numberOfMinesAround(Pair<Integer, Integer> cellPosition) {
        return (int) this.grid.getNeighbours(cellPosition.getX(), cellPosition.getY()).stream().filter((cell) -> cell.getEntityType() == EntityType.MINE).count();
    }

    private Optional<ClickResult> handleCellClick(GameCell gameCell) {
        if (gameCell.getEntityType() == EntityType.MINE) {
            this.gameLost = true;
            return Optional.of(ClickResult.LOSE);
        }
        if (this.numberOfMinesAround(gameCell.getCellPosition()) == 0) {
            this.handleRecursiveAutoClick(gameCell);
        }
        if (this.allEmptyCellClicked()) {
            return Optional.of(ClickResult.WIN);
        }
        return Optional.empty();
    }

    private void handleRecursiveAutoClick(GameCell gameCell) {
        for (GameCell cell :
                this.grid.getNeighbours(gameCell.getCellPosition().getX(), gameCell.getCellPosition().getY())) {
            this.clickCell(cell.getCellPosition());
        }
    }

    private boolean allEmptyCellClicked() {
        for (int i = 0; i < this.grid.getSize(); i++) {
            for (int j = 0; j < this.grid.getSize(); j++) {
                GameCell gameCell = this.grid.getCellAt(i,j);
                if(gameCell.getEntityType() == EntityType.EMPTY && !gameCell.isSelected()){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ClickResult clickCell(Pair<Integer, Integer> cellPosition) {
        if(!gameLost) {
            GameCell gameCell = this.grid.getCellAt(cellPosition.getX(), cellPosition.getY());
            if (!gameCell.isSelected() && !gameCell.isFlagged()) {
                gameCell.select();
                return handleCellClick(gameCell).orElse(ClickResult.EMPTY);
            }
        }
        return ClickResult.EMPTY;
    }

    @Override
    public void toggleFlag(Pair<Integer, Integer> cellPosition) {
        GameCell gameCell = this.grid.getCellAt(cellPosition.getX(), cellPosition.getY());
        if(!this.gameLost && !gameCell.isSelected()){
            gameCell.toggleFlag();
        }
    }

    @Override
    public GameCellData getCellStatus(Pair<Integer, Integer> cellPosition) {
        return GameCellData.fromCell(this.grid.getCellAt(cellPosition.getX(),cellPosition.getY()), this.numberOfMinesAround(cellPosition));
    }


}
