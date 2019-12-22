package fall2018.csc2017.slidingtiles;


import java.io.Serializable;


public class TilesGame implements Serializable {

    BoardManager BoardManager ;

    public void setBoardManager(BoardManager BoardManager){
        this.BoardManager = BoardManager;

    }

    public  BoardManager getBoardManager(){
        return this.BoardManager;
    };

}
