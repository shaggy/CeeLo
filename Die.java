import java.util.*;
public class Die 
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
