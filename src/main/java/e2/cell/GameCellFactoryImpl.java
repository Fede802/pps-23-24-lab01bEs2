package e2.cell;

public class GameCellFactoryImpl implements GameCellFactory {

    @Override
    public GameCell createMine(Integer cellX, Integer cellY) {
        return new GameCell(cellX,cellY,EntityType.MINE);
    }

    @Override
    public GameCell createEmpty(Integer cellX, Integer cellY) {
        return new GameCell(cellX,cellY,EntityType.EMPTY);
    }

}
