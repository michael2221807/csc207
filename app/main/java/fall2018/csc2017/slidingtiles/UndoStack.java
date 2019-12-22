package fall2018.csc2017.slidingtiles;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 *The UndoStack can be used to save steps that user makes, and stack size is settable.
 * @param <T> generic type input.
 */
public class UndoStack<T> implements Serializable {

    /**
     * An List that used to save data.
     */
    public List<T> s;

    /**
     * The size of the undo stack.
     */
    private int i;


    /**
     * Contractor of an empty Undo Stack.
     * @param i the size of stack.
     */
    UndoStack(int i) {
        this.i = i;
        this.s = new ArrayList<>(i);


    }

    /**
     * Add an element to the stack, if the stack is full, remove the bottom element of the stack.
     * @param obj the input object.
     */
    void add1(T obj) {
        if (s.size() < i) {
            s.add(obj);
        } else {
            s.remove(0);
            s.add(obj);
        }
//        Log.e("", "Added!");
    }


    /**
     * Remove an element of the stack.
     * @return object input.
     */
    T remove1() {
        if (s.size() > 0) {
//            Log.e("", "Removed!" + Integer.toString(s.size()));
            return s.remove(s.size() - 1);
        } else throw new NoSuchElementException();

    }
}
