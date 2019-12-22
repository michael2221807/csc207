package MainClass;


import java.io.Serializable;
import connect4game.GridGame;
import fall2018.csc2017.slidingtiles.TilesGame;
import mineSweeper.MineGame;


public class User implements Serializable {
    /**
    user name
    */
    public String userName;
    /**
    save all account info if m is changing.
    */
    public String password;
    /**
    user's complexity choice
     */
    private int UserComplexity = 4;

    private TilesGame TilesGames;

    private GridGame gridGame;

    private MineGame mineGame;

    /**
    set user's scoreboard
     */
    public void setTileGame(TilesGame Tile){
        this.TilesGames = Tile;
    }

    public TilesGame getTilesGame(){
        return this.TilesGames;
    }


    public void setComplexity(int Complexity){
        this.UserComplexity = Complexity;
    }
    /**
    ini user.
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    /**
    get users complexity
     */
    public int getUserComplexity() {
        return UserComplexity;
    }

    /**
     * get the GirdGame saved in User
     * @return - the Girdgame saved in User
     */
    public GridGame getGridGame() {
        return gridGame;
    }

    /**
     * set the GirdGame for User
     * @param gridGame the GirdGame to save in User
     */
    public void setGridGame(GridGame gridGame) {
        this.gridGame = gridGame;
    }

    /**
     * get the MineGame stored in User
     * @return
     */
    public MineGame getMineGame() {
        return mineGame;
    }

    /**
     * store the Mine Game in User
     * @param mineGame the MineGame that is to saved in User
     */
    public void setMineGame(MineGame mineGame) {
        this.mineGame = mineGame;
    }
}
