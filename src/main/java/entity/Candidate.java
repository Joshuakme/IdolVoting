package entity;

import adt.SortedList;
import adt.SortedListInterface;

/**
 *
 * @author Shia Chai Fen
 */

public class Candidate implements Comparable<Candidate>{
    private int candidateId;
    private String candidateName;
    private int voteCount;
    
    

    //Constructor
    public Candidate(int candidateId, String candidateName, int voteCount) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.voteCount = voteCount;
    }

    //Getter 
    public int getCandidateId() {
        return candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }
    
    public int getVoteCount() {
        return this.voteCount;
    }

    //Setter
    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }
    
    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    //toString
    @Override
    public String toString() {
        return "Candidate{" + "candidateId=" + candidateId + ", candidateName=" + candidateName + '}';
    }

    //
    @Override
        public int compareTo(Candidate otherCandidate) {
        // Compare candidates based on vote count
        return Integer.compare(otherCandidate.getVoteCount(), this.voteCount);
    }
}

