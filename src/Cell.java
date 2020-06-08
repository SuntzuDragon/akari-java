public class Cell {

    private final MyJButton button;
    private final int r;
    private final int c;
    private String type = "unassigned";
    private int lightLevel = 0;

    public Cell(MyJButton buttonIn, int row, int col) {
        button = buttonIn;
        r = row;
        c = col;
    }

    public MyJButton getButton() {
        return button;
    }

    public String getType() {
        return type;
    }

    public void setType(String typeIn) {
        type = typeIn;
    }

    public int getLightLevel() {
        return lightLevel;
    }

    public void addLight() {
        lightLevel++;
    }

    public void subLight() {
        lightLevel--;
    }

    public int getRow() {
        return r;
    }

    public int getCol() {
        return c;
    }
}
