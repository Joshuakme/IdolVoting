package client;

import entity.Admin;
import entity.Votee;
import adt.HashMap;
import adt.MapInterface;
import java.util.Scanner;

/**
 *
 * @author Joshua Koh Min En
 */
public class AdminMenu {
    // Global Variables
    private static Scanner sc = new Scanner(System.in);
    private static boolean isLogged = false;
    private static Admin admin;
    
    public static void main(String[] args) {
        // Constant
        String[][] MENU_ITEM_ARR = {
            {"Votee", "Create Votee", "Update Votee", "Delete Votee"},
            {"Poll", "Start Poll", "End Poll", "View PolL Status"},
            {"Login"}
        };
        
        // Variables Initialization
        admin = initAdmin();
        
        // Status Variables
        boolean notExit = true;
        

        while(notExit) {
            System.out.println("\n--- Welcome to ADMIN ---\n");
            
            // Display Admin Menu
            adminMenuLev1(MENU_ITEM_ARR);    
            
            System.out.print("Please select choice (1 - " + MENU_ITEM_ARR.length + "): ");
            int choiceAdmin = sc.nextInt();
            sc.next();
            
            switch (choiceAdmin) {
                case 1:
                case 2:
                    if(!isLogged) {
                        // Error Msg
                        break;
                    } else {
                        adminMenuLev2(MENU_ITEM_ARR,choiceAdmin);
                        // Get level 2 choice
                        System.out.print("Please select choice (1 - " + MENU_ITEM_ARR.length + "): ");
                        int choiceAdmin2 = sc.nextInt();

                        adminActionLev2(choiceAdmin,choiceAdmin2);
                        break;
                    }
                case 3:
                    // Assume admin credentials entered correctly
                    admin = new Admin();
                    // Log in process
                    isLogged = true;
                    break;
                case 0:
                    notExit = false;
                    break;
                default:
                    break;
            }
            
            
            Votee votee1 = new Votee("V001", "Ariana Grande", "A popular American singer and songwriter.");
            Votee votee2 = new Votee("V002", "Justin Bieber", "A Canadian singer, songwriter, and multi-instrumentalist.");
            
            
            MapInterface<String, Votee> voteeMap = new HashMap<>();
            voteeMap.put(votee1.getId(), votee1);
            voteeMap.put(votee2.getId(), votee2);
            
            // Update the description of votee1
            votee1.setDescription("An award-winning singer with a powerful voice.");

            // Update the entry in the HashMap
            voteeMap.put(votee1.getId(), votee1);

            // Delete votee2 from the HashMap
            voteeMap.remove(votee2.getId());

        }
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
}
