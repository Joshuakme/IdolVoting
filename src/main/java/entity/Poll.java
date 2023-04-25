package entity;

import adt.ArrayList;
import adt.HashMap;
import adt.ListInterface;
import adt.MapInterface;

/**
 *
 * @author Joshua Koh
 */
public class Poll implements Comparable<Poll> {
    // Data Attribute
    private String name;
    private ListInterface<Votee> voteeList;
    private PollStatus pollStatus;
    private boolean isOpen;
    
    // Constructor
    public Poll(String name) {
        this.name = name;
        this.voteeList = new ArrayList<>();
        this.pollStatus = new PollStatus();
        
        for(int i=0; i<voteeList.size(); i++) {
            MapInterface<Votee, Integer> voteCount = new HashMap<>();
            
            // Initialize the voteCount map 
            voteCount.put(voteeList.get(i), 0);
            
            pollStatus.setVoteCount(voteCount);
        }
        this.isOpen = false;
    }
    
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

    public ListInterface<Votee> getVoteeList() {
        return voteeList;
    }

    public PollStatus getStatus() {
        return pollStatus;
    }

    public boolean isOpen() {
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
    
    
    // Votee CRUD
    public void addVotee(Votee votee) {
        pollStatus.getVoteCount().put(votee, 0);
    }
    
    public void updateVotee(Votee oldVotee, Votee newVotee) {
        MapInterface<Votee, Integer> updatedMap = new HashMap<>();
        
        for (MapInterface.Entry<Votee, Integer> entry : pollStatus.getVoteCount().entrySet()) {
            if (entry.getKey().equals(oldVotee)) {
                // Copy the old value and replace by new key
                updatedMap.put(newVotee, entry.getValue());
            } else {
                updatedMap.put(entry.getKey(), entry.getValue());
            }
        }
        
        pollStatus.setVoteCount(updatedMap);
    }
    
    public void removeVotee(Votee votee) {
        pollStatus.getVoteCount().remove(votee);
    }
    
    public ListInterface<Votee> searchVotee(String voteeName) {
        if (voteeName == null || voteeName.isEmpty()) {
            return null;
        }
        
        ListInterface<Votee> matchedVoteeList = new ArrayList<>();

        for (MapInterface.Entry<Votee, Integer> pairEntry : pollStatus.getVoteCount().entrySet()) {
            if (pairEntry.getKey().getName().toUpperCase().contains(voteeName.toUpperCase())) {
                matchedVoteeList.add(pairEntry.getKey());
            }
        }

        return matchedVoteeList;
    }
    
    
    // Operation Methods
    public void addVote(Votee votee) {
        MapInterface<Votee, Integer> newVote = new HashMap<>();
        
        if (isOpen) {
            newVote.put(votee, pollStatus.getVoteCount().get(votee) + 1);
            
            pollStatus.setVoteCount(newVote);
        } else {
            throw new IllegalStateException("Poll is closed.");
        }
    }
     
    public void end() {
        this.isOpen = false;
    }

    
    // Comparable Methods
    @Override
    public int compareTo(Poll otherPoll) {
        // compare based on total number of votes
        int totalVotes = 0;
        for (int voteCount : pollStatus.getVoteCount().values()) {
            totalVotes += voteCount;
        }

        int otherTotalVotes = 0;
        for (int voteCount : otherPoll.pollStatus.getVoteCount().values()) {
            otherTotalVotes += voteCount;
        }

        return Integer.compare(otherTotalVotes, totalVotes);
    }
}

