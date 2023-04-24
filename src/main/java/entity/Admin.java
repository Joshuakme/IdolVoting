package entity;

import adt.ArrayList;
import adt.ListInterface;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Joshua Koh Min En
 */
public class Admin extends User {
    // Data 
    private Map<String,Votee> votees;
    private Poll poll;

    // Constructor
    public Admin() {
        this("admin", "admin123");
    }
    
    public Admin(String username, String password) {
        super(username, password);
    }
  
    
    // Getter
    public Map<String,Votee> getVotees() {
        return votees;
    }

    public Poll getPoll() {
        return poll;
    }
    
    
    // Setter 
    public void setVotees(Map<String, Votee> votees) {
        this.votees = votees;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }
    
    
    // Votee Methods
    public void createVotee(Votee votee) {
        votees.put(votee.getId(), votee);
    }
    
    public void updateVotee(Votee votee) {
        if(votees.get(votee.getId()).getId().equals((votee.getId()))) {
            votees.put(votee.getId(), votee);
        }
    }
    
    public void deleteVotee(String voteeId) {
        votees.remove(voteeId);
    }

    
    // Poll Methods
    public void startPoll(String pollName) {
        ListInterface<Votee> voteeList = new ArrayList<>();
        
            for(Votee votee : votees.values()) {
                voteeList.add(votee);
            }
        
        poll = new Poll(pollName, voteeList);
        // start accepting vot+es
    }
    
    public void endPoll() {
        poll.end();
        // stop accepting votes
    }
    
    public PollStatus viewPollStatus() {
        return poll.getStatus();
    }
}
