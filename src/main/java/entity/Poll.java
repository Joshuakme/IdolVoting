package entity;

import adt.HashMap;
import adt.LinkedList;
import adt.ListInterface;
import adt.MapInterface;

/**
 *
 * @author Joshua Koh
 */
public class Poll {
    // Data Attribute
    private String name;
    private ListInterface<Votee> voteeList;
    private PollStatus pollStatus;
    private MapInterface<Votee, ListInterface<Voter>> votedList; //Add new
    private boolean isOpen;
    
    // Constructor
    public Poll(String name, ListInterface<Votee> voteeList) {
        this.name = name;
        this.voteeList = voteeList;
        this.pollStatus = new PollStatus();
        
        for(int i=0; i<voteeList.size(); i++) {
            MapInterface<Votee, Integer> voteCount = new HashMap<>();
            
            // Initialize the voteCount map 
            voteCount.put(voteeList.get(i), 0);
            
            pollStatus.setVoteCount(voteCount);
        }
        this.isOpen = false;
    }
    
    // Getter
    public String getName() {
        return name;
    }

    //By cheeSheng modified
    public ListInterface<Votee> getVoteeList() {
        ListInterface<Votee> voteeList = new LinkedList<>();
        
        for (MapInterface.Entry<Votee, ListInterface<Voter>> entry : getVotedList().entrySet()) {
            voteeList.add(entry.getKey());
        }
        return voteeList;
    }

    public PollStatus getPollStatus() {
        return pollStatus;
    }

    public boolean isIsOpen() {
        return isOpen;
    }
    
    
    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setVoteeList(ListInterface<Votee> voteeList) {
        this.voteeList = voteeList;
    }

    public void setPollStatus(PollStatus pollStatus) {
        this.pollStatus = pollStatus;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public MapInterface<Votee, ListInterface<Voter>> getVotedList() {
        return votedList;
    }

    public void setVotedList(MapInterface<Votee, ListInterface<Voter>> votedList) {
        this.votedList = votedList;
    }
    
    
    
    
    // Methods
    public void addVote(Votee votee) {
        MapInterface<Votee, Integer> newVote = new HashMap<>();
        
        if (isOpen) {
            newVote.put(votee, pollStatus.getVoteCount().get(votee) + 1);
            
            pollStatus.setVoteCount(newVote);
        } else {
            throw new IllegalStateException("Poll is closed.");
        }
    }
    
    //By cheeSheng modified
    public void addVoter(Votee votee, Voter voter){
        votedList.get(votee).add(voter);
    }
    
    public PollStatus getStatus() {
        return pollStatus;
    }
    
    public boolean isOpen() {
        return isOpen;
    }
    
    public void end() {
        this.isOpen = false;
    }
}

