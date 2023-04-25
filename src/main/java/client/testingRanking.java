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
     public static void main(String []args){
         Poll polling = new Poll("MoonByul");
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
         
         for(int i = 0; i<500; i++){
             polling.addVote(votee1);
         }
         
         for(int i = 0; i<400; i++){
             polling.addVote(votee2);
         }
         
         for(int i = 0; i<480; i++){
             polling.addVote(votee3);
         }
         
          for(int i = 0; i<3000; i++){
             polling.addVote(votee4);
         }
          
           for(int i = 0; i<100; i++){
             polling.addVote(votee5);
         }
           
            for(int i = 0; i<200; i++){
             polling.addVote(votee6);
         }
         
         
         for (MapInterface.Entry<Votee, Integer> entry : polling.getPollStatus().getVoteCount().entrySet()) {
             System.out.println(entry.getKey());
        }
          ArrayList<Votee> voteeList = new ArrayList<>();
          voteeList = polling.defaultRanking();
          
          for(int i = 0; i<voteeList.size();i++){
              System.out.print(voteeList.get(i).getName() + " : ");
              System.out.println(polling.getPollStatus().getVoteCount().get(voteeList.get(i)));
          }
        
         
    }
}
