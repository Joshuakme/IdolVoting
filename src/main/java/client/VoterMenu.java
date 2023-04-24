/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;
import adt.LinkedList;
import adt.ListInterface;
import adt.MapInterface;
import entity.Poll;
import entity.Vote;
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
                     
                        castVote();
                    break;
                    
                 case 4:
                    int numberOfVoter = voterLinkedList.getNumberOfEntries(); //Obtain total voter involved 
                    System.out.println("The total number of voters currently is " + numberOfVoter + ".\n");
                    break;
                    
                 case 5:
                    // Remove a voter from the list
                        Voter removeableVoter = voter1;
                        boolean removed = removeableVoter.removeVoter(voterLinkedList);

                        if (removed) {
                            System.out.println("Successfully removed voter " + removeableVoter.getVoteeName() + " (" + removeableVoter.getVoterID() + ")");
                        } else {
                            System.out.println("Failed to remove voter " + removeableVoter.getVoteeName() + " (" + removeableVoter.getVoterID() + ")");
                        }
                        
                 default:
                }
            } while(choice != 5 && choice != 6);
            
        System.out.println("Thank you for using idol voting application");   
    }
    
    public static LinkedList<Voter> initVoter() {
        LinkedList<Voter> voterLinkedList = new LinkedList<>();
        
        Voter voter1 = new Voter("V1002","Joshua", "joshua@gmail.com", "Joshhh", "5678");
        Voter voter2 = new Voter("V1003","User404", "user404@gmail.com", "Invalid", "8888");
        
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
                for(int i = 0; i < voterLinkedList.size(); i++){
                    if(voterLinkedList.get(i).validateAccount(tempUsername, tempPassword)){
                        isLogged = true;
                        curVoter = voterLinkedList.get(i);
                        System.out.println("Login Successful!!");
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
    
    public static void castVote(){
        if(checkVoted()){
            System.out.println("Voter " + curVoter.getVoterID() + " has already voted!");      
       
        } else { //add vote if not yet vote 
            voteCastableVotee();
        }     
            
    }    
    
    public static void selectPoll(){
        
    }
    
    public static boolean checkVoted(){

        //Get list of voted Voter from votedList(from poll)
        Poll curVotingPoll = pollLinkedList.get(curVotingPollIndex);
        
        
        //Check and compare the voterID(get from main) with the voterId of list of voted Voter using contain
        for (MapInterface.Entry<Votee, ListInterface<Voter>> entry : curVotingPoll.getVotedList().entrySet()) {
            Votee votee = entry.getKey();
            ListInterface<Voter> votedList = entry.getValue();
            
            if (votedList.contains(curVoter)) {
                System.out.println("Voter " + curVoter.getVoterID() + " has already voted!");
                return false;
            } else { //add vote if not yet vote 
                pollLinkedList.get(curVotingPollIndex).getVotedList();

                //need increase vote number of voteCount in pollStatus

                System.out.println("Voter " + curVoter.getVoterID() + " has been verified and can vote.");
                return true;
            }
        }
        return false;
    }
    
    public static void voteCastableVotee(){
        Poll curVotingPoll = pollLinkedList.get(curVotingPollIndex);
        
        ListInterface<Votee> voteeList = curVotingPoll.getVoteeList();
        
        System.out.println("Idol List");
        System.out.println("================");
        for(int i = 0 ; i < voteeList.size(); i++){
            System.out.println((i+1) + ") " + voteeList.get(i).getName());
        }

        System.out.printf("Cast your vote(1-" + voteeList.size()+ "): ");
        int idolChoice = sc.nextInt();
        sc.nextLine();
        
        //add vote number to Poll
        curVotingPoll.addVote(voteeList.get(idolChoice - 1));
        
        //add voter record into votedList(from Poll class)
        curVotingPoll.addVoter(voteeList.get(idolChoice - 1), curVoter);
                
        System.out.println("You has successfully voted for " + voteeList.get(idolChoice - 1).getName() + ".");       
            
    }
}
