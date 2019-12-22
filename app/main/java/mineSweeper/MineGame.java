package mineSweeper;

import java.io.Serializable;
import java.util.List;

public class MineGame implements Serializable {

    private MineManager mineManager;
    private int size;
    private List<Integer> positions;

    public MineManager getMineManager() {
        return mineManager;
    }

    public void setMineManager(MineManager mineManager) {
        this.mineManager = mineManager;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Integer> getPositions() {
        return positions;
    }

    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }
}
