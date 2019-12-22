package MainClass;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;


public class AccountManager {
    /**
    a map include user info
     */
    public Map<String, User> m = new HashMap<>();
    /**
    file name which contains the saves
     */
    private static final String fileName = "All_Account_info.ser";
    /**
    the accounts being manage.
    */
    private static AccountManager am;


    private AccountManager() {

    }

    /**
     * @return accountManager
     * return account manager if it is not null,
     * create new account manager if it is null.
     */
    public static AccountManager getAm(){
        if (am == null){
            am = new AccountManager();
        }
        return am;
    }

    /**
    save all account info if m is changing.
     */
    public void saveToFile1(Context context) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(fileName, context.MODE_PRIVATE));
            outputStream.writeObject(m);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    /**
    Add a new User to the map
     */
    public void signUp(String inputUsername, String inputPassword, Context context) {
        User newUser = new User(inputUsername, inputPassword);
        m.put(inputUsername, newUser);
        saveToFile1(context);
    }


    /**
    load all account info
     */
    private void loadFromFile1(Context context) {

        try {
            InputStream inputStream = context.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                 m = (HashMap<String, User>) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }


    /**
    check given username and password can be login.
     */
    public boolean login(String inputUsername, String inputPassword, Context context) {
        loadFromFile1(context);
        if (m.containsKey(inputUsername)){
            return m.get(inputUsername).password.equals(inputPassword);
        }
        return false;

    }

    /**
    return if username can be added
     */
    public boolean contains(String inputUsername, Context context) {
        loadFromFile1(context);
        if (m.isEmpty()){
            return false;
        }

        return m.containsKey(inputUsername);
    }

    /**
    return User by given userName
     */
    public User getUser(String userName, Context context){
        loadFromFile1(context);
        if (!m.isEmpty()){
            return m.get(userName);
        }
        return null;

    }

    /**
     tear down the existing AccountManager.
     */
    public void tearDown(){
        am  = null;

    }


}
