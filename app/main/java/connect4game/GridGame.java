package connect4game;


import java.io.Serializable;

public class GridGame implements Serializable {

    private GridManager gridManager;

    private GridManager gridManagerPVP;

    /**
     * Get the gridManager as wanted.
     * @return The gridManager.
     */

    public GridManager getGridManager() {
        return gridManager;
    }

    /**
     * Set gridManager to GridManager gridManager.
     * @param gridManager The gridManager to be set.
     */

    public void setGridManager(GridManager gridManager) {
        this.gridManager = gridManager;
    }

    /**
     * Get the gridManager for PVP.
     * @return The gridManagerPVP.
     */

    public GridManager getGridManagerPVP() {
        return gridManagerPVP;
    }

    /**
     * Set gridManagerPVP to GridManager gridManager.
     * @param gridManagerPVP The gridManager to be set.
     */

    public void setGridManagerPVP(GridManager gridManagerPVP) {
        this.gridManagerPVP = gridManagerPVP;
    }
}
