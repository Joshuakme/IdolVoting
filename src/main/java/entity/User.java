package entity;

import java.util.HashMap;

/**
 *
 * @author Joshua Koh Min En
 */
public class User {
    private int id;
    private String username;
    private String password;
    private int previousId = 1000;
    
    // Constructor
    public User() {
        this.id = previousId;
        this.username = String.valueOf(this.id);
        this.password = this.id + "abc";
        previousId++;
    }
    
    public User(String username, String password) {
        this.id = previousId;
        this.username = username;
        this.password = password;
        previousId++;
    }
    
    // Methods
    public boolean validateAccount(String username, String password) {
        if(this.username.equalsIgnoreCase(username)) {
            if(this.password.equals(password)) {
                return true;
            }
        }
        return false;
    }
}
