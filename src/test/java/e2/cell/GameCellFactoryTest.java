package e2.cell;

import e2.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameCellFactoryTest {

    private GameCellFactory gameCellFactory;

    @BeforeEach
    void initGameCellFactory(){
        this.gameCellFactory = new GameCellFactoryImpl();
    }

    @Test
    void mineIsCorrectlyCreated(){
        int cellX = 5;
        int cellY = 5;
        GameCell gameCell = this.gameCellFactory.createMine(cellX,cellY);
        assertAll(
                () -> assertEquals(EntityType.MINE, gameCell.getEntityType()),
                () -> assertEquals(new Pair<>(cellX,cellY), gameCell.getCellPosition())
        );
    }

    @Test
    void emptyIsCorrectlyCreated(){
        int cellX = 5;
        int cellY = 5;
        GameCell gameCell = this.gameCellFactory.createEmpty(cellX,cellY);
        assertAll(
                () -> assertEquals(EntityType.EMPTY, gameCell.getEntityType()),
                () -> assertEquals(new Pair<>(cellX,cellY), gameCell.getCellPosition())
        );
    }
}
