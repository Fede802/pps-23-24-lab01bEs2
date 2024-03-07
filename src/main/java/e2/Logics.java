package e2;


import e2.cell.GameCellData;
import e2.utils.Pair;

public interface Logics {

    ClickResult clickCell(Pair<Integer, Integer> cellPosition);

    void toggleFlag(Pair<Integer, Integer> cellPosition);

    GameCellData getCellStatus(Pair<Integer, Integer> cellPosition);

}
