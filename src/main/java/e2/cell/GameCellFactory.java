package e2.cell;

public interface GameCellFactory {

    GameCell createMine(Integer cellX, Integer cellY);

    GameCell createEmpty(Integer cellX, Integer cellY);

}
