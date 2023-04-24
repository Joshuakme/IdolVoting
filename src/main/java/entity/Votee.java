package entity;

/**
 *
 * @author Joshua Koh Min En
 */
public class Votee {
    private String id;
    private String name;
    private String description;
    private int votes;
    
    public Votee(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.votes = 0;
    }


    // Getter
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public int getVotes() {
        return votes;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void addVote() {
        votes++;
    }
}
