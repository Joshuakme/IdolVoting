package entity;

import adt.ArrayList;
import adt.ListInterface;
import adt.MapInterface;
import adt.HashMap;

/**
 *
 * @author Joshua Koh Min En
 */
public class Admin extends User {
    // Data 
    private MapInterface<String,Votee> votees;
    private Poll poll;

    // Constructor
    public Admin() {
        this("admin", "admin123");
        this.votees = new HashMap<>();
    }
    
    public Admin(String username, String password) {
        super(username, password);
    }
  
    
    // Getter
    public MapInterface<String,Votee> getVotees() {
        return votees;
    }

    public Poll getPoll() {
        return poll;
    }
    
    
    // Setter 
    public void setVotees(MapInterface<String, Votee> votees) {
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
    
    public ListInterface<Votee> searchVotee(String voteeName) {
        if (voteeName == null || voteeName.isEmpty()) {
            return null;
        }
        
        ListInterface<Votee> matchedVoteeList = new ArrayList<>();

        for (MapInterface.Entry<String, Votee> pairEntry : votees.entrySet()) {
            if (pairEntry.getKey().toUpperCase().contains(voteeName.toUpperCase())) {
                matchedVoteeList.add(pairEntry.getValue());
            }
        }

        return matchedVoteeList;
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
