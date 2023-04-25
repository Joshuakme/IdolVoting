package entity;

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

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
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

