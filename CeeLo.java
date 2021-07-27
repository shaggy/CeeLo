// shaggy CeeLo game
import java.util.*;

public class CeeLo
{
 
 
/**
*   gameLogic calculates score a player has based on their roll and CeeLo rules
*
*   @param ArrayList<Integer> array = sorted array of all die values from player class 
*   @param Player x = player object
*   @return int with player score
*/
 public int gameLogic(ArrayList<Integer> array, Player x)
 {
   
   
   int playerScore = 0;
   
   // 4, 5, 6 is an automatic win
   
   if(array.get(0) == 4 && array.get(1) == 5 && array.get(2) == 6)
   {  
      
      System.out.println("-----456: Automatic win!-----\n" );
      playerScore = 100; //high number not achieved by any other rolls
   
   }
   // 1, 2, 3 is an automatic loss
   else if (array.get(0) == 1 && array.get(1) == 2 && array.get(2) == 3)
    {
         System.out.println("-----123: Automatic loss!-----\n");
         playerScore = -100; //no roll can give neg value
    }
   //sorted array may either have two of same number at beginning or end - leftover is score
   else if (array.get(0) == array.get(1) && array.get(1) != array.get(2))
   {
      playerScore = array.get(2);
      System.out.println(x.getName() + " got: " +  playerScore + "\n");
   }
   else if (array.get(1) == array.get(2) && array.get(2) != array.get(0))
   {
      playerScore = array.get(0);
      System.out.println(x.getName() + " got: " + playerScore + "\n");
   
   }
   //triple of same number worth more than above scores. Multiply score by 10 to signify this. 
   else if (array.get(0) == array.get(1) && array.get(1) == array.get(2))
   {
      System.out.println(x.getName() + " rolled trip " + array.get(0) + "'s!\n");
      playerScore = array.get(0) * 10;
   
   }
   // No score: set score to 0
   else
   {
     playerScore = 0;
     
   }
      x.getDieValues().clear();//empty arraylist so it doesn't get filled dynamically on next roll and same roll returned
 
 return playerScore;
 }
/**
*   calculateWinner compares the scores attained by human and player 
*   
*
*   @param int a = human's score
*   @param int b = computer's score
*   @param Player x = human player
*   @param Player ai = computer
*   @return string with winner's name
*/
 public String calculateWinner(int a, int b, Player x, Player ai)
 {
   String winner = " ";
   
   if(a > b)
   {
      winner = x.getName();
   }
   else if ( a <  b)
   {
      winner = ai.getName();
   }
   else
   {
      System.out.println("Tie!");
      winner = "Nobody: Tie!";
   }
   return winner;
 }
/**
*   adjustWallet deducts lost bet or adds winnings to wallet amount
*
* @param String s = victor from main, which stores name of winner of round
* @param double max = wallet amount (initialized to 100 to start)
* @param double wager = bet amount
* @param Player x = human player
* @param Player ai = computer
* @return max 
*/
 
 public double adjustWallet(String s, double max, double wager, Player x, Player ai)
 {
   if (s.equals(x.getName()))
         {
            max += wager;
         }else if (s.equals(ai.getName()))
         {
            max -= wager;
         }

   return max;
}


/**
*   showRoll get's player's die values
*   
*   @param Player x = player object instance
*   
*/

public int[] showRoll(Player x)
 {    
      final int SIZE = 3;
      
      int[] rollArray = new int[SIZE];
      
      for (int i = 0; i < SIZE; i++)
      {
        rollArray[i] = x.getDieValues().get(i);
         
      } 
      
     return rollArray;
      
 }


}

