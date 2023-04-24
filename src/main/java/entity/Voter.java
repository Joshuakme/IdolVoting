/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import adt.LinkedList;

/**
 *
 * @author Lenovo
 */
public class Voter extends User{
    private String voterID;
    private String voterName;
    private String email;
    
    public Voter(String voterID, String voterName, String email, String username, String password){
        super(username, password);
        this.voterID = voterID;
        this.voterName = voterName;
        this.email = email;
    }

    public String getVoterID() {
        return voterID;
    }

    public void setVoterID(String voterID) {
        this.voterID = voterID;
    }

    public String getVoteeName() {
        return voterName;
    }

    public void setVoteeName(String voteeName) {
        this.voterName = voteeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
