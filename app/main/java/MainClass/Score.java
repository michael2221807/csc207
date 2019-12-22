package MainClass;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * This class represents the Score.
 */

public class Score implements Serializable, Comparable<Score> {
    private int score;
    private String userName;
    private String gameName;

    public Score(int score, String userName, String gameName) {
        this.score = score;
        this.userName = userName;
        this.gameName = gameName;
    }

    /**
     * This method returns the score.
     * @return An int representing the score.
     */

    public int getScore() {
        return score;
    }

    /**
     * Setter for score.
     * @param score An int representing score.
     */

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Getter for UserName.
     * @return The UserName as wanted.
     */

    public String getUsername() {
        return userName;
    }

    /**
     * Set the userName to given userName.
     * @param username A string of userName to be set.
     */

    public void setUsername(String username) {
        this.userName = username;
    }

    /**
     * Getter for the gameName.
     * @return A string representing the gameName.
     */

    public String getGameName() {
        return gameName;
    }

    /**
     * Set gameName to the string gameName.
     * @param gameName A string of gameName to be set.
     */

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * This method compares one score with current score.
     * @param o The score to be compared with.
     * @return An positive int if this Score has more score than Score o.
     */
    @Override
    public int compareTo(@NonNull Score o) {
        return o.getScore() - this.getScore();
    }
}
