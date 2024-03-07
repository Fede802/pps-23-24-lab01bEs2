package e2.cell;

public record GameCellData(boolean isClicked, boolean isFlagged, boolean isMine, int numberOfMinesAround) {

    public static GameCellData fromCell(GameCell cell, int minesAround) {
        return new GameCellData(cell.isSelected(), cell.isFlagged(), cell.getEntityType() == EntityType.MINE, minesAround);
    }

}
