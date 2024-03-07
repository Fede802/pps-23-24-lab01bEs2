package e2;


import e2.utils.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class LogicsTest{

    private final static int GRID_SIZE = 7;
    private Logics logics;

    @BeforeEach
    void initLogics(){
        this.logics = new LogicsImpl(GRID_SIZE,0);
    }

    private void wrongCellTester(Consumer<Pair<Integer,Integer>> functionToTest) {
        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class,()->functionToTest.accept(new Pair<>(-1,0))),
                () -> assertThrows(IndexOutOfBoundsException.class,()->functionToTest.accept(new Pair<>(GRID_SIZE,0))),
                () -> assertThrows(IndexOutOfBoundsException.class,()->functionToTest.accept(new Pair<>(0,-1))),
                () -> assertThrows(IndexOutOfBoundsException.class,()->functionToTest.accept(new Pair<>(0,GRID_SIZE)))
        );
    }

    @Test
    void createLogicWithTooMuchMinesHandling(){
        assertThrows(IllegalArgumentException.class, () -> new LogicsImpl(GRID_SIZE,GRID_SIZE*GRID_SIZE+1));
    }

    @Test
    void clickEmptyCell(){
        Set<Pair<Integer,Integer>> presetMinePosition = Set.of(
                new Pair<>(0,1),
                new Pair<>(1,0),
                new Pair<>(1,1)
        );
        this.logics = new LogicsImpl(GRID_SIZE,presetMinePosition);
        Pair<Integer, Integer> cell = new Pair<>(0,0);
        assertAll(
                () -> assertFalse(this.logics.getCellStatus(cell).isMine()),
                () -> assertEquals(ClickResult.EMPTY,this.logics.clickCell(cell))
        );
    }

    @Test
    void clickMineCell(){
        Pair<Integer, Integer> cell = new Pair<>(0,0);
        this.logics = new LogicsImpl(GRID_SIZE,GRID_SIZE*GRID_SIZE);
        assertAll(
                () -> assertTrue(this.logics.getCellStatus(cell).isMine()),
                () -> assertEquals(ClickResult.LOSE,this.logics.clickCell(cell))
        );

    }

    @Test
    void clickActionsOnWrongCell(){
        this.wrongCellTester((p) -> this.logics.clickCell(p));
        this.wrongCellTester((p) -> this.logics.getCellStatus(p).isClicked());
    }

    @Test
    void clickAllEmptyCell(){
        Set<Pair<Integer,Integer>> presetMinePosition = Set.of(
                new Pair<>(0,1),
                new Pair<>(1,0),
                new Pair<>(1,1)
        );
        this.logics = new LogicsImpl(GRID_SIZE,presetMinePosition);
        for (int i = 2; i < GRID_SIZE; i++) {
            for (int j = 2; j < GRID_SIZE; j++) {
                    this.logics.clickCell(new Pair<>(i, j));
            }
        }
        assertEquals(ClickResult.WIN,this.logics.clickCell(new Pair<>(0,0)));
    }

    @Test
    void allCellInitiallyNotFlagged(){
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if(!(i == 0 && j == 0)) {
                    assertFalse(this.logics.getCellStatus(new Pair<>(i, j)).isFlagged());
                }
            }
        }
    }

    @Test
    void flagCell(){
        Pair<Integer, Integer> cell = new Pair<>(0,0);
        this.logics.toggleFlag(cell);
        assertTrue(this.logics.getCellStatus(cell).isFlagged());
    }

    @Test
    void resetFlag(){
        Pair<Integer, Integer> cell = new Pair<>(0,0);
        this.logics.toggleFlag(cell);
        this.logics.toggleFlag(cell);
        assertFalse(this.logics.getCellStatus(cell).isFlagged());
    }

    @Test
    void flagActionsOnWrongCell(){
        this.wrongCellTester((p) -> this.logics.toggleFlag(p));
        this.wrongCellTester((p) -> this.logics.getCellStatus(p).isClicked());
    }

    @Test
    void allCellInitiallyNotClicked(){
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if(!(i == 0 && j == 0)) {
                    assertFalse(this.logics.getCellStatus(new Pair<>(i, j)).isClicked());
                }
            }
        }
    }

    @Test
    void clickCell(){
        Pair<Integer, Integer> cell = new Pair<>(0,0);
        this.logics.clickCell(cell);
        assertTrue(this.logics.getCellStatus(cell).isClicked());
    }

    @Test
    void doubleClickCell(){
        Pair<Integer, Integer> cell = new Pair<>(0,0);
        this.logics.clickCell(cell);
        assertDoesNotThrow(() -> this.logics.clickCell(cell));
    }

    @Test
    void minesAroundCheckerWorksCorrectlyInEmptyGrid(){
        this.logics = new LogicsImpl(GRID_SIZE,0);
        assertAll(
                () -> assertEquals(0, this.logics.getCellStatus(new Pair<>(0,0)).numberOfMinesAround()),
                () -> assertEquals(0, this.logics.getCellStatus(new Pair<>(GRID_SIZE-1,0)).numberOfMinesAround()),
                () -> assertEquals(0, this.logics.getCellStatus(new Pair<>(0,GRID_SIZE-1)).numberOfMinesAround()),
                () -> assertEquals(0, this.logics.getCellStatus(new Pair<>(GRID_SIZE-1,GRID_SIZE-1)).numberOfMinesAround()),

                () -> assertEquals(0, this.logics.getCellStatus(new Pair<>(0,1)).numberOfMinesAround()),
                () -> assertEquals(0, this.logics.getCellStatus(new Pair<>(1,0)).numberOfMinesAround()),
                () -> assertEquals(0, this.logics.getCellStatus(new Pair<>(GRID_SIZE-1,GRID_SIZE-2)).numberOfMinesAround()),
                () -> assertEquals(0, this.logics.getCellStatus(new Pair<>(GRID_SIZE-2,GRID_SIZE-1)).numberOfMinesAround()),

                () -> assertEquals(0,this.logics.getCellStatus(new Pair<>(1,1)).numberOfMinesAround())
        );
    }

    @Test
    void minesAroundCheckerWorksCorrectlyInFullGrid(){
        this.logics = new LogicsImpl(GRID_SIZE,GRID_SIZE*GRID_SIZE);
        assertAll(
                () -> assertEquals(3, this.logics.getCellStatus(new Pair<>(0,0)).numberOfMinesAround()),
                () -> assertEquals(3, this.logics.getCellStatus(new Pair<>(GRID_SIZE-1,0)).numberOfMinesAround()),
                () -> assertEquals(3, this.logics.getCellStatus(new Pair<>(0,GRID_SIZE-1)).numberOfMinesAround()),
                () -> assertEquals(3, this.logics.getCellStatus(new Pair<>(GRID_SIZE-1,GRID_SIZE-1)).numberOfMinesAround()),

                () -> assertEquals(5, this.logics.getCellStatus(new Pair<>(0,1)).numberOfMinesAround()),
                () -> assertEquals(5, this.logics.getCellStatus(new Pair<>(1,0)).numberOfMinesAround()),
                () -> assertEquals(5, this.logics.getCellStatus(new Pair<>(GRID_SIZE-1,GRID_SIZE-2)).numberOfMinesAround()),
                () -> assertEquals(5, this.logics.getCellStatus(new Pair<>(GRID_SIZE-2,GRID_SIZE-1)).numberOfMinesAround()),

                () -> assertEquals(8,this.logics.getCellStatus(new Pair<>(1,1)).numberOfMinesAround())
        );
    }

    @Test
    void chainEffectOnEmptyGrid(){
        assertEquals(ClickResult.WIN,this.logics.clickCell(new Pair<>(0,0)));
    }

    @Test
    void cannotFlagClickedCell(){
        Pair<Integer, Integer> cell = new Pair<>(0,0);
        this.logics.clickCell(cell);
        this.logics.toggleFlag(cell);
        assertFalse(this.logics.getCellStatus(cell).isFlagged());

    }

    @Test
    void cannotFlagCellWhenGameIsLost(){
        Pair<Integer, Integer> emptyCell = new Pair<>(0,0);
        Pair<Integer, Integer> mineCell = new Pair<>(0,1);
        this.logics = new LogicsImpl(GRID_SIZE,Set.of(mineCell));
        this.logics.clickCell(mineCell);
        this.logics.toggleFlag(emptyCell);
        assertFalse(this.logics.getCellStatus(emptyCell).isFlagged());

    }

    @Test
    void cannotClickCellWhenGameIsLost(){
        Pair<Integer, Integer> emptyCell = new Pair<>(0,0);
        Pair<Integer, Integer> mineCell = new Pair<>(0,1);
        this.logics = new LogicsImpl(GRID_SIZE,Set.of(mineCell));
        this.logics.clickCell(mineCell);
        this.logics.clickCell(emptyCell);
        assertFalse(this.logics.getCellStatus(emptyCell).isClicked());

    }

}
