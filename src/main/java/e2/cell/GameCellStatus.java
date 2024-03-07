package e2.cell;

public interface GameCellStatus {

    boolean isSelected();

    boolean isFlagged();

    void select() throws IllegalStateException;

    void toggleFlag();

}
