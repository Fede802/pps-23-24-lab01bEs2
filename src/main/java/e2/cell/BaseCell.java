package e2.cell;


import e2.utils.Pair;

public class BaseCell implements Cell {

    private final Pair<Integer, Integer> position;

    public BaseCell(int cellX, int cellY) {
        this.position = new Pair<>(cellX, cellY);
    }

    @Override
    public Pair<Integer, Integer> getCellPosition() {
        return this.position;
    }
}
