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
        int choice;
        
            do{
                System.out.println("================== VOTER MENU =================");
                System.out.println("|| 1.Register Voter");
                System.out.println("|| 2.Login/Logout Voter");
                System.out.println("|| 3.Cast Vote");
                System.out.println("|| 4.View total number of voters participated");
                System.out.println("|| 5.Modify Account Details");
                System.out.println("|| 6.Delete Account");
                System.out.println("|| 7.Back to Main Menu");
                System.out.println("===============================================");   
                System.out.printf("==>Enter your choice(1-7): ");
                choice = sc.nextInt();
                sc.nextLine();
                
                switch(choice){
                 case 1:
                    //Register Voter
                    registerVoter();
                    break;
                    
                 case 2:
                    //Login Voter
                    loginVoter();    
                    break;

                 case 3:
                    //Check if user logged in
                    if(!isLogged){ 
                        loginNotify();
                    }else{
                        //select Poll
                        selectPoll();

                        //cast Vote
                        castVote();
                    }
                    break;
                    
                 case 4:
                    if(!isLogged){ 
                        loginNotify();
                    }else{
                        //View number of voters
                        int numberOfVoter = voterLinkedList.getNumberOfEntries(); //Obtain total voter involved 
                        System.out.println("The total number of voters currently is " + numberOfVoter + ".\n");
                    }
                    break;
                    
                 case 5:
                    if (!isLogged) {
                        loginNotify();
                    }else{
                        // Modify voter's details from the list
                        updateVoterMenu();
                    }
                    break;
                
                 case 6:
                    if (!isLogged) {
                        loginNotify();
                    } else {
                        // Remove a voter from the list
                        deleteVoterSelf();
                    }
                    break;
                    
                 case 7:
                    // Back to User Menu;
                    break;

                 default:
                    System.out.println("Invalid choice, try again.");
                }
            } while(choice != 7);
            
        System.out.println("Back to User Menu...");   
    }
    
    public static LinkedList<Voter> initVoter() {
        LinkedList<Voter> voterLinkedList = new LinkedList<>();
        
        Voter voter1 = new Voter("V1001","Joshua", "joshua@gmail.com", "Joshhh", "123");
        Voter voter2 = new Voter("V1002","User404", "user404@gmail.com", "Invalid", "404");
        
        voterLinkedList.add(voter1);
        voterLinkedList.add(voter2);  
        
        return voterLinkedList;
        
    }
    
    public static void registerVoter(){
        System.out.println("\n=====User Registration=============");
        System.out.printf("||Username: ");
        String tempUsername = sc.nextLine();
        System.out.printf("||Password: ");
        String tempPassword = sc.nextLine();

        System.out.println("\n||Fill in your User Detail");
        System.out.printf("||Real Name: ");
        String tempName = sc.nextLine();
        System.out.printf("||Email: ");
        String tempEmail = sc.nextLine();


        Voter tempVoter = new Voter("V1003",tempName, tempEmail, tempUsername, tempPassword);
        voterLinkedList.add(tempVoter);  
    }
    
    public static void loginVoter(){
        int loginTry = 0;
        //Check if user logged in
        if(!isLogged){ 
            //login only attempt less than 3 times
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
            //logout voter
            logoutVoter();
        }
        System.out.println("\nBack to menu....");
        
    }
    
    public static void logoutVoter(){
        System.out.printf("Do you wish to logout? (\"Y\" to log out): ");
        char logoutAccChoice = sc.next().charAt(1);

        if(logoutAccChoice == 'Y' || logoutAccChoice == 'y'){
            System.out.println("Voter " + voterLinkedList.getEntry(curVoterIndex).getVoterName() 
                    + "(ID: " + voterLinkedList.getEntry(curVoterIndex).getVoterID() + ") has successfully logout.");
            isLogged = false;
            curVoterIndex = 0;
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
    
    public static void updateVoterMenu(){
        int updateChoice;
        do{
            System.out.println("=======Update Voter Info==================");
            System.out.println("|| 1) Username");
            System.out.println("|| 2) Email");
            System.out.println("|| 3) Password");
            System.out.println("|| 4) Back to menu");
            System.out.println("==========================================");
            updateChoice = sc.nextInt();
            sc.nextLine();

            switch(updateChoice) {
                case 1: case 2: case 3:
                    updateVoter(updateChoice);
                    break;
                    
                case 4:
                    System.out.println("Back to Menu...");
                    break;
                    
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }while(updateChoice != 4);  
    }
    
    public static void updateVoter(int updateOption){    
        //declare temporary info
        String tempUpdatedUsername = curVoter.getUsername();
        String tempUpdatedEmail = curVoter.getEmail();
        String tempUpdatedPassword = curVoter.getPassword();
        
        switch (updateOption) {
            case 1:
                System.out.printf("\nNew Username: ");
                tempUpdatedUsername = sc.nextLine();
                break;
            case 2:
                System.out.printf("\nNew Email: ");
                tempUpdatedEmail = sc.nextLine();
                break;
            case 3:
                System.out.printf("\nNew Password: ");
                tempUpdatedPassword = sc.nextLine();
                break;
            default:
                break;
        }
        
        Voter tempUpdatedVoter = new Voter(curVoter.getVoterID(),curVoter.getVoterName(), tempUpdatedEmail, tempUpdatedUsername, tempUpdatedPassword);
        
        //Replace new info into existed Voter
        voterLinkedList.replace(curVoterIndex, tempUpdatedVoter);
        
        //Set updated Voter as logged user
        curVoter = voterLinkedList.getEntry(curVoterIndex);
        
    }
    
    public static void deleteVoterSelf(){
        System.out.println("===========Account Delete Confirmation===========");
        System.out.println("|| Once you delete your account, you won't     ||");
        System.out.println("|| be able to view or vote for the idol.       ||");
        System.out.println("|| ____________________________________________||");
        System.out.printf("|| Are you sure to delete? (\"Y\" to confirm): ");
        char deleteAccChoice = sc.next().charAt(1);
        
        if(deleteAccChoice == 'Y' || deleteAccChoice == 'y'){
            System.out.println("Voter " + voterLinkedList.getEntry(curVoterIndex).getVoterName() 
                    + "(ID: " + voterLinkedList.getEntry(curVoterIndex).getVoterID() + ") has successfully deleted.");
            voterLinkedList.remove(curVoterIndex); 
            isLogged = false; //Logout user
            curVoterIndex = 0; 
            
        }
        System.out.println("\nBack to menu....");
    }
    
    public static void loginNotify(){
        System.out.println("===LOGIN REQUIRED================");
        System.out.println("| Please proceed to login first |");
        System.out.println("=================================");
    }
}
