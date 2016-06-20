package Authentication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserHandler implements Runnable {
    volatile File _userFile;
    volatile Boolean _stop;

    public UserHandler(File file) {
        _userFile = file;
        _stop = false;
    }

    synchronized public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(_userFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] user = line.split(",");
                users.add(new User(user[0], user[1]));
            }
        } catch (IOException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return users;
    }
    
    synchronized public boolean saveUser(String username, String password) {
        // Sanity check for user
        boolean userExists = checkUsername(username);
        if (!userExists) {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(_userFile, true)))) {
                out.println(username + "," + password);
                return true;
            } catch (IOException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } 
        }
        return false;
    }

    synchronized public boolean checkCredentials(String username, String password) {
        List<User> users = new ArrayList<>();
        users = getUsers();
        if (users == null) {
            return false;
        } else {
            for(User user : users){
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    synchronized public boolean checkUsername(String username) {
        List<User> users = new ArrayList<>();
        users = getUsers();
        if (users == null) {
            return false;
        } else {
            for(User user : users){
                if (username.equals(user.getUsername())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void run() {
        while (!_stop) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stop() {
        _stop = true;
    }
}