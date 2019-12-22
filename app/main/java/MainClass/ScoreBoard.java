package MainClass;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  This class represents ScoreBoard.
 */

public class ScoreBoard implements Serializable {
    private ArrayList<Score> scoreList = new ArrayList<>();

    public ScoreBoard(){
    }
    /**
     * This method sorts the ArrayList of scores by score, from highest to lowest.
     * @param l An ArrayList of scores.
     */
    private void sortScore(ArrayList<Score> l){
        for (int j = 0; j < l.size() - 1; j++){
            for (int i = 0; i < l.size() - 1 - 1; i++){
                if (l.get(i).getScore() < l.get(i+1).getScore()){
                    Score tempSc = l.get(i);
                    l.set(i, l.get(i+1));
                    l.set(i+1, tempSc);
                }
            }
        }
    }

    /**
     * This method adds one score to the scoreList.
     * @param score The score to be added.
     */

    public void addScore(Score score){
        scoreList.add(score);
        sortScore(scoreList);
    }
    public boolean isEmpty(){
        return scoreList.size() == 0;
    }

    /**
     * Return an ArrayList containing all scores of the user provided in the given gameType, sorted
     * from highest to lowest.
     * @param userName A string representing the userName.
     * @param gameType A string representing the name of the game.
     * @return A sorted ArrayList containing all scores of the user in the given gameType.
     */

    public ArrayList<Score> getUserScoreBoard(String userName, String gameType){
        ArrayList<Score> listToReturn = new ArrayList<>();
        for(Score score: scoreList){
            if (score.getUsername().equals(userName) && score.getGameName().equals(gameType)){
                listToReturn.add(score);
            }
        }
        sortScore(listToReturn);
        return listToReturn;
    }

    /**
     * Return an ArrayList of scores of given gameType.
     * @param gameType A string representing the gameType.
     * @return A sorted ArrayList of all scores in the desired gameType.
     */

    public ArrayList<Score> getGameScoreBoard(String gameType){
        ArrayList<Score> temp = new ArrayList<>();
        for (Score score: scoreList){
            if(score.getGameName().equals(gameType)){
                temp.add(score);
            }
        }
        sortScore(temp);
        return temp;
    }
}