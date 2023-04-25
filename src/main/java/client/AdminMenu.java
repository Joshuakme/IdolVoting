package client;

import entity.Admin;
import entity.Votee;
import entity.Poll;
import adt.HashMap;
import adt.MapInterface;
import adt.LinkedList;
import adt.ListInterface;
import adt.SortedList;
import java.util.Scanner;

/**
 *
 * @author Joshua Koh Min En
 */
public class AdminMenu {
    // Global util
    private static Scanner sc = new Scanner(System.in);
    // Global Variables
    private static LinkedList<Poll> pollLinkedList  = new LinkedList<>();
    private static int curVotingPollIndex;
    
    // Global Status
    private static boolean isLogged = false;
    // Global User
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
            sc.nextLine();
            
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
                        sc.nextLine();

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
        
        System.out.println("\n");
        for(int i=1; i<menuItemArr[choice].length; i++) {
            System.out.println((i) + ". " + menuItemArr[choice][i]);
        }
        System.out.print("\n");
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
    
    public static void displayVoteeList(ListInterface<Votee> voteeList) {
        // Display the list of matchced votee
        System.out.println("\nBelow is the list of Votee that matched the entered name:");
        System.out.printf("%4s %-8s %-10s %-20s","No.", "Votee ID", "Votee Name", "Votee Desc");
        for(int i=0; i<voteeList.size(); i++) {
            System.out.printf("%4s %-8s %-10s %-20s", (i+1) + ". ", voteeList.get(i).getId(), voteeList.get(i).getName(), voteeList.get(i).getDescription());
        }
        System.out.println("\n");
    }
    
    // Operation Methods
    public static void adminActionLev2(int choiceAdminLevel1,int choiceAdminLevel2) {
        if(choiceAdminLevel1 == 1) {
            // Votee
            switch(choiceAdminLevel2) {
                case 1:
                    // Create Votee Function
                    boolean validCreateVoteeInput = false;
                    
                    while(!validCreateVoteeInput) {
                            // - Get new Votee details
                        System.out.println("Please enter the details of the new votee in the format(Name, Description)");
                        System.out.println("Example: Joshua Koh, A popular Malaysian singer and songwriter.");
                        System.out.print("New Votee Details: ");
                        String newVoteeDetails = sc.nextLine();

                        // Split string by character ","
                        String[] newVoteeDetailArr = newVoteeDetails.split(", ");

                        if(newVoteeDetailArr.length == 2) {
                            // Assign new Votee detail
                            Votee newVotee = new Votee(newVoteeDetailArr[0], newVoteeDetailArr[1]);

                            // Create New Votee
                            pollLinkedList.get(curVotingPollIndex).addVotee(newVotee);
                            //admin.createVotee(newVotee);
                            
                            // break loop
                            validCreateVoteeInput = true;
                            
                            // Successful Message
                            System.out.println("New Votee (" + newVotee.getName()+ ") created successfully!!");
                        } else if (newVoteeDetailArr.length < 2) {
                            System.err.println("\nYou have entered too few information. Please try again!\n");
                        } else {
                            System.err.println("\nYou have entered too much information. Please try again!\n");
                        }  
                    }
                    break;
                case 2:
                    // Update Votee Information
                    boolean validUpdateVoteeInput = false;
                    
                    while(!validUpdateVoteeInput) {
                        System.out.print("Please enter the Votee Name you would like to update: ");
                        String updateVoteeName = sc.nextLine();

                        // Get list of votee that matched the name of the entered name
                        ListInterface<Votee> searchedVoteeList = pollLinkedList.get(curVotingPollIndex).searchVotee(updateVoteeName);
                        
                        // If search result is not null
                        if(searchedVoteeList != null) {
                            boolean validselectedVoteeIndex = false;
                            
                            while(!validselectedVoteeIndex) {
                                displayVoteeList(searchedVoteeList);

                                // Get confirmed Votee
                                System.out.print("Please select the Votee you would like to update: ");
                                int selectedVoteeIndex = sc.nextInt();
                                sc.nextLine();

                                // Check if valid selectedVoteeIndex
                                if(selectedVoteeIndex > 0 && selectedVoteeIndex < searchedVoteeList.size()) {
                                    boolean validUpdatedVoteeDetails = false;
                                    
                                    while(!validUpdatedVoteeDetails) {
                                        // Get updated detail of the selected Votee
                                        System.out.println("Please enter the updated detail: ");

                                        System.out.println("Old: " + searchedVoteeList.get(selectedVoteeIndex).getId() + ", " + searchedVoteeList.get(selectedVoteeIndex).getName() + ", " +
                                                            searchedVoteeList.get(selectedVoteeIndex).getDescription());
                                        System.out.print("New: " + searchedVoteeList.get(selectedVoteeIndex).getId() + ", ");

                                        String updatedVoteeDetails = sc.nextLine();

                                        // Split string by character ","
                                        String[] updatedVoteeDetailArr = updatedVoteeDetails.split(", ");

                                        // Check if the input is valid (exactly 2 parameter)
                                        if(updatedVoteeDetailArr.length == 2) {
                                            Votee updatedVotee = new Votee(updatedVoteeDetailArr[0],updatedVoteeDetailArr[1]);

                                            // Update the selected Votee detail
                                            pollLinkedList.get(curVotingPollIndex).updateVotee(searchedVoteeList.get(selectedVoteeIndex), updatedVotee);
                                            //admin.updateVotee(updatedVotee);

                                            // break loop
                                            // Set all flags to true
                                            validUpdatedVoteeDetails = true;        // This Level
                                            validselectedVoteeIndex = true;         // Upper Level
                                            validUpdateVoteeInput = true;           // Outmost Level

                                            // Successful Message
                                            System.out.println("Updated Votee (" + updatedVotee.getName()+ ") updated successfully!!");
                                        }
                                        else if (updatedVoteeDetailArr.length < 2) {
                                            System.err.println("\nYou have entered too few information. Please try again!\n");
                                        } else {
                                            System.err.println("\nYou have entered too much information. Please try again!\n");
                                        }  
                                    } 
                                } else {
                                    // If invalid selectedVoteeIndex

                                    System.err.println("\nPlease enter a number that is in the range of (1 - " + searchedVoteeList.size() + "). Please try again\n");
                                }
                            }
                        } else {
                            // If search result is null
                            
                            System.err.println("\nThere is no Votee in the list that matched the entered name. Please try again\n");
                        }
                    }       
                    break;
                case 3:
                    // Delete Votee
                    boolean validDeleteVoteeInput = false;
                    
                    while(!validDeleteVoteeInput) {
                        System.out.print("Please enter the Votee Name you would like to delete: ");
                        String deleteVoteeName = sc.nextLine();
                        
                        // Get list of votee that matched the name of the entered name
                        ListInterface<Votee> searchedVoteeList = pollLinkedList.get(curVotingPollIndex).searchVotee(deleteVoteeName);
                        
                        // If search result is not null
                        if(searchedVoteeList != null) {
                            boolean validSelectedVoteeIndex = false;
                            
                            while(!validSelectedVoteeIndex) {    
                                displayVoteeList(searchedVoteeList);

                                // Get confirmed Votee
                                System.out.print("Please select the Votee you would like to delete: ");
                                int selectedVoteeIndex = sc.nextInt();
                                sc.nextLine();

                                // Check if valid selectedVoteeIndex
                                if(selectedVoteeIndex > 0 && selectedVoteeIndex <= searchedVoteeList.size()) {
                                    boolean validConfirmDeleteVoteeInput = false;
                                    
                                    while(!validConfirmDeleteVoteeInput) {
                                        // Print selected Votee detail
                                        System.out.println("Below is the detail of the selected Votee: ");
                                        System.out.println("Votee ID: " + searchedVoteeList.get(selectedVoteeIndex).getId() + "\n" +
                                                            "Votee Name: " + searchedVoteeList.get(selectedVoteeIndex).getName() + "\n" +
                                                            "Votee Description: " + searchedVoteeList.get(selectedVoteeIndex).getDescription() + "\n");

                                        // Get confirmation from the admin
                                        System.out.println("Do you confirm to delete the Votee? (Y/N/0) ");
                                        char deleteVoteeConfirmation = Character.toUpperCase(sc.nextLine().charAt(0));

                                        if(deleteVoteeConfirmation == 'Y') {
                                            pollLinkedList.get(curVotingPollIndex).removeVotee(searchedVoteeList.get(selectedVoteeIndex));
                                            //admin.deleteVotee(searchedVoteeList.get(selectedVoteeIndex).getId());

                                            // Set all flags to true
                                            validConfirmDeleteVoteeInput = true;    // Current Level
                                            validSelectedVoteeIndex = true;         // Upper Level
                                            validDeleteVoteeInput = true;           // Outmost Level
                                        } else if(deleteVoteeConfirmation == 'N' || deleteVoteeConfirmation == 0) {
                                            validSelectedVoteeIndex = true;
                                        } else {
                                            System.err.println("Please enter a Y-Yes, N-No, 0-Return to last step");
                                        }
                                    }
                                } else {
                                    // If invalid selectedVoteeIndex

                                    System.err.println("\nPlease enter a number that is in the range of (1 - " + searchedVoteeList.size() + "). Please try again\n");
                                }
                            } 
                        } else {
                            // If search result is null
                            
                            System.err.println("\nThere is no Votee in the list that matched the entered name. Please try again\n");
                        }
                    }
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
                    
                    // Add new Poll object into pollList
                    pollLinkedList.add(new Poll(pollName));
                    
                    // Update the current Voting Poll Index to the latest poll
                    curVotingPollIndex = pollLinkedList.size()-1;
                    break;
                case 2:
                    // End Poll
                    boolean validEndPollInput = false;
                        
                    while(!validEndPollInput) {
                        displayAvailablePolls();
                        
                        SortedList<Poll> availablePollList = getAvailablePolls();
                        
                        // Get confirmed Votee
                        System.out.print("Please select the Poll you would like to end: ");
                        int endPollInput = sc.nextInt();
                        sc.nextLine();
                        
                        // Check if valid endPollInput
                        if(endPollInput > 0 && endPollInput <= availablePollList.size()) {
                            boolean validConfirmEndPollInput = false;
                            
                            while(!validConfirmEndPollInput) {
                                // Get confirmation from the admin
                                System.out.println("Do you confirm to end this Poll? (Y/N/0) ");
                                char deleteVoteeConfirmation = Character.toUpperCase(sc.nextLine().charAt(0));

                                if(deleteVoteeConfirmation == 'Y') {
                                    pollLinkedList.get(curVotingPollIndex).end();

                                    // Set all flags to true
                                    validConfirmEndPollInput = true;            // Current Level
                                    validEndPollInput = true;                   // Outmost Level
                                } else if(deleteVoteeConfirmation == 'N' || deleteVoteeConfirmation == 0) {
                                    validConfirmEndPollInput = true;
                                } else {
                                    System.err.println("Please enter a Y-Yes, N-No, 0-Return to last step");
                                }
                            }
                        } else {
                            // If invalid endPollInput

                            System.err.println("\nPlease enter a number that is in the range of (1 - " + availablePollList.size() + "). Please try again\n");
                        } 
                    }
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
    
    // Util Methods
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
    
    public static int getCurVotingPollIndex() {
        boolean validCurVotingPollIndex = false;
        
        while(!validCurVotingPollIndex) {
            SortedList<Poll> availablePollList = getAvailablePolls();
                    
            System.out.println("Below is the list of available Polls");
        
            // Display Available Polls
            displayAvailablePolls();

            // Get input from user
            System.out.println("Please select a poll to proceed: ");
            int curVotingPollIndex = sc.nextInt();
            sc.nextLine();
            
            if(curVotingPollIndex > 0 && curVotingPollIndex <= availablePollList.size()) {
                // Proceed
                
                
            } else {
                // If invalid endPollInput

                System.err.println("\nPlease enter a number that is in the range of (1 - " + availablePollList.size() + "). Please try again\n");
            }
        }
        
        return -1;
    }
    

    

}
