package mineSweeper;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * Managing the board, initializing the board with correct number of mines
 */
public class MineManager implements Serializable {
    /**
     * Indicate the current status of the button at [][]
     */
    protected int[][] map;
    /**
     * The size of the board
     */
    protected int size;

    /**
     * A set of integers which represent the locations of mines
     */
    Set<Integer> RandomMines;

    /**
     * Construct a new board with size of 10
     */
     MineManager(){
            map = new int[10][10];
            size = 10;

    }
    /**
     * Construct a new board with size i
     */
    MineManager(int i){
        map = new int[i][i];
        size = i;

    }

    /**
     * Initialize the board with correct number of mines
     */
    public void initData() {
        //first generate a board with map value of 0 at every location
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = 0;
            }
        }
        //set the map value to -1 on positions that are randomly generated
        RandomMines = getRandom();
        for (Integer integer : RandomMines) {
            int row = integer / size;
            int column = integer % size;
            map[row][column] = -1;
        }
        //for each position, check eight different sides around it and get the final value of that
        // position, where the value indicate how many mines are around that position
        for (int i = 0; i < size; i++) {
            //if the position is a mine itself, pass and check for the following position
            for (int j = 0; j < size; j++) {
                if (map[i][j] == -1)
                    continue;
                int sum = 0;
                //if the current position is not on the first column or row, check whether the
                // bottom left position has mine, if it has, then plus 1 to the value
                // of current position
                if (i != 0 && j != 0) {
                    if (map[i - 1][j - 1] == -1)
                        sum++;
                }
                //if the current position is not on the first column, check the postion on the left
                if (j != 0) {
                    if (map[i][j - 1] == -1)
                        sum++;
                }
                //if not on the first column or highest row, check the position on the top left
                if (j != 0 && i != (size-1)) {
                    if (map[i + 1][j - 1] == -1)
                        sum++;
                }
                //if not on the first row, check the position directly below
                if (i != 0) {
                    if (map[i - 1][j] == -1)
                        sum++;
                }
                //if not on the highest row, check the position directly above
                if (i != (size-1)) {
                    if (map[i + 1][j] == -1)
                        sum++;
                }
                //if not on the rightest column or first row, check the position on the bottom right
                if (j != (size-1) && i != 0) {
                    if (map[i - 1][j + 1] == -1)
                        sum++;
                }
                //if not on the rightest column, check the position on the right
                if (j != (size-1)) {
                    if (map[i][j + 1] == -1)
                        sum++;
                }
                //if not on the rightest column or highest row, check the position on the top right
                if (j != (size-1) && i != (size-1)) {
                    if (map[i + 1][j + 1] == -1)
                        sum++;
                }
                map[i][j] = sum;
            }
        }
//        //print out the board
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    /**
     * Randomly generate a set of integers
     * @return a set of integers of of size equals to the (size of board) ^2/10, each integer
     * represents the location of a mine
     */
    private Set<Integer> getRandom() {
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() != size*size/10) {
            int random = (int) (Math.random() * size*size);
            set.add(random);
        }
        return set;
    }

    /**
     * Return the int value at [][]
     * @return the int value at [][]
     */
    int[][] getMap(){
        return map;

    }

    /**
     * Set the map value
     * @param map the integer that indicate the situation at [][]
     */
    void setMap(int[][] map){
        this.map = map;
    }

    /**
     * Return the size of the board
     * @return int value indicating the size of the board
     */
    int getSize(){
        return size;
    }

    /**
     * Set the size of the board
     * @param size integer that indicate the size of the board
     */
    void setSize(int size){
        this.size = size;
    }

}
