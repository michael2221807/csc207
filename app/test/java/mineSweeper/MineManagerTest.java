package mineSweeper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MineManagerTest {


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }
/**
 *Test whether initData would generate a map with 10 mines
 */
    @Test
    public void initData() {
        MineManager mineManager = new MineManager();
        int num = 0;
        mineManager.initData();

        for (int i = 0; i < mineManager.getSize(); i++) {
            for (int j = 0; j < mineManager.getSize(); j++) {
                if (mineManager.getMap()[i][j] == -1){
                    num += 1;
                }
            }
        }
        assertEquals(10, num);
    }

    @Test
    public void getMap() {
        MineManager mineManager = new MineManager();
        int[][] map = new int[10][10];
        int[][] map2 = new int[10][10];
        mineManager.setMap(map);
        for (int i = 0; i < mineManager.getSize(); i++) {
            for (int j = 0; j < mineManager.getSize(); j++) {
                assert mineManager.getMap()[i][j] == map2[i][j];
                }
            }
    }


    @Test
    public void setMap(){
        MineManager mineManager = new MineManager();
        int[][] map = new int[10][10];
        int[][] map2 = new int[10][10];
        mineManager.setMap(map);
        for (int i = 0; i < mineManager.getSize(); i++) {
            for (int j = 0; j < mineManager.getSize(); j++) {
                assert mineManager.getMap()[i][j] == map2[i][j];
            }
        }
    }

    @Test
    public void getSize() {
        MineManager mineManager = new MineManager();
        int size = 10;
        mineManager.setSize(size);
        assertEquals(10,mineManager.getSize());
    }

    @Test
    public void setSize() {
        MineManager mineManager = new MineManager();
        int size = 10;
        mineManager.setSize(size);
        assertEquals(10,mineManager.getSize());
    }

}