package entity;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Joshua Koh Min En
 */
public class PollStatus {
    private Map<Votee, Integer> voteCount;
    private long timeRemaining;
    
    public PollStatus() {
        this.voteCount = new HashMap<>();
        this.timeRemaining = 0;
    }
    
    public Map<Votee, Integer> getVoteCount() {
        return voteCount;
    }
    
    public void setVoteCount(Map<Votee, Integer> voteCount) {
        this.voteCount = voteCount;
    }
    
    public long getTimeRemaining() {
        return timeRemaining;
    }
    
    public void setTimeRemaining(long timeRemaining) {
        this.timeRemaining = timeRemaining;
    }
}
