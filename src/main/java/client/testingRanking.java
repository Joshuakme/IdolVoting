/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import adt.ArrayList;
import adt.MapInterface;
import entity.Poll;
import entity.Votee;

/**
 *
 * @author Tiffany
 */
public class Testing {

    private static Poll polling = new Poll("MoonByul");

    public static void main(String[] args) {

        polling.setIsOpen(true);

        Votee votee1 = new Votee("Heng Heng", "Idol");
        Votee votee2 = new Votee("Heng Ong", "Idol");
        Votee votee3 = new Votee("Ong Huat", "Idol");
        Votee votee4 = new Votee("Choy San Yeh", "God");
        Votee votee5 = new Votee("Nah Tok Gong", "God");
        Votee votee6 = new Votee("Teh Zu Gong", "God");

        polling.addVotee(votee1);
        polling.addVotee(votee2);
        polling.addVotee(votee3);
        polling.addVotee(votee4);
        polling.addVotee(votee5);
        polling.addVotee(votee6);

        for (int i = 0; i < 500; i++) {
            polling.addVote(votee1);
        }

        for (int i = 0; i < 400; i++) {
            polling.addVote(votee2);
        }

        for (int i = 0; i < 480; i++) {
            polling.addVote(votee3);
        }

        for (int i = 0; i < 3000; i++) {
            polling.addVote(votee4);
        }

        for (int i = 0; i < 100; i++) {
            polling.addVote(votee5);
        }

        for (int i = 0; i < 200; i++) {
            polling.addVote(votee6);
        }

//         for (MapInterface.Entry<Votee, Integer> entry : polling.getPollStatus().getVoteCount().entrySet()) {
//             System.out.println(entry.getKey());
//        }
        ArrayList<Votee> voteeList = new ArrayList<>();
        voteeList = polling.descRanking();
//          
//          for(int i = 0; i<voteeList.size();i++){
//              System.out.print(voteeList.get(i).getName() + " : ");
//              System.out.println(polling.getPollStatus().getVoteCount().get(voteeList.get(i)));
//          }
//        
//        
        result(voteeList, 5, 'B');

    }

    public static void result(ArrayList<Votee> voteeList, int pollIndex, char chooseRankType) {
        if (voteeList != null || !voteeList.isEmpty()) {
            switch (Character.toUpperCase(chooseRankType)) {
                case 'A':
                    System.out.println("*********************************************************************************************");
                    System.out.println("*             R E S U L T  F O R  S I N G I N G  I D O L  C O M P E T I T I O N             *");
                    System.out.println("*********************************************************************************************");
                    System.out.printf("%-10s %-20s %-6s", "No", "Name", "Votes");
                    System.out.println("\n");
                    break;

                case 'B':
                    System.out.println("******************************************************************************************************");
                    System.out.println("*          R A N K I N G   R E S U L T  F O R  S I N G I N G  I D O L  C O M P E T I T I O N          *");
                    System.out.println("*******************************************************************************************************");
                    System.out.printf("%-10s %-20s %-6s", "Rank", "Name", "Votes");
                    System.out.println("\n");
                    break;

                case 'C':
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

            for (int i = 0; i < voteeList.size(); i++) {
                System.out.printf("%-10s %-20s %-6d\n", (i + 1) + ". ", voteeList.get(i).getName(), polling.getPollStatus().getVoteCount().get(voteeList.get(i)));
            }
        } else {
            System.out.println("***********************************************");
            System.out.println("*             The List is EMPTY!              *");
            System.out.println("***********************************************");
        }
    }
}
