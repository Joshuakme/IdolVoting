/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;
import adt.LinkedList;
import adt.ListInterface;
import adt.MapInterface;
import entity.Poll;
import entity.Votee;
import entity.Voter;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class VoterMenu {
    private static LinkedList<Voter> voterLinkedList  = new LinkedList<>();
    private static LinkedList<Poll> pollLinkedList  = new LinkedList<>();
    private static Scanner sc = new Scanner(System.in);
    private static boolean isLogged = false;
    private static Voter curVoter; 
    private static int curVoterIndex; 
    private static int curVotingPollIndex;
    
    public static void main(String[] args) {

        voterLinkedList = initVoter();
        
        //Menu
        //default Value
        int choice = 1;
        boolean loginExist = false;
        
            do{
                System.out.printf("================== VOTER MENU =================\n");
                System.out.printf("|| 1.Register Voter\n");
                System.out.printf("|| 2.Login Voter\n");
                System.out.printf("|| 3.Cast Vote\n");
                System.out.printf("|| 4.View total number of voters participated\n");
                System.out.printf("|| 5.Delete Account\n");
                System.out.printf("|| 6.Back to Main Menu\n");
                System.out.printf("===============================================\n");   
                System.out.printf("==>Enter your choice(1-4): ");
                choice = sc.nextInt();
                sc.nextLine();
                
                switch(choice){
                 case 1:
                    registerVoter();
                    break;
                    
                 case 2:
                    loginVoter();
                    break;
                    
                 case 3:
                    //select Poll
                    selectPoll();

                    //cast Vote
                    castVote();
                    break;
                    
                 case 4:
                    //View number of voters
                    int numberOfVoter = voterLinkedList.getNumberOfEntries(); //Obtain total voter involved 
                    System.out.println("The total number of voters currently is " + numberOfVoter + ".\n");
                    break;
                    
                 case 5:
                    // Remove a voter from the list
                    deleteVoterSelf();
                    break;
                    
                 default:
                }
            } while(choice != 5 && choice != 6);
            
        System.out.println("Thank you for using idol voting application");   
    }
    
    public static LinkedList<Voter> initVoter() {
        LinkedList<Voter> voterLinkedList = new LinkedList<>();
        
        Voter voter1 = new Voter("V1002","Joshua", "joshua@gmail.com", "Joshhh", "123");
        Voter voter2 = new Voter("V1003","User404", "user404@gmail.com", "Invalid", "404");
        
        voterLinkedList.add(voter1);
        voterLinkedList.add(voter2);  
        
        return voterLinkedList;
        
    }
    
    public static void registerVoter(){
        System.out.printf("\nUser Registration\n");
        System.out.printf("Username: ");
        String tempUsername = sc.nextLine();
        System.out.printf("Password: ");
        String tempPassword = sc.nextLine();

        System.out.printf("\nFill in your User Detail");
        System.out.printf("\nReal Name: ");
        String tempName = sc.nextLine();
        System.out.printf("Email: ");
        String tempEmail = sc.nextLine();


        Voter tempVoter = new Voter("V1003",tempName, tempEmail, tempUsername, tempPassword);
        voterLinkedList.add(tempVoter);  
    }
    
    public static void loginVoter(){
        int loginTry = 0;
        //Check if user logged in
        if(!isLogged){ //login only attempt less than 3 times
            while(!isLogged  && loginTry < 3){
                System.out.printf("User Login\n");
                System.out.printf("Username: ");
                String tempUsername = sc.nextLine();
                System.out.printf("Password: ");
                String tempPassword = sc.nextLine();

                //Verify login detail with linked list contain
                for(int i = 1; i < voterLinkedList.getNumberOfEntries(); i++){
                    if(voterLinkedList.getEntry(i).validateAccount(tempUsername, tempPassword)){
                        isLogged = true;
                        curVoter = voterLinkedList.getEntry(i);
                        System.out.println("Login Successful!!");
                        curVoterIndex = i;
                    }
                }
                if(!isLogged){

                    System.out.printf("Login Failed, try again...\n");
                    loginTry++;   
                }
   
            }
        }else{
            System.out.println("Voter already login.");
        }
    }
    
    public static void selectPoll(){
        if(pollLinkedList.isEmpty()){ //if poll empty
            System.out.println("There is no poll available to vote currently... (back menu)");
        } else{
            System.out.println("===========Poll List============");
            for(int i = 1 ; i < pollLinkedList.getNumberOfEntries(); i++){
                System.out.println("||" + i + ") " + pollLinkedList.getEntry(i).getName() );
            }
            
            System.out.println("=> Select the poll to vote ( 1 - " + pollLinkedList.getNumberOfEntries() + " ): ");
            curVotingPollIndex = sc.nextInt();
        }
    }
        
    public static boolean checkVoted(){
        //Get list of voted Voter from votedList(from poll)
        Poll curVotingPoll = pollLinkedList.getEntry(curVotingPollIndex);
        
        
        //Check and compare the voterID(get from main) with the voterId of list of voted Voter using contain
        for (MapInterface.Entry<Votee, ListInterface<Voter>> entry : curVotingPoll.getVotedList().entrySet()) {
            Votee votee = entry.getKey();
            ListInterface<Voter> votedList = entry.getValue();
            
            if (votedList.contains(curVoter)) {
                System.out.println("Voter " + curVoter.getVoterID() + " has already voted!");
                return false;
            } else { //add vote if not yet vote 
                pollLinkedList.getEntry(curVotingPollIndex).getVotedList();

                //need increase vote number of voteCount in pollStatus

                System.out.println("Voter " + curVoter.getVoterID() + " has been verified and can vote.");
                return true;
            }
        }
        return false;
    }
    
    public static void castVote(){
        if(checkVoted()){ //if voter already voted on specified poll
            System.out.println("Voter " + curVoter.getVoterID() + " has already voted!");      
       
        } else { //add vote if not yet vote 
            voteCastableVotee();
        }     
            
    }    
     
    public static void voteCastableVotee(){
        Poll curVotingPoll = pollLinkedList.getEntry(curVotingPollIndex);
        
        ListInterface<Votee> voteeList = curVotingPoll.getVoteeList();
        
        System.out.println("Idol List");
        System.out.println("=============================");
        for(int i = 1 ; i < voteeList.getNumberOfEntries(); i++){
            System.out.println((i) + ") " + voteeList.getEntry(i).getName());
        }

        System.out.printf("Cast your vote(1-" + voteeList.getNumberOfEntries()+ "): ");
        int idolChoice = sc.nextInt();
        sc.nextLine();
        
        //add vote number to Poll
        curVotingPoll.addVote(voteeList.getEntry(idolChoice));
        
        //add voter record into votedList(from Poll class)
        curVotingPoll.addVoter(voteeList.getEntry(idolChoice), curVoter);
                
        System.out.println("You has successfully voted for " + voteeList.getEntry(idolChoice).getName() + ".");       
            
    }
    
    public static void deleteVoterSelf(){
        System.out.println("===========Account Delete Confirmation===========");
        System.out.println("|| Once you delete your account, you won't     ||");
        System.out.println("|| be able to view or vote for the idol.       ||");
        System.out.println("|| ____________________________________________||");
        System.out.println("|| Are you sure to delete? (\"Y\" to confirm: ");
        char deleteAccChoice  = sc.next().charAt(1);
        
        if(deleteAccChoice == 'Y' || deleteAccChoice == 'y'){
            System.out.println("Voter " + voterLinkedList.getEntry(curVoterIndex).getVoteeName() 
                    + "(ID: " + voterLinkedList.getEntry(curVoterIndex).getVoterID() + ") has successfully deleted.");
            voterLinkedList.remove(curVoterIndex); 
        }
        System.out.println("\nBack to menu....");
    }
}
