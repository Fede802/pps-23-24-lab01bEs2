package e2.cell;


import e2.utils.Pair;

public class GameCell implements Entity, GameCellStatus, Cell {

    private final BaseCell cell;

    private final GameCellStatus gameCellStatus;

    private final EntityType entityType;

    public GameCell(int cellX, int cellY, EntityType entityType) {
        this.cell = new BaseCell(cellX,cellY);
        this.gameCellStatus = new GameCellStatusImpl();
        this.entityType = entityType;
    }

    @Override
    public Pair<Integer, Integer> getCellPosition() {
        return cell.getCellPosition();
    }

    @Override
    public boolean isSelected() {
        return gameCellStatus.isSelected();
    }

    @Override
    public boolean isFlagged() {
        return gameCellStatus.isFlagged();
    }

    @Override
    public void select() throws IllegalStateException {
        gameCellStatus.select();
    }

    @Override
    public void toggleFlag() {
        gameCellStatus.toggleFlag();
    }

    @Override
    public EntityType getEntityType() {
        return this.entityType;
    }
}
