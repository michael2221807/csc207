package fall2018.csc2017.slidingtiles;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import android.util.Log;

public class UndoStackTest {
    private UndoStack<Integer> undoStack;

    @Before
    public void setUp() throws Exception {
        undoStack = new UndoStack<>(3);
    }

    @After
    public void tearDown() throws Exception {
        undoStack = null;
    }

    @Test
    public void add1() {
        undoStack.add1(1);
        undoStack.add1(2);
        undoStack.add1(3);
        undoStack.add1(4);
        assert undoStack.remove1() == 4;
        assert undoStack.remove1() == 3;
        assert undoStack.remove1() == 2;
        assert undoStack.s.size() == 0;


    }

    @Test
    public void remove1() {
        undoStack.add1(1);
        undoStack.add1(2);
        undoStack.add1(3);
        undoStack.add1(4);
        assert undoStack.remove1() == 4;
        assert undoStack.remove1() == 3;
        assert undoStack.remove1() == 2;
        assert undoStack.s.size() == 0;
    }
}