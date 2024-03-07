package e2.cell;

import e2.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CellTest {

    private final static int CELL_X = 5;

    private final static int CELL_Y = 5;

    private Cell cell;

    @BeforeEach
    void initCell(){
        this.cell = new BaseCell(CELL_X,CELL_Y);
    }

    @Test
    void cellPositionSetCorrectly(){
        assertEquals(new Pair<>(CELL_X,CELL_Y), this.cell.getCellPosition());
    }

}
