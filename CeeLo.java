// shaggy CeeLo game
import java.util.*;
import java.io.*;


public class CeeLo
{
   public static void main(String[] args) throws IOException
   {
      //declaration block
      BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
      
      Player human = new Player();
      Player computer = new Player();
      CeeLo gameInstance = new CeeLo();

      double wallet = 100.0;
      double bet = 0.0;
      String quit = "";
      
      System.out.println("What's your name?:  ");
      human.setName(br.readLine());
      computer.setName("Computer");
      
      System.out.println("\nWelcome to Cee Lo " + human.getName() + "\n");
     
     //------------------game loop------------------//
       
     
      do
      
      {
      
         System.out.println("\nWallet: " + wallet + "\t\t<<[Any key to bet, q to quit]" );
         //System.out.println("([Any key to bet, q to quit])\n");
                  
         quit = br.readLine();
        
         if (quit.equals("q") || quit.equals("Q"))
         {
            break;
         }
         
         System.out.println("Bet amount: ");
         bet = gameInstance.input(wallet);
         
           
         human.playerRolls();
         computer.playerRolls();
         
         gameInstance.showRoll(human);
         gameInstance.showRoll(computer);
      
         int scoreHuman = gameInstance.gameLogic(human.getDieValues(), human);
         int scoreComputer = gameInstance.gameLogic(computer.getDieValues(), computer);
      
         String victor = gameInstance.calculateWinner(scoreHuman,scoreComputer, human, computer);
         System.out.println("The winner is: " + victor);
         
         wallet = gameInstance.adjustWallet(victor, wallet, bet, human, computer);
         
                                                                                  
     }  while (wallet > 0.0);
    
    System.out.println("\nWallet: " + wallet);
    System.out.println("\nGoodbye!");
      
     

 }
/**
*   gameLogic returns an int with the score a player has based on their roll
*
*   @param ArrayList<Integer> array = sorted array of all die values from player class 
*   @param Player x = player object
* 
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
   // No score: roll, print roll, recursive call to gameLogic with new die values
   else
   {
      System.out.println(x.getName() + " got: nothing. Re-rolling...");
      x.playerRolls();
      showRoll(x);
      playerScore = gameLogic(x.getDieValues(), x);
   }
      x.getDieValues().clear();//empty arraylist so it doesn't get filled dynamically on next roll and same roll returned
 
 return playerScore;
 }
/**
*   calculateWinner compares the scores attained by human and player and
* returns string with winner's name.
*
*   @param int a = human's score
*   @param int b = computer's score
*   @param Player x = human player
*   @param Player ai = computer
*   
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
      winner = "Nobody";
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
*
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
*   showRoll prints out player's roll values.
*   
*   @param Player x = player object instance
*   
*/
 public void showRoll(Player x)
 {    
      final int SIZE = 3;
      System.out.println(x.getName() + " rolls... \n");
         
      for (int i = 0; i < SIZE; i++)
      {
         System.out.print(x.getDieValues().get(i) + " ");
      } 
      
      System.out.println("\n");
      
 }
/**
*     The input method takes in the amount a player wants to bet as a
*  <code>String</code> and returns it as a <code>double</code> if it's more than
*  zero and within the wallet amount.
*
* @param double max = wallet amount
* @return value : amount player wants to bet
*/
public double input(double max) throws IOException
   {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      double value;
      
      value = Double.parseDouble(br.readLine());
      
      if(value < 0 || value > max)
        
         value = validation(value, max);
        
      return value;
   }
/**
*  validation tells player if they input an amount 0 or less, or more than they have in the wallet; 
*  retakes input until conditions satisfied. 
*
*
* @param double x : amount a player wants to bet
* @param double max : wallet amount

  @return x : amount the player wants to bet - returns as <code>double</code>
*/   
public double validation(double x, double max) throws IOException
   {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      
      while( x <= 0 || x > max)
      {
         System.out.println("\nValue must be greater than 0! Max bet = whole wallet.\n");
         x = Double.parseDouble(br.readLine());
         
      }
   
      return x;

  }


}


class Die 
{
   private int sides;
   private int value;
   
   Die(int numSides)
   {
      sides = numSides;
      roll();
      
   }
   
   void roll() 
   {
      Random rand = new Random();
      value = rand.nextInt(sides) + 1;
   }
   
   int getSides()
   {
      return sides;
   }
   
   int getValue()
   {
      return value;
   
   }

}

class Player
{  
   private String name;
   private ArrayList<Integer> dieValues;
   
   Player()
   {
      this.dieValues = new ArrayList<>();
   
   }

  
   
   private final int numDie = 3;
   private final int numSides = 6;

   void playerRolls()
   {
      ArrayList<Die> dieArray = new ArrayList<>(numDie);
             
      
      for (int i = 0; i < numDie; i++)
      {
         dieArray.add(new Die(numSides));
         
      }
      
      for (int i = 0; i < numDie; i++)
      {
         
         dieValues.add(i, dieArray.get(i).getValue());
      }
      
         Collections.sort(dieValues); 
         dieArray.clear();
    } 
    
    
    ArrayList<Integer> getDieValues()
    {
      return dieValues;
    }
    
    void setName(String name)
    {
      this.name = name;
    }
    
    String getName()
    {
      return name;
    }
}



