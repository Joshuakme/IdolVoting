package client;
import adt.SortedList;
import adt.LinkedList;
import adt.ArrayList;
import entity.Poll;
import entity.Voter;
import entity.Votee;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author Shia Chai Fen
 */

public class PollMenu {
    private static LinkedList<Voter> voterLinkedList  = new LinkedList<>();
    private static LinkedList<Poll> pollLinkedList  = new LinkedList<>();
    private static int curVotingPollIndex;
    public static Scanner input = new Scanner (System.in);
    
    public static void main(String []args){
        char selection;
        char contProcess = 'N';

        
        // Init List Variables
        voterLinkedList = initVoterList();
        pollLinkedList = initPollList();
        
        int loop = 0;
            // display menu
            do {
                System.out.println("\n\n\n\t------------------------------------------------------------------------------------------");
                System.out.println("\t|                                                                                              |");
                System.out.println("\t|            S I N G I N G    I D O L   V O T I N G   R E S U L T   S Y S T E M                |");
                System.out.println("\t|                                                                                              |");
                System.out.println("\t------------------------------------------------------------------------------------------------");
                System.out.println("\t|                                                                                              |");
                System.out.println("\t|      Select one :                                                                            |");
                System.out.println("\t|         1. Check Final Result                                                                |");
                System.out.println("\t|         2. Final Ranking                                                                     |");
                System.out.println("\t|            a. Edit Votee List                                                                |");
                System.out.println("\t|            b. Edit Poll                                                                      |");
                System.out.println("\t|         3. Back                                                                              |");
                System.out.println("\t------------------------------------------------------------------------------------------------");

                try {
                    System.out.print("\nEnter Your Option (1/a/b/3): ");
                    selection = input.next().charAt(0);
                    int pollIndex = getCurVotingPollIndex();
                    
                    
                    
                    
                    
                    switch (selection) {
                        case 1:
                            ArrayList<Votee> defaultVoteeResult = new ArrayList<>();
                            defaultVoteeResult = pollLinkedList.get(pollIndex).defaultRanking();
                            
                               result(defaultVoteeResult, pollIndex, 'A');
                               break;
                        
                        case 'A':
                            ArrayList<Votee> descVoteeResult = new ArrayList<>();
                            descVoteeResult = pollLinkedList.get(pollIndex).descRanking();
                             
                            result(descVoteeResult, pollIndex, 'B');
                             break;
                            
                        case 'B':
                            ArrayList<Votee> ascVoteeResult = new ArrayList<>();
                            ascVoteeResult = pollLinkedList.get(pollIndex).ascRanking();

                            result(ascVoteeResult, pollIndex, 'C');
                             break;
                        
                        case 3:
                             while (loop == 1);
                             
                        default:
                            System.out.println("\n==================================");
                            System.out.println("! Error input. Please try again. !");
                            System.out.println("==================================\n");
                            loop = 1;

                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n====================================");
                    System.out.println("! Invalid input. Please try again. !");
                    System.out.println("====================================\n");
                    loop = 1;
                }

            } while (loop == 1);
        
//            System.out.print("Do you wish to continue ? [Y|N] : ");
//            contProcess = input.next().charAt(0);

    }

    public static LinkedList<Voter> initVoterList() {
        LinkedList<Voter> voterList = new LinkedList<>();

        Voter v1 = new Voter(001, "Ong Lai Huat");
        Voter v2 = new Voter(002, "Choy San Yeh");
        Voter v3 = new Voter(003, "Yeoh Qi Lai");
        Voter v4 = new Voter(004, "Loo Oh Cui");
        Voter v5 = new Voter(005, "Lee Xiao Fii");

        voterList.add(v1);
        voterList.add(v2);
        voterList.add(v3);
        voterList.add(v4);
        voterList.add(v5);

        return voterList;
    }

    public static LinkedList<Poll> initPollList(){
        LinkedList<Poll> pollList = new LinkedList<>();
        
        Poll p1 = new Poll ("MoonByul");
        Poll p2 = new Poll ("Solar");
        Poll p3 = new Poll ("Hwasa");
        Poll p4 = new Poll ("WheeIn");
        
        return pollList;
    }
    
    //Display Result after the Poll is end (The result can't be review if the poll still processing)
    public static void result(ArrayList<Votee> voteeList, int pollIndex, char chooseRankType) {
        if (voteeList != null || !voteeList.isEmpty()) {
            switch (Character.toUpperCase(chooseRankType)) {
                case 'A':   //Default Rankin List
                    System.out.println("*********************************************************************************************");
                    System.out.println("*             R E S U L T  F O R  S I N G I N G  I D O L  C O M P E T I T I O N             *");
                    System.out.println("*********************************************************************************************");
                    System.out.printf("%-10s %-20s %-6s", "No", "Name", "Votes");
                    System.out.println("\n");
                    break;

                case 'B':    // Descending Ranking Result (The Most Famous to the Least)
                    System.out.println("*******************************************************************************************************");
                    System.out.println("*          R A N K I N G   R E S U L T  F O R  S I N G I N G  I D O L  C O M P E T I T I O N          *");
                    System.out.println("*******************************************************************************************************");
                    System.out.printf("%-10s %-20s %-6s", "Rank", "Name", "Votes");
                    System.out.println("\n");
                    break;
                    
                case 'C':   // Ascending Ranking Result (The Least Famous to the Most)
                    System.out.println("*************************************************************************");
                    System.out.println("*             S I N G I N G  I D O L  C O M P E T I T I O N             *");
                    System.out.println("*************************************************************************");
                    System.out.printf("%-10s %-20s %-6s", "No", "Name", "Votes");
                    System.out.println("\n");
                    break;
                    
                default: 
                    System.out.println("******************************");
                    System.out.println("      Invalid Character!      ");
                    System.out.println("******************************");
            }

        for(int i = 0; i< voteeList.size();i++){
                    System.out.printf("%-10s %-20s %-6d\n", (i+1) + ". " ,voteeList.get(i).getName(), pollLinkedList.get(pollIndex).getPollStatus().getVoteCount().get(voteeList.get(i)));
                }
            }
        else{
            System.out.println("***********************************************");
            System.out.println("*             The List is EMPTY!              *");
            System.out.println("***********************************************");
        }
    }
   
     public static int getCurVotingPollIndex() {
        boolean validCurVotingPollIndex = false;
        
        while(!validCurVotingPollIndex) {
            SortedList<Poll> availablePollList = getAvailablePolls();
                    
            System.out.println("Below is the list of available Polls");
        
            // Display Available Polls
            displayAvailablePolls();

            // Get input from user
            System.out.println("Please select a poll to proceed: ");
            int curVotingPollIndexInput = input.nextInt();
            input.nextLine();
            
            if(curVotingPollIndexInput > 0 && curVotingPollIndexInput <= availablePollList.size()) {
                // Proceed
                curVotingPollIndex = curVotingPollIndexInput;
                
            } else {
                // If invalid endPollInput

                System.err.println("\nPlease enter a number that is in the range of (1 - " + availablePollList.size() + "). Please try again\n");
            }
        }
        
        return -1;
    }
     
      public static void displayAvailablePolls() {
        // Variables Init
        SortedList<Poll> availablePollList = getAvailablePolls();
                    
        // Display the list of available polls
        System.out.println("\nBelow is the list of available Polls: ");
        System.out.printf("%4s %-20s","No.", "Poll Name");
        for(int i=0; i<availablePollList.size(); i++) {
            System.out.printf("%4s %-20s", (i+1) + ". ", availablePollList.get(i).getName());
        }
        System.out.println("\n");
    }
    
       public static SortedList<Poll> getAvailablePolls() {
        // Variables Init
        SortedList<Poll> availablePollList = new SortedList<>();

        // Get list of available polls
        for(int i=0; i<pollLinkedList.size(); i++) {
            if(pollLinkedList.get(i).isOpen()) {
                availablePollList.add(pollLinkedList.get(i));
            }
        }
        
        return availablePollList;
    }