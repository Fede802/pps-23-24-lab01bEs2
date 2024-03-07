package e2.cell;

public class GameCellStatusImpl implements GameCellStatus {

    private boolean selected;

    private boolean flagged;

    @Override
    public boolean isSelected() {
        return this.selected;
    }

    @Override
    public boolean isFlagged() {
        return this.flagged;
    }

    @Override
    public void select() throws IllegalStateException{
        if(this.isSelected()){
            throw new IllegalStateException();
        }
        this.selected = true;
    }

    @Override
    public void toggleFlag() {
        this.flagged = !this.flagged;
    }

}
