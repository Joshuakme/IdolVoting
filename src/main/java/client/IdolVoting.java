package client;

// Import ADT
import adt.HashMap;
import adt.MapInterface;
import adt.SortedList;
import adt.SortedListInterface;
// Import Entity
import entity.Admin;
import entity.Poll;
import entity.Votee;
import entity.Voter;
import entity.User;
// Import Util
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * @author Joshua Koh Min En
 * @author Lai Chee Sheng
 * @author Lim Kang Yi
 * @author Shia Chai Fen
 */
public class IdolVoting { 
    // Global Variables
    private static Scanner sc = new Scanner(System.in);
    
    
    private static boolean isLogged = false;
    private static User currentUser;
    
    private static Admin admin;
    
    public static void main(String[] args) {
        // CONSTANT
        
        // Variable Initialization
        
        // Status Variables
        
        
        System.out.println("Hello World!");
    }
    
    // Init Methods
    public static Admin initAdmin() {
        return new Admin();
    }
    
    // Display Methods
    public static void adminMenuLev1(String[][] menuItemArr) {          
        for(int i=0; i<menuItemArr.length; i++) {
            System.out.println((i+1) + ". " + menuItemArr[i][0]);
        }
        System.out.print("\n");
    }
    
    public static void adminMenuLev2(String[][] menuItemArr, int choice) {    
        choice = choice - 1;
     
        for(int i=1; i<menuItemArr[choice].length; i++) {
            System.out.println((i+1) + ". " + menuItemArr[choice][i]);
        }
    }
    
    public static void adminActionLev2(int choiceAdminLevel1,int choiceAdminLevel2) {
        if(choiceAdminLevel1 == 1) {
            // Votee
            switch(choiceAdminLevel2) {
                case 1:
                    // Create Votee Function
                    
                    // - Get new Votee details
                    System.out.println("Please enter the details of the new votee in the format(ID, Name, Description)");
                    System.out.println("Example: V001, Joshua Koh, A popular Malaysian singer and songwriter.");
                    System.out.print("New Votee Details: ");
                    String newVoteeDetails = sc.nextLine();
                    
                    // Split string by character ","
                    String[] newVoteeDetaiLArr = newVoteeDetails.split(",");
                    
                    // Assign new Votee detail
                    Votee newVotee = new Votee(newVoteeDetaiLArr[0], newVoteeDetaiLArr[1], newVoteeDetaiLArr[2]);
                    
                    // Create New Votee
                    admin.createVotee(newVotee);
                    break;
                case 2:
                    // Update Votee
                    break;
                case 3:
                    // Delete Votee
                    break;
                default:
                    break;
            }
        } else if(choiceAdminLevel1 == 2) {
            // Poll
            switch(choiceAdminLevel2) {
                case 1:
                    // Start Poll
                    System.out.print("Enter the poll title: ");
                    String pollName = sc.nextLine(); 
                    
                    admin.startPoll(pollName);;
                    break;
                case 2:
                    // End Poll
                    break;
                case 3:
                    // View Poll Status
                    displayPollStatus();
                    break;
                default:
                    break;
            }
        } 
    }
    
    public static void displayPollStatus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Operation Methods
    
    // Utils Methods

}
