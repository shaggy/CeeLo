// shaggy (Jan Dabrowski)
// first version of CeeLo Dice game
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException; 

public class CeeLo
{
   public static void main(String[] args) throws IOException
   {
      //declaration block
      BufferedReader BR = new BufferedReader (new InputStreamReader(System.in));
      
      Player human = new Player();
      Player computer = new Player();

      double wallet = 100.0;
      double bet = 0.0;
           
   
      System.out.println("What's your name?:  ");
 
      human.setName(BR.readLine());
       
      computer.setName("Computer");
      
      System.out.println("\nWelcome to Cee Lo " + human.getName() + "\n");
     
     //------------------game loop------------------//
       
     
      do
      {
      
         System.out.println("\nWallet: " + wallet);
            
         System.out.println("How much would you like to bet?\n");
      
         bet = input(wallet);
           
         human.playerRolls();
      
         computer.playerRolls();
        
         showRoll(human);
         showRoll(computer);
      
         int scoreHuman = gameLogic(human.getDieValues(), human);
         int scoreComputer = gameLogic(computer.getDieValues(), computer);
      
         String victor = calculateWinner(scoreHuman,scoreComputer, human, computer);
         
         System.out.println("\nThe winner is: " + victor);
         
         wallet = adjustWallet(victor, wallet, bet, human, computer);
          
                                                                                  
     }  while (wallet > 0.0);
    
    System.out.println("\nWallet: " + wallet);
    System.out.println("Goodbye!");
      
     

 }
 /**
   @param int[] array = sorted array of all die values from player class 
   @param Player x = player object
   
   gameLogic returns an int with the score a player has based on their roll
 
 */
 public static int gameLogic(int[] array, Player x)
 {
   
   
   int playerScore = 0;
   
   // 4, 5, 6 is an automatic win
   
   if(array[0] == 4 && array[1] == 5 && array[2] == 6)
   {
      System.out.println(x.getName() + " won!");
      playerScore = 100; //high number not achieved by any other rolls
   
   }
   // 1, 2, 3 is an automatic loss
   else if (array[0] == 1 && array[1] == 2 && array[2] == 3)
    {
         System.out.println(x.getName() + " loses!");
         playerScore = -100; //no roll can give neg value
    }
   //sorted array may either have two of same number at beginning or end - leftover is score
   else if (array[0] == array[1] && array[1] != array[2])
   {
      playerScore = array[2];
      System.out.println(x.getName() + " got: " +  playerScore);
   }
   else if (array[1] == array[2] && array[2] != array[0])
   {
      playerScore = array[0];
      System.out.println(x.getName() + " got: " + playerScore);
   
   }
   //triple of same number worth more than above scores. Multiply score by 10 to signify this. 
   else if (array[0] == array[1] && array[1] == array[2])
   {
      System.out.println(x.getName() + " rolled trip " + array[0] + "'s!");
      playerScore = array[0] * 10;
   
   }
   // No score: roll, print roll, recursive call to gameLogic with new die values
   else
   {
      System.out.println(x.getName() + " got: nothing. Re-rolling...");
      x.playerRolls();
      showRoll(x);
      playerScore = gameLogic(x.getDieValues(), x);
   }
 
 return playerScore;
 }
 /**
   @param  int a = human's score
   @param int b = computer's score
   @param Player x = human player
   @param Player ai = computer
   
   calculateWinner compares the scores attained by human and player and
returns string with winner's name.
 
 */
 public static String calculateWinner(int a, int b, Player x, Player ai)
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
@param String s = victor from main, which stores name of winner of round
@param double max = wallet amount (initialized to 100 to start)
@param double wager = bet amount
@param Player x = human player
@param Player ai = computer
adjustWallet removes lost bet or adds winnings to wallet amount
*/
 
 public static double adjustWallet(String s, double max, double wager, Player x, Player ai)
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
   @param Player x = player object instance
   
   showRoll prints out player's roll values.
*/
 public static void showRoll(Player x)
 {
      System.out.println(x.getName() + " rolls... ");
         
      for (int i = 0; i < 3; i++)
      {
         System.out.println(x.getDieValues()[i] + " ");
      } 
      
      System.out.println();
 }
/**
@param double max = wallet amount
input takes in the amount a player wants to bet and returns it if it's more than 0 and within wallet amount
*/
public static double input(double max) throws IOException
   {
      BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
      double value;
      
      value = Double.parseDouble(BR.readLine());;
      
      if(value < 0 || value > max)
         value = validation(value, max);
      
      return value;
   }
/**
@param double x = amount a player wants to bet
@param double max = wallet amount
validation tells player if they input an amount 0 or less, or more than they have in the wallet 
and takes in input until it meets the rules. Returns to input method. 
*/   
public static double validation(double x, double max) throws IOException
   {
      BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

      
      while( x < 0 || x > max)
      {
         System.out.println("\nValue must be greater than 0! Max bet = whole wallet.\n");
         x = Double.parseDouble(BR.readLine());
         
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
   private int[] dieValues = new int[3];
         
   void playerRolls()
   {
      final int numDie = 3;
      final int numSides = 6;
      
      Die[] dieArray = new Die[numDie];
      
      for (int i = 0; i < dieArray.length; i++)
      {
         dieArray[i] = new Die(numSides);
      }
      
      for (int i = 0; i < dieArray.length; i++)
      {
         dieValues[i] = dieArray[i].getValue();
      
      }
      
      selectionSort(dieValues);
     
     } 
    
    
    int[] getDieValues()
    {
      return dieValues;
    }
    
    void setName(String pname)
    {
      name = pname;
    }
    
    String getName()
    {
      return name;
    }
/**
@param int[] array = array to be sorted
selectionSort sorts an array's contents from lowest to highest
*/    
   void selectionSort(int[] array)
   {
      int startScan, index, minIndex, minValue;
      
      for (startScan = 0; startScan < array.length-1; startScan++)
      {
         minIndex = startScan;
         minValue = array[startScan];
         
         for( index = startScan + 1; index < array.length; index++)
         {
            if(array[index] < minValue)
            {
               minValue = array[index];
               minIndex = index;
            }
            
            array[minIndex] = array[startScan];
            array[startScan] = minValue;
         
         }
      }
   
   }
}
